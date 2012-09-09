package epiris.tower.defense.cstockton.org.spell.affect;

import org.anddev.andengine.util.MathUtils;
import org.anddev.andengine.util.pool.GenericPool;

import epiris.tower.defense.cstockton.org.config.DamageTypes;
import epiris.tower.defense.cstockton.org.config.DotSpellAffectTypes;
import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;

public class DamageSpellAffect extends SpellAffect {
    final public static int DISTANCE_ALGO_NONE = 0;
    final public static int DISTANCE_ALGO_BY_100 = 1;
    final public static int DISTANCE_ALGO_BY_75 = 2;
    final public static int DISTANCE_ALGO_BY_50 = 3;
    final public static int DISTANCE_ALGO_BY_10 = 4;

    final public static int ACCUMULATOR_ALGO_NONE = 0;
    final public static int ACCUMULATOR_ALGO_MINUS_DIV_10 = 1;
    final public static int ACCUMULATOR_ALGO_MINUS_DIV_2 = 2;
    final public static int ACCUMULATOR_ALGO_PLUS_DIV_10 = 3;

    private Damages mDamages;
    private float mDamagesMultiplier;
    private int mDistanceAlgo;
    private int mAccumulatorAlgo;
    private float mDamageToDotAmount;

    static final private GenericPool<DamageSpellAffect> mDamageSpellAffect = new GenericPool<DamageSpellAffect>() {

        @Override
        protected DamageSpellAffect onAllocatePoolItem() {
            return new DamageSpellAffect();
        }
    };

    static final public DamageSpellAffect obtain() {
        return mDamageSpellAffect.obtainPoolItem();
    }

    static final public void recycle(final DamageSpellAffect pDamageSpellAffect) {
        mDamageSpellAffect.recyclePoolItem(pDamageSpellAffect);
        pDamageSpellAffect.reset();
    }

    public void setDamage(final int pDistanceAlgo, final int pAccumulatorAlgo, final float pDamageToDotAmount, final Damages pDamages) {
        mDamages = (Damages) pDamages.clone();
        mDamagesMultiplier = mDamages.mMultiplier;
        mDistanceAlgo = pDistanceAlgo;
        mAccumulatorAlgo = pAccumulatorAlgo;
        mDamageToDotAmount = pDamageToDotAmount;
    }

    @Override
    public void finished() {
        DamageSpellAffect.recycle(this);
    }


    @Override
    public void onRefresh(final ISpellAffects pSpellAffectType) {
        onApplication();
    }

    @Override
    public void onApplication() {
        mDamages.mMultiplier = mDamagesMultiplier;

        switch(mDistanceAlgo) {
            default:
            case DISTANCE_ALGO_NONE: break;

            case DISTANCE_ALGO_BY_100: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                mDamages.mMultiplier = distance / 100f;
            } break;

            case DISTANCE_ALGO_BY_75: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                mDamages.mMultiplier = distance / 75f;
            } break;

            case DISTANCE_ALGO_BY_50: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                mDamages.mMultiplier = distance / 50f;
            } break;

            case DISTANCE_ALGO_BY_10: {
                final float distance = MathUtils.distance(mAffected.getCenterX(), mAffected.getCenterY(), mCaster.getCenterX(), mCaster.getCenterX());
                mDamages.mMultiplier = distance / 10f;
            } break;
        }

        final float curStack = getCurrentStackCount();
        switch(mAccumulatorAlgo) {
            default:
            case ACCUMULATOR_ALGO_NONE: break;

            case ACCUMULATOR_ALGO_PLUS_DIV_10: {
                mDamages.mMultiplier = mDamages.mMultiplier + (curStack / 10);
            } break;

            case ACCUMULATOR_ALGO_MINUS_DIV_10: {
                mDamages.mMultiplier = mDamages.mMultiplier + (-(curStack / 10));
                if(mDamages.mMultiplier < -.95f) {
                    mDamages.mMultiplier = -.95f;
                }
            } break;

            case ACCUMULATOR_ALGO_MINUS_DIV_2: {
                mDamages.mMultiplier = mDamages.mMultiplier + (-(curStack / 2));
                if(mDamages.mMultiplier < -.95f) {
                    mDamages.mMultiplier = -.95f;
                }
            } break;
        }

        final int damageDone = mAffected.damage(mDamages);

        if(mDamageToDotAmount > 0f) {

            // @TODO we need a damages pool at this point
            // 6f = tick count, for a 1f int
            final Damages damages = new Damages(new Damage(DamageTypes.PHYSICAL, (int) ((damageDone * mDamageToDotAmount) / 6f)));

            final DotSpellAffect dot = DotSpellAffect.obtain();
            dot.setType(DotSpellAffectTypes.ARBITRARY);
            dot.setTickInterval(1f);
            dot.setTickCount(6);
            dot.setStackCount(1);

            dot.mTickDamages = damages;

            mAffected.applyAffect(mCaster, dot);

        }
    }
}