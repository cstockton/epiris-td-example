package epiris.tower.defense.cstockton.org.spell.affect;

import java.util.ArrayList;

import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.HealSpellAffectTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class HealSpellAffect extends SpellAffect {
    final public static int DISTANCE_ALGO_NONE = 0;
    final public static int DISTANCE_ALGO_BY_100 = 1;
    final public static int DISTANCE_ALGO_BY_75 = 2;
    final public static int DISTANCE_ALGO_BY_50 = 3;
    final public static int DISTANCE_ALGO_BY_10 = 4;

    final public static int ACCUMULATOR_ALGO_NONE = 0;
    final public static int ACCUMULATOR_ALGO_MINUS_DIV_10 = 1;
    final public static int ACCUMULATOR_ALGO_MINUS_DIV_2 = 2;
    final public static int ACCUMULATOR_ALGO_PLUS_DIV_10 = 3;

    private final ArrayList<ISpellAffectable> mSpellAffectables = new ArrayList<ISpellAffectable>();

    public float mHealAmount;
    public boolean mHealIsPercentage;

    public int mDistanceAlgo;
    public int mAccumulatorAlgo;

    public float mHealToHotTickInterval;
    public int mHealToHotTickCount;
    public float mHealToHotAmount;

    public int mAoeRange;
    public int mAoeMaxTargets;
    public float mAoeHealModifier;

    static final private GenericPool<HealSpellAffect> mHealSpellAffect = new GenericPool<HealSpellAffect>() {

        @Override
        protected HealSpellAffect onAllocatePoolItem() {
            return new HealSpellAffect();
        }
    };

    static final public HealSpellAffect obtain() {
        return mHealSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final HealSpellAffect pHealSpellAffect) {
        mHealSpellAffect.recyclePoolItem(pHealSpellAffect);
        pHealSpellAffect.reset();
    }

    @Override
    public void finished() {
        HealSpellAffect.recycle(this);
    }


    @Override
    public void onRefresh(final ISpellAffects pSpellAffectType) {
        onApplication();
    }

    @Override
    public void onApplication() {
        float healMultiplier = 0f;

        switch(mDistanceAlgo) {
            default:
            case DISTANCE_ALGO_NONE: break;

            case DISTANCE_ALGO_BY_100: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                healMultiplier = distance / 100f;
            } break;

            case DISTANCE_ALGO_BY_75: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                healMultiplier = distance / 75f;
            } break;

            case DISTANCE_ALGO_BY_50: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                healMultiplier = distance / 50f;
            } break;

            case DISTANCE_ALGO_BY_10: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                healMultiplier = distance / 10f;
            } break;
        }

        final float curStack = getCurrentStackCount();
        switch(mAccumulatorAlgo) {
            default:
            case ACCUMULATOR_ALGO_NONE: break;

            case ACCUMULATOR_ALGO_PLUS_DIV_10: {
                healMultiplier = healMultiplier + (curStack / 10);
            } break;

            case ACCUMULATOR_ALGO_MINUS_DIV_10: {
                healMultiplier = healMultiplier + (-(curStack / 10));
                if(healMultiplier < -.95f) {
                    healMultiplier = -.95f;
                }
            } break;

            case ACCUMULATOR_ALGO_MINUS_DIV_2: {
                healMultiplier = healMultiplier + (-(curStack / 2));
                if(healMultiplier < -.95f) {
                    healMultiplier = -.95f;
                }
            } break;
        }

        final float healingDone = mHealAmount * (healMultiplier + 1f);
        mAffected.heal(healingDone, mHealIsPercentage);

        if(mHealToHotAmount > 0f) {

            final HealSpellAffect hot = (HealSpellAffect) HealSpellAffectTypes.ARBITRARY.getAffect();
            hot.setTickInterval(mHealToHotTickInterval);
            hot.setTickCount(mHealToHotTickCount);
            hot.setStackCount(1);
            hot.setRefreshable(false);

            hot.mHealAmount = (int) ((healingDone * mHealToHotAmount) / mHealToHotTickCount);
            hot.mHealIsPercentage = false;

            mAffected.applyAffect(mCaster, hot);

        }

        if(mAoeRange > 0) {
            mSpellAffectables.clear();

            Registry.sCreepManager.getSpellAffectablesInRange(mAffected.getX(), mAffected.getY(), mAoeRange, mSpellAffectables);

            int targetsCastUpon = 0;

            for(final ISpellAffectable target : mSpellAffectables) {
                if(++targetsCastUpon > mAoeMaxTargets) {
                    break;
                }
                if(target.equals(mAffected)) {
                    continue;
                }

                target.heal(healingDone, mHealIsPercentage);
            }
        }
    }
}