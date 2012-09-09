package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.effect.IEffects;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;

public enum SpellTypes {

    /****** TOWER REFS ******/

    /** PHYSICAL * ARCHER */
    ARCHER3_AOE(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_2S_25F }),
    ARCHER4_AOE(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_4S_25F }),
    ARCHER5_AOE(null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_6S_25F }),

    /** PHYSICAL * MULTISHOT ARCHER */
    MULTISHOT_ARCHER3_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2)), null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { }),
    MULTISHOT_ARCHER4_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3)), null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { }),
    MULTISHOT_ARCHER5_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4)), null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, 0f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { }),

    /** MAGICAL * MAGE */
    MAGE3_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, null, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_3S }),
    MAGE4_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, null, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_4S }),
    MAGE5_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, null, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_6S }),

    /** MAGICAL * EARTH MAGE */
    EARTH_MAGE3_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_4S }),
    EARTH_MAGE4_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_8S }),
    EARTH_MAGE5_AOE(null, null, TowerConstants.TOWER_VELOCITY_INSTANT, null, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_12S }),

    /** MAGICAL * FIRE MAGE */
    FIRE_MAGE3_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2)), null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_IMPACT, null),
    FIRE_MAGE4_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3)), null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_IMPACT, null),
    FIRE_MAGE5_AOE(0f, 0f, null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4)), null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_SMALL_S70_IMPACT, null),

    /****** PHYSICAL TOWER SPELLS ******/

    /** PHYSICAL * ARCHER */
    ARCHER1(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S70_IMPACT, new ISpellAffects[] { }),
    ARCHER2(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S70_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_2S_25F }),
    ARCHER3(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S70_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_4S_25F, AoeCastSpellAffectTypes.ARCHER3 }),
    ARCHER4(null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S70_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_6S_25F, AoeCastSpellAffectTypes.ARCHER4 }),
    ARCHER5(null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S70_IMPACT, new ISpellAffects[] { MovementSpellAffectTypes.SLOW_20P_8S_25F, AoeCastSpellAffectTypes.ARCHER5 }),

    /** PHYSICAL * TRUESHOT ARCHER */
    TRUESHOT_ARCHER1(null, null, TowerConstants.TOWER_VELOCITY_TIER_10, EffectTypes.SPELLS_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_1_BY_DIST }),
    TRUESHOT_ARCHER2(null, null, TowerConstants.TOWER_VELOCITY_TIER_11, EffectTypes.SPELLS_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_2_BY_DIST }),
    TRUESHOT_ARCHER3(null, null, TowerConstants.TOWER_VELOCITY_TIER_12, EffectTypes.SPELLS_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_3_BY_DIST }),
    TRUESHOT_ARCHER4(null, null, TowerConstants.TOWER_VELOCITY_TIER_13, EffectTypes.SPELLS_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_4_BY_DIST }),
    TRUESHOT_ARCHER5(null, null, TowerConstants.TOWER_VELOCITY_TIER_14, EffectTypes.SPELLS_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_5_BY_DIST }),

    /** PHYSICAL * MULTISHOT ARCHER */
    MULTISHOT_ARCHER1(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_1_BY_DIST }),
    MULTISHOT_ARCHER2(null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_2_BY_DIST }),
    MULTISHOT_ARCHER3(null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_3_BY_DIST }),
    MULTISHOT_ARCHER4(null, null, TowerConstants.TOWER_VELOCITY_TIER_6, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_4_BY_DIST }),
    MULTISHOT_ARCHER5(null, null, TowerConstants.TOWER_VELOCITY_TIER_7, EffectTypes.SPELLS_ARROW_S50_FLIGHT, null, .75f, EffectTypes.SPELLS_ARROW_S50_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.TOWER_DAMAGE_TIER_5_BY_DIST }),

    /** PHYSICAL * POISON ARCHER */
    POISON_ARCHER1(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_POISON_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_POISON_ARROW_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.POISON_ARCHER1 }),
    POISON_ARCHER2(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_POISON_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_POISON_ARROW_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.POISON_ARCHER2 }),
    POISON_ARCHER3(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_POISON_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_POISON_ARROW_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.POISON_ARCHER3 }),
    POISON_ARCHER4(null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_POISON_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_POISON_ARROW_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.POISON_ARCHER4 }),
    POISON_ARCHER5(null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_POISON_ARROW_S70_FLIGHT, null, .75f, EffectTypes.SPELLS_POISON_ARROW_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.POISON_ARCHER5 }),

    /** PHYSICAL * IRON ARCHER */
    IRON_ARCHER1(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_IRON_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_IRON_ARROW_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.STUN_2S }),
    IRON_ARCHER2(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_IRON_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_IRON_ARROW_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.STUN_4S  }),
    IRON_ARCHER3(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_IRON_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_IRON_ARROW_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.STUN_6S, MovementSpellAffectTypes.SLOW_30P_12S_25F }),
    IRON_ARCHER4(null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_IRON_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_IRON_ARROW_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.STUN_8S, MovementSpellAffectTypes.SLOW_30P_16S_25F }),
    IRON_ARCHER5(null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_IRON_ARROW_FLIGHT, null, .75f, EffectTypes.SPELLS_IRON_ARROW_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.STUN_12S, MovementSpellAffectTypes.SLOW_30P_24S_25F }),

    /****** MAGICAL TOWER SPELLS ******/

    /** MAGICAL * MAGE */
    MAGE1(null, null, TowerConstants.TOWER_VELOCITY_TIER_6, EffectTypes.SPELLS_YELLOW_BALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_YELLOW_BALL_S70_IMPACT, new ISpellAffects[] { }),
    MAGE2(null, null, TowerConstants.TOWER_VELOCITY_TIER_7, EffectTypes.SPELLS_YELLOW_BALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_YELLOW_BALL_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_4S }),
    MAGE3(null, null, TowerConstants.TOWER_VELOCITY_TIER_8, EffectTypes.SPELLS_YELLOW_BALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_YELLOW_BALL_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_6S, AoeCastSpellAffectTypes.MAGE3 }),
    MAGE4(null, null, TowerConstants.TOWER_VELOCITY_TIER_9, EffectTypes.SPELLS_YELLOW_BALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_YELLOW_BALL_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_8S, AoeCastSpellAffectTypes.MAGE4 }),
    MAGE5(null, null, TowerConstants.TOWER_VELOCITY_TIER_10, EffectTypes.SPELLS_YELLOW_BALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_YELLOW_BALL_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.SILENCE_12S, AoeCastSpellAffectTypes.MAGE5 }),

    /** MAGICAL * FIRE MAGE */
    FIRE_MAGE1(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.FIRE_MAGE1 }),
    FIRE_MAGE2(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.FIRE_MAGE2 }),
    FIRE_MAGE3(null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.FIRE_MAGE3 }),
    FIRE_MAGE4(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.FIRE_MAGE4 }),
    FIRE_MAGE5(null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_FIRE_BALL_LARGE_S70_IMPACT, new ISpellAffects[] { DotSpellAffectTypes.FIRE_MAGE5 }),

    /** MAGICAL * EARTH MAGE */
    EARTH_MAGE1(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_BOULDER_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_4S }),
    EARTH_MAGE2(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_BOULDER_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_8S }),
    EARTH_MAGE3(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_BOULDER_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_12S, DotSpellAffectTypes.EARTH_MAGE3 }),
    EARTH_MAGE4(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_BOULDER_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_16S, DotSpellAffectTypes.EARTH_MAGE4 }),
    EARTH_MAGE5(null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_BOULDER_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BOULDER_S70_IMPACT, new ISpellAffects[] { DisableSpellAffectTypes.ROOT_24S, DotSpellAffectTypes.EARTH_MAGE5 }),

    /** MAGICAL * ACCUMULATING MAGE */
    ACCUMULATING_MAGE1(null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT, null, 0, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.ACCUMULATING_MAGE1, MovementSpellAffectTypes.ACCUMULATING_BOOST_1 }),
    ACCUMULATING_MAGE2(null, null, TowerConstants.TOWER_VELOCITY_TIER_6, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.ACCUMULATING_MAGE2, MovementSpellAffectTypes.ACCUMULATING_BOOST_2 }),
    ACCUMULATING_MAGE3(null, null, TowerConstants.TOWER_VELOCITY_TIER_7, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.ACCUMULATING_MAGE3, MovementSpellAffectTypes.ACCUMULATING_BOOST_3 }),
    ACCUMULATING_MAGE4(null, null, TowerConstants.TOWER_VELOCITY_TIER_8, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.ACCUMULATING_MAGE4, MovementSpellAffectTypes.ACCUMULATING_BOOST_4 }),
    ACCUMULATING_MAGE5(null, null, TowerConstants.TOWER_VELOCITY_TIER_9, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.ACCUMULATING_MAGE5, MovementSpellAffectTypes.ACCUMULATING_BOOST_5 }),

    /** MAGICAL * DIMINISHING MAGE */
    DIMINISHING_MAGE1(null, null, TowerConstants.TOWER_VELOCITY_TIER_10, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT, null, 0, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.DIMINISHING_MAGE1, MovementSpellAffectTypes.DIMINISHING_SNARE_1 }),
    DIMINISHING_MAGE2(null, null, TowerConstants.TOWER_VELOCITY_TIER_11, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.DIMINISHING_MAGE2, MovementSpellAffectTypes.DIMINISHING_SNARE_2 }),
    DIMINISHING_MAGE3(null, null, TowerConstants.TOWER_VELOCITY_TIER_12, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.DIMINISHING_MAGE3, MovementSpellAffectTypes.DIMINISHING_SNARE_3 }),
    DIMINISHING_MAGE4(null, null, TowerConstants.TOWER_VELOCITY_TIER_13, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.DIMINISHING_MAGE4, MovementSpellAffectTypes.DIMINISHING_SNARE_4 }),
    DIMINISHING_MAGE5(null, null, TowerConstants.TOWER_VELOCITY_TIER_14, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT, null, 0f, EffectTypes.SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT, new ISpellAffects[] { DamageSpellAffectTypes.DIMINISHING_MAGE5, MovementSpellAffectTypes.DIMINISHING_SNARE_5 }),

    /****** CREEP SPELLS ******/

    HEAL_SPELL_AFFECT(0f, 0F, null, null, null, null, null, 100f, EffectTypes.SPELLS_GREEN_ENERGY_FLIGHT, null, 0f, EffectTypes.SPELLS_GREEN_ENERGY_IMPACT, new ISpellAffects[] { HealSpellAffectTypes.HEAL_50_HOT }),

    HEAL_OTHERS_SPELL_AFFECT(0f, 0F, null, null, null, null, null, 100f, EffectTypes.SPELLS_GREEN_ENERGY_FLIGHT, null, 0f, EffectTypes.SPELLS_GREEN_ENERGY_IMPACT, new ISpellAffects[] { HealSpellAffectTypes.HEAL_50_HOT }),


    ;

    private final float mQueueTime;
    private final float mVelocity;

    private final float mCastTime;
    private final float mImpactTime;

    public final Damages mCastDamages;
    public final Damages mFlightDamages;
    public final Damages mImpactDamages;

    private final IEffects mCastEffect;
    private final ISpellAffects[] mCastSpellAffects;

    private final IEffects mFlightEffect;
    private final ISpellAffects[] mFlightSpellAffects;

    private final IEffects mImpactEffect;
    private final ISpellAffects[] mImpactSpellAffects;

    SpellTypes(
        final IEffects pCastEffect,
        final ISpellAffects[] pCastSpellAffects,
        final float pVelocity,
        final IEffects pFlightEffect,
        final ISpellAffects[] pFlightSpellAffects,
        final float pImpactTime,
        final IEffects pImpactEffect,
        final ISpellAffects[] pImpactSpellAffects
    ) {
        this(0f, 0f, null, null, null, pCastEffect, pCastSpellAffects, pVelocity, pFlightEffect, pFlightSpellAffects, pImpactTime, pImpactEffect, pImpactSpellAffects);
    }

    SpellTypes(
        final float pQueueTime,
        final float pCastTime,

        final Damages pCastDamages,
        final Damages pFlightDamages,
        final Damages pImpactDamages,

        final IEffects pCastEffect,
        final ISpellAffects[] pCastSpellAffects,
        final float pVelocity,
        final IEffects pFlightEffect,
        final ISpellAffects[] pFlightSpellAffects,
        final float pImpactTime,
        final IEffects pImpactEffect,
        final ISpellAffects[] pImpactSpellAffects
    ) {
        mQueueTime = pQueueTime;
        mCastTime = pCastTime;

        mCastDamages = pCastDamages;
        mFlightDamages = pFlightDamages;
        mImpactDamages = pImpactDamages;

        mVelocity = pVelocity;
        mImpactTime = pImpactTime;

        mCastEffect = pCastEffect;
        mCastSpellAffects = pCastSpellAffects;

        mFlightEffect = pFlightEffect;
        mFlightSpellAffects = pFlightSpellAffects;

        mImpactEffect = pImpactEffect;
        mImpactSpellAffects = pImpactSpellAffects;
    }

    public float getQueueTime() {
        return mQueueTime;
    }

    public float getVelocity() {
        return mVelocity;
    }

    public float getCastTime() {
        return mCastTime;
    }

    public float getImpactTime() {
        return mImpactTime;
    }

    public IEffects getCastEffect() {
        return mCastEffect;
    }

    public ISpellAffects[] getCastSpellAffects() {
        return mCastSpellAffects;
    }

    public IEffects getFlightEffect() {
        return mFlightEffect;
    }

    public ISpellAffects[] getFlightSpellAffects() {
        return mFlightSpellAffects;
    }

    public IEffects getImpactEffect() {
        return mImpactEffect;
    }

    public ISpellAffects[] getImpactSpellAffects() {
        return mImpactSpellAffects;
    }
}







