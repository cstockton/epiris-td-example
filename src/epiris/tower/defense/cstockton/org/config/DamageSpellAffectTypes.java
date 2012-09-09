package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.spell.affect.DamageSpellAffect;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.spell.affect.SpellAffect;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;

public enum DamageSpellAffectTypes implements ISpellAffects {

    TOWER_DAMAGE_TIER_1_BY_DIST(0f, 0, 0, DamageSpellAffect.DISTANCE_ALGO_BY_100, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1))),
    TOWER_DAMAGE_TIER_2_BY_DIST(0f, 0, 0, DamageSpellAffect.DISTANCE_ALGO_BY_100, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2))),
    TOWER_DAMAGE_TIER_3_BY_DIST(0f, 0, 0, DamageSpellAffect.DISTANCE_ALGO_BY_100, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, .25f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3))),
    TOWER_DAMAGE_TIER_4_BY_DIST(0f, 0, 0, DamageSpellAffect.DISTANCE_ALGO_BY_100, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, .5f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4))),
    TOWER_DAMAGE_TIER_5_BY_DIST(0f, 0, 0, DamageSpellAffect.DISTANCE_ALGO_BY_100, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 1f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5))),

    ACCUMULATING_MAGE1(6f, 1, 4, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_PLUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_1))),
    ACCUMULATING_MAGE2(8f, 1, 8, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_PLUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2))),
    ACCUMULATING_MAGE3(10f, 1, 12, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_PLUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3))),
    ACCUMULATING_MAGE4(12f, 1, 16, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_PLUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4))),
    ACCUMULATING_MAGE5(14f, 1, 20, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_PLUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5))),

    DIMINISHING_MAGE1(14f, 1, 20, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_MINUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2))),
    DIMINISHING_MAGE2(12f, 1, 16, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_MINUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3))),
    DIMINISHING_MAGE3(10f, 1, 12, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_MINUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4))),
    DIMINISHING_MAGE4(8f, 1, 8, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_MINUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5))),
    DIMINISHING_MAGE5(6f, 1, 4, DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_MINUS_DIV_10, 0f, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_6))),

    /*
    TOWER_PHYSICAL_DAMAGE_TIER_1(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1))),
    TOWER_PHYSICAL_DAMAGE_TIER_2(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2))),
    TOWER_PHYSICAL_DAMAGE_TIER_3(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3))),
    TOWER_PHYSICAL_DAMAGE_TIER_4(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4))),
    TOWER_PHYSICAL_DAMAGE_TIER_5(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5))),

    TOWER_MAGICAL_DAMAGE_TIER_1(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1))),
    TOWER_MAGICAL_DAMAGE_TIER_2(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2))),
    TOWER_MAGICAL_DAMAGE_TIER_3(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3))),
    TOWER_MAGICAL_DAMAGE_TIER_4(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4))),
    TOWER_MAGICAL_DAMAGE_TIER_5(DamageSpellAffect.DISTANCE_ALGO_NONE, DamageSpellAffect.ACCUMULATOR_ALGO_NONE, 0f, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5))),
    */

    ;
    public final float mTickInterval;
    public final int mTickCount;
    public final int mStackCount;

    private final Damages mDamages;
    private final int mDistanceAlgo;
    private final int mAccumulatorAlgo;
    private final float mDamageToDotAmount;

    DamageSpellAffectTypes(final float pTickInterval, final int pTickCount, final int pStackCount, final int pDistanceAlgo, final int pAccumulatorAlgo, final float pDamageToDotAmount, final Damages pDamages) {
        mTickInterval = pTickInterval;
        mTickCount = pTickCount;
        mStackCount = pStackCount;

        mDamages = pDamages;
        mDistanceAlgo = pDistanceAlgo;
        mAccumulatorAlgo = pAccumulatorAlgo;
        mDamageToDotAmount = pDamageToDotAmount;
    }

    @Override
    public SpellAffect getAffect() {
        final DamageSpellAffect affect = DamageSpellAffect.obtain();

        affect.setType(this);
        affect.setStackId(this.name());
        affect.setDamage(mDistanceAlgo, mAccumulatorAlgo, mDamageToDotAmount, mDamages);
        affect.setStackCount(mStackCount);
        affect.setTickCount(mTickCount);
        affect.setTickInterval(mTickInterval);

        return affect;
    }

    @Override
    public String getStackId() {
        return this.name();
    }
}
