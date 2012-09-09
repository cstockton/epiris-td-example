package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.spell.affect.ChainCastSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;

public enum ChainCastSpellAffectTypes implements ISpellAffects {
//    MULTI_SHOT_DARK_MASTER_MARKSMAN_ARROW_CHAIN(TowerConstants.TOWER_RANGE_TIER_5, 1, 25, 0f, 0f, new SpellTypes[] { SpellTypes.MULTI_SHOT_DARK_MASTER_MARKSMAN_ARROW_SPLIT_ATTACK } ),

    ;

    private final int mRange;
    private final int mMaxTargets;
    private final int mMaxChains;
    private final float mCastTargetDelay;
    private final float mCastChainDelay;
    private final SpellTypes[] mSpellTypes;

    ChainCastSpellAffectTypes(final int pRange, final int pMaxTargets, final int pMaxChains, final float pCastTargetDelay, final float pCastChainDelay, final SpellTypes[] pSpellTypes) {
        mRange = pRange;
        mMaxTargets = pMaxTargets;
        mMaxChains = pMaxChains;
        mCastTargetDelay = pCastTargetDelay;
        mCastChainDelay = pCastChainDelay;
        mSpellTypes = pSpellTypes;
    }

    @Override
    public SpellAffect getAffect() {
        final ChainCastSpellAffect affect = ChainCastSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(this.name());
        affect.setStackCount(1);
        affect.setTickCount(0);
        affect.mRange = mRange;
        affect.mMaxTargets = mMaxTargets;
        affect.mMaxChains = mMaxChains;
        affect.mCastTargetDelay = mCastTargetDelay;
        affect.mCastChainDelay = mCastChainDelay;
        affect.mSpellTypes = mSpellTypes;

        return affect;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}