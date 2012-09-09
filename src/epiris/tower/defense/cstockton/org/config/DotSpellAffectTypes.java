package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.spell.affect.DotSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;

public enum DotSpellAffectTypes implements ISpellAffects {
    POISON_ARCHER1(new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1 / 4)), 1f, 3, 1),
    POISON_ARCHER2(new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2 / 4)), 1f, 6, 3),
    POISON_ARCHER3(new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3 / 4)), 1f, 9, 6, TowerConstants.TOWER_AOE_RANGE_TIER_1, 8, .25f),
    POISON_ARCHER4(new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4 / 4)), 1f, 12, 9, TowerConstants.TOWER_AOE_RANGE_TIER_2, 16, .5f),
    POISON_ARCHER5(new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5 / 4)), 1f, 15, 12, TowerConstants.TOWER_AOE_RANGE_TIER_3, 24, 1f),

    FIRE_MAGE1(new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_1 / 4)), 1f, 3, 2),
    FIRE_MAGE2(new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2 / 4)), 1f, 6, 4),
    FIRE_MAGE3(new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3 / 4)), 1f, 9, 6, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_1, 8, 0f, null),
    FIRE_MAGE4(new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4 / 4)), 1f, 12, 8, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_2, 16, 0f, null),
    FIRE_MAGE5(new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5 / 4)), 1f, 15, 12, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_3, 24, 0f, null),

    EARTH_MAGE3(null, 1f, 12, 1, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_1, 255, 0f, null),
    EARTH_MAGE4(null, 1f, 16, 1, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_2, 255, 0f, null),
    EARTH_MAGE5(null, 1f, 24, 1, 0, 0, 0f, TowerConstants.TOWER_AOE_RANGE_TIER_3, 255, 0f, null),

    ARBITRARY(null, 1f, 10, 1000, 0, 0, 0f),

    ;

    static {
        FIRE_MAGE3.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.FIRE_MAGE3_AOE });
        FIRE_MAGE4.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.FIRE_MAGE4_AOE });
        FIRE_MAGE5.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.FIRE_MAGE5_AOE });

        EARTH_MAGE3.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.EARTH_MAGE3_AOE });
        EARTH_MAGE4.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.EARTH_MAGE4_AOE });
        EARTH_MAGE5.setTickCastSpellTypes(new SpellTypes[] { SpellTypes.EARTH_MAGE5_AOE });
    }

    public Damages mTickDamages;

    public final float mTickInterval;
    public final int mTickCount;
    public final int mStackCount;
    public final int mAoeRange;
    public final int mAoeMaxTargets;
    public final float mAoeDamageModifier;

    public final int mTickCastRange;
    public final int mTickCastMaxTargets;
    public final float mTickCastCastDelay;
    public SpellTypes[] mTickCastSpellTypes;

    DotSpellAffectTypes(
        final Damages pTickDamages, final float pTickInterval, final int pTickCount, final int pStackCount, final int pAoeRange, final int pAoeMaxTargets, final float pAoeDamageModifier,

        final int pTickCastRange,
        final int pTickCastMaxTargets,
        final float pTickCastCastDelay,
        SpellTypes[] pTickCastSpellTypes
    ) {
        mTickDamages = pTickDamages;
        mTickInterval = pTickInterval;
        mTickCount = pTickCount;
        mStackCount = pStackCount;
        mAoeRange = pAoeRange;
        mAoeMaxTargets = pAoeMaxTargets;
        mAoeDamageModifier = pAoeDamageModifier;
        mTickCastRange = pTickCastRange;
        mTickCastMaxTargets = pTickCastMaxTargets;
        mTickCastCastDelay = pTickCastCastDelay;
        mTickCastSpellTypes = pTickCastSpellTypes;
    }

    DotSpellAffectTypes(final Damages pTickDamages, final float pTickInterval, final int pTickCount, final int pStackCount, final int pAoeRange, final int pAoeMaxTargets, final float pAoeDamageModifier) {
        this(pTickDamages, pTickInterval, pTickCount, pStackCount, pAoeRange, pAoeMaxTargets, pAoeDamageModifier, 0, 0, 0f, null);
    }

    DotSpellAffectTypes(final Damages pTickDamages, final float pTickInterval, final int pTickCount, final int pStackCount) {
        this(pTickDamages, pTickInterval, pTickCount, pStackCount, 0, 0, 0f, 0, 0, 0f, null);
    }

    public void setTickCastSpellTypes(final SpellTypes[] pTickCastSpellTypes) {
        mTickCastSpellTypes = pTickCastSpellTypes;
    }

    @Override
    public SpellAffect getAffect() {
        final DotSpellAffect dot = DotSpellAffect.obtain();

        dot.setType(this);
        dot.setStackId(this.name());
        //dot.setDamage(mTickDamages, mAoeRange, mAoeMaxTargets, mAoeDamageModifier);
        dot.setStackCount(mStackCount);
        dot.setTickInterval(mTickInterval);
        dot.setTickCount(mTickCount);

        dot.mTickDamages = mTickDamages;
        dot.mAoeRange = mAoeRange;
        dot.mAoeMaxTargets = mAoeMaxTargets;
        dot.mAoeDamageModifier = mAoeDamageModifier;

        dot.mTickCastRange = mTickCastRange;
        dot.mTickCastMaxTargets = mTickCastMaxTargets;
        dot.mTickCastCastDelay = mTickCastCastDelay;
        dot.mTickCastSpellTypes = mTickCastSpellTypes;

        return dot;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}