/*
package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.effect.IEffects;
import epiris.tower.defense.cstockton.org.spell.affect.ISpellAffects;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;
import epiris.tower.defense.cstockton.org.util.Debug;

public enum SpellTypes {

    ARCHER1(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_1, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),
    ARCHER2(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_2, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),

    ARCHER3(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),
    ARCHER3_AOE(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_3, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),

    ARCHER4(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),
    ARCHER4_AOE(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_4, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),

    ARCHER5(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),
    ARCHER5_AOE(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),

    HEAL_OTHERS_SPELL_AFFECT(0f, 0f, null, null, null, null, TowerConstants.TOWER_VELOCITY_TIER_5, EffectTypes.SPELLS_ARROW_FLIGHT, .75f, EffectTypes.SPELLS_ARROW_IMPACT),


    ;

    static {
        ARCHER2.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_2S_25F });
        ARCHER3.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_4S_25F, AoeCastSpellAffectTypes.ARCHER3 });
        ARCHER3_AOE.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_2S_25F });

        ARCHER4.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_6S_25F, AoeCastSpellAffectTypes.ARCHER4 });
        ARCHER4_AOE.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_4S_25F });

        ARCHER5.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_20P_8S_25F, AoeCastSpellAffectTypes.ARCHER5 });
        ARCHER5_AOE.setImpactSpellAffects(new ISpellAffects[] { MovementSpellAffectTypes.SLOW_15P_6S_25F });
    }

    private final float mQueueTime;
    private final float mVelocity;

    private final float mCastTime;
    private final float mImpactTime;

    public final Damages mCastDamages;
    public final Damages mFlightDamages;
    public final Damages mImpactDamages;

    private final IEffects mCastEffect;
    private ISpellAffects[] mCastSpellAffects;

    private final IEffects mFlightEffect;
    private ISpellAffects[] mFlightSpellAffects;

    private final IEffects mImpactEffect;
    private ISpellAffects[] mImpactSpellAffects;

    SpellTypes(
        final float pQueueTime,
        final float pCastTime,

        final Damages pCastDamages,
        final Damages pFlightDamages,
        final Damages pImpactDamages,

        final IEffects pCastEffect,
        //final ISpellAffects[] pCastSpellAffects,
        final float pVelocity,
        final IEffects pFlightEffect,
        //final ISpellAffects[] pFlightSpellAffects,
        final float pImpactTime,
        final IEffects pImpactEffect
        //final ISpellAffects[] pImpactSpellAffects
    ) {
        Debug.i("SpellTypes :: load :: " + this.name());

        mQueueTime = pQueueTime;
        mCastTime = pCastTime;

        mCastDamages = pCastDamages;
        mFlightDamages = pFlightDamages;
        mImpactDamages = pImpactDamages;

        mVelocity = pVelocity;
        mImpactTime = pImpactTime;

        mCastEffect = pCastEffect;
        //mCastSpellAffects = pCastSpellAffects;

        mFlightEffect = pFlightEffect;
        //mFlightSpellAffects = pFlightSpellAffects;

        mImpactEffect = pImpactEffect;
        //mImpactSpellAffects = pImpactSpellAffects;
    }

    public void setFlightSpellAffects(final ISpellAffects[] pFlightSpellAffects) {
        mFlightSpellAffects = pFlightSpellAffects;
    }

    public void setImpactSpellAffects(final ISpellAffects[] pImpactSpellAffects) {
        mImpactSpellAffects = pImpactSpellAffects;
    }

    public void setCastSpellAffects(final ISpellAffects[] pCastSpellAffects) {
        mCastSpellAffects = pCastSpellAffects;
    }

    public float getQueueTime() {
        return mQueueTime;
    }

    public float getVelocity() {
        return mVelocity;
    }

    public float getCastTime() {
        return mCastTime;
    }

    public float getImpactTime() {
        return mImpactTime;
    }

    public IEffects getCastEffect() {
        return mCastEffect;
    }

    public ISpellAffects[] getCastSpellAffects() {
        return mCastSpellAffects;
    }

    public IEffects getFlightEffect() {
        return mFlightEffect;
    }

    public ISpellAffects[] getFlightSpellAffects() {
        return mFlightSpellAffects;
    }

    public IEffects getImpactEffect() {
        return mImpactEffect;
    }

    public ISpellAffects[] getImpactSpellAffects() {
        return mImpactSpellAffects;
    }
}
*/