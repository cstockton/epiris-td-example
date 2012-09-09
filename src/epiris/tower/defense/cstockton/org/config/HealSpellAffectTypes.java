package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.spell.affect.HealSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

public enum HealSpellAffectTypes implements ISpellAffects {

    HEAL_50_HOT(0f, 0, 0, HealSpellAffect.DISTANCE_ALGO_NONE, HealSpellAffect.ACCUMULATOR_ALGO_NONE, .5f, true, 0f, 0, 0f, 0, 0, 0f),


    ARBITRARY(0f, 0, 0, HealSpellAffect.DISTANCE_ALGO_NONE, HealSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, false, 0f, 0, 0f, 0, 0, 0f),

    ;

    public final float mTickInterval;
    public final int mTickCount;
    public final int mStackCount;

    public final float mHealAmount;
    public final boolean mHealIsPercentage;
    public final int mDistanceAlgo;
    public final int mAccumulatorAlgo;

    public final float mHealToHotTickInterval;
    public final int mHealToHotTickCount;
    public final float mHealToHotAmount;

    public int mAoeRange;
    public int mAoeMaxTargets;
    public float mAoeHealModifier;

    HealSpellAffectTypes(
        final float pTickInterval, final int pTickCount, final int pStackCount,

        final int pDistanceAlgo, final int pAccumulatorAlgo,

        final float pHealAmount, final boolean pHealIsPercentage,

        final float pHealToHotTickInterval, final int pHealToHotTickCount, final float pHealToHotAmount,

        final int pAoeRange, final int pAoeMaxTargets, final float pAoeHealModifier

    ) {
        mTickInterval = pTickInterval;
        mTickCount = pTickCount;
        mStackCount = pStackCount;

        mHealAmount = pHealAmount;
        mHealIsPercentage = pHealIsPercentage;

        mDistanceAlgo = pDistanceAlgo;
        mAccumulatorAlgo = pAccumulatorAlgo;

        mHealToHotTickInterval = pHealToHotTickInterval;
        mHealToHotTickCount = pHealToHotTickCount;
        mHealToHotAmount = pHealToHotAmount;

        mAoeRange = pAoeRange;
        mAoeMaxTargets = pAoeMaxTargets;
        mAoeHealModifier = pAoeHealModifier;
    }

    @Override
    public SpellAffect getAffect() {
        final HealSpellAffect affect = HealSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(this.name());
        affect.setStackCount(mStackCount);
        affect.setTickCount(mTickCount);
        affect.setTickInterval(mTickInterval);

        affect.mDistanceAlgo = mDistanceAlgo;
        affect.mAccumulatorAlgo = mAccumulatorAlgo;

        affect.mHealToHotTickInterval = mHealToHotTickInterval;
        affect.mHealToHotTickCount = mHealToHotTickCount;
        affect.mHealToHotAmount = mHealToHotAmount;

        affect.mHealAmount = mHealAmount;
        affect.mHealIsPercentage = mHealIsPercentage;

        affect.mAoeRange = mAoeRange;
        affect.mAoeMaxTargets = mAoeMaxTargets;
        affect.mAoeHealModifier = mAoeHealModifier;

        return affect;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}
