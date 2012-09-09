package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.spell.affect.AoeCastSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;

public enum AoeCastSpellAffectTypes implements ISpellAffects {

    ARCHER3(TowerConstants.TOWER_AOE_RANGE_TIER_2, 2, 0f),
    ARCHER4(TowerConstants.TOWER_AOE_RANGE_TIER_3, 4, 0f),
    ARCHER5(TowerConstants.TOWER_AOE_RANGE_TIER_4, 8, 0f),

    MULTISHOT_ARCHER3(TowerConstants.TOWER_AOE_RANGE_TIER_2, 2, 0f),
    MULTISHOT_ARCHER4(TowerConstants.TOWER_AOE_RANGE_TIER_3, 4, 0f),
    MULTISHOT_ARCHER5(TowerConstants.TOWER_AOE_RANGE_TIER_4, 8, 0f),

    MAGE3(TowerConstants.TOWER_AOE_RANGE_TIER_2, 255, 0f),
    MAGE4(TowerConstants.TOWER_AOE_RANGE_TIER_3, 255, 0f),
    MAGE5(TowerConstants.TOWER_AOE_RANGE_TIER_4, 255, 0f),

    ;

    static {
        ARCHER3.setSpellTypes(new SpellTypes[] { SpellTypes.ARCHER3_AOE });
        ARCHER4.setSpellTypes(new SpellTypes[] { SpellTypes.ARCHER4_AOE });
        ARCHER5.setSpellTypes(new SpellTypes[] { SpellTypes.ARCHER5_AOE });

        MAGE3.setSpellTypes(new SpellTypes[] { SpellTypes.MAGE3_AOE });
        MAGE4.setSpellTypes(new SpellTypes[] { SpellTypes.MAGE4_AOE });
        MAGE5.setSpellTypes(new SpellTypes[] { SpellTypes.MAGE5_AOE });
    }

    private final int mRange;
    private final int mMaxTargets;
    private final float mCastDelay;
    private SpellTypes[] mSpellTypes;

    AoeCastSpellAffectTypes(final int pRange, final int pMaxTargets, final float pCastDelay) {
        mRange = pRange;
        mMaxTargets = pMaxTargets;
        mCastDelay = pCastDelay;
    }

    public void setSpellTypes(final SpellTypes[] pSpellTypes) {
        mSpellTypes = pSpellTypes;
    }

    @Override
    public SpellAffect getAffect() {
        final AoeCastSpellAffect aoe = AoeCastSpellAffect.obtain();

        aoe.setType(this);
        aoe.setStackId(this.name());
        aoe.mRange = mRange;
        aoe.mMaxTargets = mMaxTargets;
        aoe.mCastDelay = mCastDelay;
        aoe.mSpellTypes = mSpellTypes;

        return aoe;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}