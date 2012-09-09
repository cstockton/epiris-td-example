package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.creep.Creep;

public enum CreepSpellTypes {

    HEAL_10P_(
        6f, // cooldown
        0f, // cast delay
        0f, // target switch delay
        1, // max targets
        1200, // range
        Creep.CREEP_AI_TARGET_LOWEST, // ai
        SpellTypes.HEAL_OTHERS_SPELL_AFFECT // spell type
    ),

    HEAL_SPELL_AFFECT(
        3f, // cooldown
        0f, // cast delay
        0f, // target switch delay
        1, // max targets
        1200, // range
        Creep.CREEP_AI_TARGET_LOWEST, // ai
        SpellTypes.HEAL_OTHERS_SPELL_AFFECT // spell type
    ),

    HEAL_OTHERS_AFFECT(
        3f, // cooldown
        0f, // cast delay
        .50f, // target switch delay
        2, // max targets
        900, // range
        Creep.CREEP_AI_TARGET_LOWEST, // ai
        SpellTypes.HEAL_OTHERS_SPELL_AFFECT // spell type
    ),


    ;

    public final float mCooldown;
    public final float mCastDelay;
    public final float mTargetSwitchDelay;
    public final int mMaxTargets;
    public final int mRange;
    public final int mCreepAi;
    public final SpellTypes mSpellType;

    CreepSpellTypes(
        final float pCooldown,
        final float pCastDelay,
        final float pTargetSwitchDelay,
        final int pMaxTargets,
        final int pRange,
        final int pCreepAi,
        final SpellTypes pSpellType
    ) {
        mCooldown = pCooldown;
        mCastDelay = pCastDelay;
        mTargetSwitchDelay = pTargetSwitchDelay;

        mMaxTargets = pMaxTargets;
        mRange = pRange;

        mCreepAi = pCreepAi;
        mSpellType = pSpellType;
    }

    public float getCooldown() {
        return mCooldown;
    }

    public int getRange() {
        return mRange;
    }

    public float getCreepAi() {
        return mCreepAi;
    }

    public SpellTypes getSpellType() {
        return mSpellType;
    }
}