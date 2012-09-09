package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.damage.Damage;
import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.tower.ITowerAi;
import epiris.tower.defense.cstockton.org.tower.ITowerSprites;
import epiris.tower.defense.cstockton.org.tower.TowerBasicCasterAi;
import epiris.tower.defense.cstockton.org.tower.TowerConstants;
import epiris.tower.defense.cstockton.org.tower.TowerMultiCasterAi;

public enum TowerTypes {

    SUPER_TOWER(
        // name / desc
        "Super Archer Tower",
        "This tower is occupied by a archer. He strikes with extreme accuracy and is most devastating verse targets with little to no armor.",

        // reward req / cost / range / speed
        null, 10, 600, .5f,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, 100000),new Damage(DamageTypes.MAGICAL, 100000)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower base sprite / red / green / blue / alpha / scale
        null, 1f, 1f, 1f, 1f, 1f,

        // tower UI sprite / red / green / blue / alpha
        null, 1f, 1f, 1f, 1f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER5)
    ),

    /*********************************************** PHYSICAL **** ARCHER TREE ***********************************************/
    ARCHER1(
        // name / desc
        "Archer Tower",
        "This tower is occupied by a archer. He strikes with extreme accuracy and are most devastating verse targets with little" +
        " to no physical armor.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER1)
    ),

    ARCHER2(
        // name / desc
        "Elven Archer",
        "This tower is occupied by a elven archer. He strikes with extreme accuracy and are most devastating verse targets with little" +
        " to no physical armor. The attacks from this tower leave enemies 15% slower for 2 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER2)
    ),

    ARCHER3(
        // name / desc
        "Ranger Tower",
        "This tower is occupied by a ranger. He strikes with extreme accuracy and are most devastating verse targets with little" +
        " to no physical armor. The attacks from this tower leave enemies 15% slower for 4 seconds, the arrow also splits on impact" +
        " striking up to 2 nearby units leaving them snared for 2 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER3)
    ),

    ARCHER4(
        // name / desc
        "Elven Ranger Tower",
        "This tower is occupied by a elven ranger. He strikes with extreme accuracy and are most devastating verse targets with little" +
        " to no physical armor. The attacks from this tower leave enemies 15% slower for 6 seconds, the arrow also splits on impact" +
        " striking up to 4 nearby units leaving them snared for 3 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER4)
    ),

    ARCHER5(
        // name / desc
        "Dark Elven Ranger Tower",
        "This tower is occupied by a dark elven ranger. These rangers although interested in dark magic remain righteous. He strikes" +
        " with extreme accuracy and are most devastating verse targets with little" +
        " to no physical armor. The attacks from this tower leave enemies 20% slower for 8 seconds, the arrow also splits on impact" +
        " striking up to 8 nearby units leaving them snared for 4 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ARCHER5, 1f, 1f, 1f, .8f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ARCHER5)
    ),

    /*********************************************** PHYSICAL **** TRUESHOT ARCHER TREE ***********************************************/
    TRUESHOT_ARCHER1(
        // name / desc
        "Trueshot Archer Tower",
        "This tower is occupied by a archer specializing in long range attacks, the further the arrow travels the more devastating the damage.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, (int) (TowerConstants.TOWER_RANGE_TIER_2 * 1.5f), TowerConstants.TOWER_SPEED_TIER_1 * 4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.TRUESHOT_ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.TRUESHOT_ARCHER1)
    ),

    TRUESHOT_ARCHER2(
        // name / desc
        "Trueshot Elven Archer Tower",
        "This tower is occupied by a elven archer specializing in long range attacks, the further the arrow travels the more devastating the damage.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, (int) (TowerConstants.TOWER_RANGE_TIER_3 * 1.5f), TowerConstants.TOWER_SPEED_TIER_2 * 4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.TRUESHOT_ARCHER2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.TRUESHOT_ARCHER2)
    ),

    TRUESHOT_ARCHER3(
        // name / desc
        "Trueshot Ranger Tower",
        "This tower is occupied by a ranger specializing in long range attacks, the further the arrow travels the more devastating the damage. These" +
        " attacks are so powerful that the enemy will take 1/4th the initial damage caused over the next several seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, (int) (TowerConstants.TOWER_RANGE_TIER_4 * 1.5f), TowerConstants.TOWER_SPEED_TIER_3 * 4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.TRUESHOT_ARCHER3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.TRUESHOT_ARCHER3)
    ),

    TRUESHOT_ARCHER4(
        // name / desc
        "Trueshot Elven Ranger Tower",
        "This tower is occupied by a elven ranger specializing in long range attacks, the further the arrow travels the more devastating the damage. These" +
        " attacks are so powerful that the enemy will take half the initial damage caused over the next several seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, (int) (TowerConstants.TOWER_RANGE_TIER_5 * 1.5f), TowerConstants.TOWER_SPEED_TIER_4 * 4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.TRUESHOT_ARCHER4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.TRUESHOT_ARCHER4)
    ),

    TRUESHOT_ARCHER5(
        // name / desc
        "Trueshot Dark Ranger Tower",
        "This tower is occupied by a dark ranger specializing in long range attacks, the further the arrow travels the more devastating the damage. These" +
        " attacks are so powerful that the enemy will take all of the initial damage caused over the next several seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, (int) (TowerConstants.TOWER_RANGE_TIER_6 * 1.5f), TowerConstants.TOWER_SPEED_TIER_5 * 4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.TRUESHOT_ARCHER5, 1f, 1f, 1f, .8f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.TRUESHOT_ARCHER5)
    ),

    /*********************************************** PHYSICAL **** MULTI-SHOT TREE ***********************************************/
    MULTISHOT_ARCHER1(
        // name / desc
        "Multi-Shot Archer Tower",
        "This tower is occupied by 2 archers working together to crush their enemies.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MULTISHOT_ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerMultiCasterAi(2, .60f).addSpell(SpellTypes.MULTISHOT_ARCHER1)
    ),

    MULTISHOT_ARCHER2(
        // name / desc
        "Multi-Shot Elven Archer Tower",
        "This tower is occupied by 4 elven archers working together to crush their enemies.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MULTISHOT_ARCHER2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerMultiCasterAi(4, .50f).addSpell(SpellTypes.MULTISHOT_ARCHER2)
    ),

    MULTISHOT_ARCHER3(
        // name / desc
        "Multi-Shot Ranger Tower",
        "This tower is occupied by 6 rangers working together to crush their enemies. Each attack done by this arrow will split into 2 additional, less devastating arrows.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MULTISHOT_ARCHER3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerMultiCasterAi(6, .40f).addSpell(SpellTypes.MULTISHOT_ARCHER3)
    ),

    MULTISHOT_ARCHER4(
        // name / desc
        "Multi-Shot Elven Ranger Tower",
        "This tower is occupied by 8 elven rangers working together to crush their enemies. Each attack done by this arrow will split into 4 additional, less devastating arrows.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MULTISHOT_ARCHER4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerMultiCasterAi(8, .30f).addSpell(SpellTypes.MULTISHOT_ARCHER4)
    ),

    MULTISHOT_ARCHER5(
        // name / desc
        "Multi-Shot Dark Ranger Tower",
        "This tower is occupied by 12 dark rangers working together to crush their enemies. Each attack done by this arrow will split into 8 additional, less devastating arrows.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MULTISHOT_ARCHER5, 1f, 1f, 1f, .8f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerMultiCasterAi(12, .20f).addSpell(SpellTypes.MULTISHOT_ARCHER5)

    ),

    /*********************************************** PHYSICAL **** POISON TREE ***********************************************/
    POISON_ARCHER1(
        // name / desc
        "Poison Archer Tower",
        "This tower is occupied by a archer specializing in the use of chemicals and poisons. Each arrow is dipped in a deadly brew which" +
        " causes 1/4th the initial damage dealt each second over the course of the next 3 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.POISON_ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.POISON_ARCHER1)
    ),

    POISON_ARCHER2(
        // name / desc
        "Poison Elven Archer",
        "This tower is occupied by a elven archer specializing in the use of chemicals and poisons. Each arrow is dipped in a deadly brew which" +
        " causes 1/4th the initial damage dealt each second over the course of the next 6 seconds, this affect will stack up to 3 times.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.POISON_ARCHER2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.POISON_ARCHER2)
    ),

    POISON_ARCHER3(
        // name / desc
        "Poison Ranger",
        "This tower is occupied by a ranger specializing in the use of chemicals and poisons. Each arrow is dipped in a deadly brew which" +
        " causes 1/4th the initial damage dealt each second over the course of the next 9 seconds, this affect will stack up to 6 times. In" +
        " addition the fumes from this attack are enough to cause 1/4th of the DoT damage to nearby enemies each second.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.POISON_ARCHER3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.POISON_ARCHER3)
    ),

    POISON_ARCHER4(
        // name / desc
        "Poison Elven Ranger",
        "This tower is occupied by a elven ranger specializing in the use of chemicals and poisons. Each arrow is dipped in a deadly brew which" +
        " causes 1/4th the initial damage dealt each second over the course of the next 12 seconds, this affect will stack up to 9 times. In" +
        " addition the fumes from this attack are enough to cause half of the DoT damage to nearby enemies each second.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.POISON_ARCHER4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.POISON_ARCHER4)
    ),

    POISON_ARCHER5(
        // name / desc
        "Dark Elven Ranger",
        "This tower is occupied by a dark ranger specializing in the use of chemicals and poisons. Each arrow is dipped in a deadly brew which" +
        " causes 1/4th the initial damage dealt each second over the course of the next 15 seconds, this affect will stack up to 12 times. In" +
        " addition the fumes from this attack are enough to cause all of the DoT damage to nearby enemies each second.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.POISON_ARCHER5, 1f, 1f, 1f, .8f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.POISON_ARCHER5)

    ),

    /*********************************************** PHYSICAL **** IRON TREE ***********************************************/
    IRON_ARCHER1(
        // name / desc
        "Iron Archer Tower",
        "This tower is occupied by a giant archer who fires iron arrows, these attacks leave the target stunned for 2 seconds, unable to cast spells or move.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.IRON_ARCHER1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.IRON_ARCHER1)
    ),

    IRON_ARCHER2(
        // name / desc
        "Iron Elven Archer Tower",
        "This tower is occupied by a giant elven archer who fires iron arrows, these attacks leave the target stunned for 4 seconds, unable to cast spells or move.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.IRON_ARCHER2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.IRON_ARCHER2)
    ),

    IRON_ARCHER3(
        // name / desc
        "Iron Ranger Tower",
        "This tower is occupied by a giant ranger who fires iron arrows, these attacks leave the target stunned for 6 seconds, unable to cast spells or move. In addition" +
        " the enemy is struck with such force, they are left disorientated and slowed 30% for the next 12 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.IRON_ARCHER3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.IRON_ARCHER3)
    ),

    IRON_ARCHER4(
        // name / desc
        "Iron Elven Ranger Tower",
        "This tower is occupied by a giant elven ranger who fires iron arrows, these attacks leave the target stunned for 8 seconds, unable to cast spells or move. In addition" +
        " the enemy is struck with such force, they are left disorientated and slowed 30% for the next 16 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.IRON_ARCHER4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.IRON_ARCHER4)
    ),

    IRON_ARCHER5(
        // name / desc
        "Iron Dark Ranger Tower",
        "This tower is occupied by a giant dark ranger who fires iron arrows, these attacks leave the target stunned for 12 seconds, unable to cast spells or move. In addition" +
        " the enemy is struck with such force, they are left disorientated and slowed 30% for the next 24 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.PHYSICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.IRON_ARCHER5, 1f, 1f, 1f, .8f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.IRON_ARCHER5)

    ),

    /*********************************************** MAGICAL - MAGE TREE ***********************************************/
    MAGE1(
        // name / desc
        "Mage Tower",
        "This tower is occupied by a single mage. He uses his magical power to conjure primitive but effective offensive strikes most effective against armored targets.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MAGE1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1 * 0.8f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.MAGE1)
    ),

    MAGE2(
        // name / desc
        "Wizard Tower",
        "This tower is occupied by a single wizard. He uses his adept magical power to conjure powerful offensive strikes most effective against armored targets, leaving the enemy silenced for 4 seconds upon impact.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MAGE2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2 * 0.8f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.MAGE2)
    ),

    MAGE3(
        // name / desc
        "Master Wizard Tower",
        "This tower is occupied by a single master wizard. He uses masterful control of magical power to launch powerful offensive strikes most effective against armored targets, leaving the enemy silenced for 6 seconds..",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MAGE3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3 * 0.8f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.MAGE3)
    ),

    MAGE4(
        // name / desc
        "Archmage Tower",
        "This tower is occupied by a powerful archmage. He uses powerful magic only a lifetime of practice and dedication can create, leaving the primary target silenced for 8 seconds, also silencing all nearby units for 4 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MAGE4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4 * 0.8f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.MAGE4)
    ),

    MAGE5(
        // name / desc
        "White Archmage Tower",
        "This tower is occupied by a powerful archmage. He uses powerful magic only a hundred lifetimes of practice and dedication can create, his attacks silence the pimary target for 12 seconds, as well as 6 seconds to all nearby units.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.MAGE5, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_5 * 0.8f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.MAGE5)
    ),

    /*********************************************** MAGICAL - FIRE MAGE TREE ***********************************************/
    FIRE_MAGE1(
        // name / desc
        "Fire Mage Tower",
        "This tower is occupied by a single fire mage. The attacks affect damage all targets in a small AoE, the main target is left with a 9 second DoT, stacking up to 2 times.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.FIRE_MAGE1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.FIRE_MAGE1)
    ),

    FIRE_MAGE2(
        // name / desc
        "Wizard Tower",
        "This tower is occupied by a single wizard. He uses his adept magical power to conjure powerful offensive strikes most effective against armored targets.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.FIRE_MAGE2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.FIRE_MAGE2)
    ),

    FIRE_MAGE3(
        // name / desc
        "Master Wizard Tower",
        "This tower is occupied by a single master wizard. He uses masterful control of magical power to launch powerful offensive strikes, the pain is so intense it leaves enemies with impaired movement. most effective against armored targets.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.FIRE_MAGE3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.FIRE_MAGE3)
    ),

    FIRE_MAGE4(
        // name / desc
        "Archmage Tower",
        "This tower is occupied by a powerful archmage. He uses powerful magic only a lifetime of practice and dedication can create, the pain is so intense it leaves enemies with impaired movement. He is most effective against armored targets.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.FIRE_MAGE4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.FIRE_MAGE4)
    ),

    FIRE_MAGE5(
        // name / desc
        "White Archmage Tower",
        "This tower is occupied by a powerful white archmage. He uses powerful magic only a hundred lifetime of practice and dedication can create, the pain is so intense it leaves enemies with impaired movement. He is most effective against armored targets.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.FIRE_MAGE5, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.FIRE_MAGE5)
    ),


    /*********************************************** MAGICAL - FIRE MAGE TREE ***********************************************/
    EARTH_MAGE1(
        // name / desc
        "Earth Mage Tower",
        "This mage specializes in earth magic, conjuring large vine infested boulders to hurl at enemies. Each enemy struck will be rooted in place for 4 seconds and take " +
        TowerConstants.TOWER_DAMAGE_TIER_1 + " magical damage.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_1)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.EARTH_MAGE1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.EARTH_MAGE1)
    ),

    EARTH_MAGE2(
        // name / desc
        "Earth Wizard Tower",
        "This wizard specializes in refined earth magic, conjuring large vine infested boulders to hurl at enemies. Each enemy struck will be rooted in place for 8 seconds and take " +
        TowerConstants.TOWER_DAMAGE_TIER_2 + " magical damage.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_2)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.EARTH_MAGE2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.EARTH_MAGE2)
    ),

    EARTH_MAGE3(
        // name / desc
        "Earth Master Wizard Tower",
        "This master wizard specializes in powerful earth magic, conjuring large vine infested boulders to hurl at enemies. Each enemy struck will be rooted in place for 12 seconds and take " +
        TowerConstants.TOWER_DAMAGE_TIER_3 + " magical damage. For the following 12 seconds, each nearby enemy who comes to close will also be rooted in place for 4 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_3)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.EARTH_MAGE3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.EARTH_MAGE3)
    ),

    EARTH_MAGE4(
        // name / desc
        "Earth Archmage Wizard Tower",
        "This archmage specializes in vicious earth magic, conjuring large vine infested boulders to hurl at enemies. Each enemy struck will be rooted in place for 16 seconds and take " +
        TowerConstants.TOWER_DAMAGE_TIER_4 + " magical damage. For the following 16 seconds, each nearby enemy who comes to close will also be rooted in place for 8 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_4)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.EARTH_MAGE4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.EARTH_MAGE4)
    ),

    EARTH_MAGE5(
        // name / desc
        "Earth White Archmage Wizard Tower",
        "This white archmage specializes in ruthless earth magic, conjuring large vine infested boulders to hurl at enemies. Each enemy struck will be rooted in place for 24 seconds and take " +
        TowerConstants.TOWER_DAMAGE_TIER_5 + " magical damage. For the following 24 seconds, each nearby enemy who comes to close will also be rooted in place for 12 seconds.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, new Damages(new Damage(DamageTypes.MAGICAL, TowerConstants.TOWER_DAMAGE_TIER_5)),

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.EARTH_MAGE5, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.EARTH_MAGE5)
    ),

    /*********************************************** MAGICAL - ACCUMULATING MAGE TREE ***********************************************/
    ACCUMULATING_MAGE1(
        // name / desc
        "Accumulating Mage Tower",
        "This tower contains a mage whom uses a specialized accumulating arcane magic which grows in power as it manifests within a target, this will cause enemies to flee for there life," +
        " boosting speed by 10%. Each enemy struck will take magic damage based on the the total amount of times he has recently been struck by accumulating towers, which is" +
        " capped at a maximun of 4 stacks. After 6 seconds of not being struck the focused accumulation will cease.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_6,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ACCUMULATING_MAGE_1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ACCUMULATING_MAGE1)
    ),

    ACCUMULATING_MAGE2(
        // name / desc
        "Accumulating Wizard Tower",
        "This tower contains a wizard whom uses a specialized accumulating arcane magic which grows in power as it manifests within a target, this will cause enemies to flee for there life," +
        " boosting speed by 15%. Each enemy struck will take magic damage based on the the total amount of times he has recently been struck by accumulating towers, which is" +
        " capped at a maximun of 8 stacks. After 8 seconds of not being struck the focused accumulation will cease.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ACCUMULATING_MAGE_2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ACCUMULATING_MAGE2)
    ),

    ACCUMULATING_MAGE3(
        // name / desc
        "Accumulating Master Wizard Tower",
        "This tower contains a master wizard whom uses a specialized accumulating arcane magic which grows in power as it manifests within a target, this will cause enemies to flee for there life," +
        " boosting speed by 20%. Each enemy struck will take magic damage based on the the total amount of times he has recently been struck by accumulating towers, which is" +
        " capped at a maximun of 12 stacks. After 10 seconds of not being struck the focused accumulation will cease.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ACCUMULATING_MAGE_3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ACCUMULATING_MAGE3)
    ),

    ACCUMULATING_MAGE4(
        // name / desc
        "Accumulating Archmage Tower",
        "This tower contains a archmage whom uses a specialized accumulating arcane magic which grows in power as it manifests within a target, this will cause enemies to flee for there life," +
        " boosting speed by 25%. Each enemy struck will take magic damage based on the the total amount of times he has recently been struck by accumulating towers, which is" +
        " capped at a maximun of 16 stacks. After 12 seconds of not being struck the focused accumulation will cease.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ACCUMULATING_MAGE_4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ACCUMULATING_MAGE4)
    ),

    ACCUMULATING_MAGE5(
        // name / desc
        "Accumulating White Archmage Tower",
        "This tower contains a white archmage whom uses a specialized accumulating arcane magic which grows in power as it manifests within a target, this will cause enemies to flee for there life," +
        " boosting speed by 30%. Each enemy struck will take magic damage based on the the total amount of times he has recently been struck by accumulating towers, which is" +
        " capped at a maximun of 20 stacks. After 14 seconds of not being struck the focused accumulation will cease.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.ACCUMULATING_MAGE_5, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.ACCUMULATING_MAGE5)
    ),


    /*********************************************** MAGICAL - ACCUMULATING MAGE TREE ***********************************************/
    DIMINISHING_MAGE1(
        // name / desc
        "Diminishing Mage Tower",
        "This tower contains a mage whom uses a specialized diminishing arcane magic which dwindles in power as it manifests within a target. Enemies tend to ignore" +
        " this tower after being struck, knowing it's wraith will lighten they slow by 10%. Each enemy struck will take magic damage based on the the total amount of" +
        " times he has recently been struck by accumulating towers, which is capped at a maximun of 20 stacks. After 14 seconds of not being struck the mage may re-focus.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_1, TowerConstants.TOWER_RANGE_TIER_1, TowerConstants.TOWER_SPEED_TIER_1,

        // DAMAGES: on cast / on flight / on impact
        null, null,null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.DIMINISHING_MAGE_1, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_1,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.DIMINISHING_MAGE1)
    ),

    DIMINISHING_MAGE2(
        // name / desc
        "Diminishing Wizard Tower",
        "This tower contains a wizard whom uses a specialized diminishing arcane magic which dwindles in power as it manifests within a target. Enemies tend to ignore" +
        " this tower after being struck, knowing it's wraith will lighten they slow by 15%. Each enemy struck will take magic damage based on the the total amount of" +
        " times he has recently been struck by accumulating towers, which is capped at a maximun of 16 stacks. After 12 seconds of not being struck the wizard may re-focus.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_2, TowerConstants.TOWER_RANGE_TIER_2, TowerConstants.TOWER_SPEED_TIER_2,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.DIMINISHING_MAGE_2, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_2,

        // tower base sprite / red / green / blue / alpha / scale
        null, 1f, 1f, 1f, 1f, 1f,

        // tower UI sprite / red / green / blue / alpha
        null, 1f, 1f, 1f, 1f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.DIMINISHING_MAGE2)
    ),

    DIMINISHING_MAGE3(
        // name / desc
        "Diminishing Master Wizard Tower",
        "This tower contains a master wizard whom uses a specialized diminishing arcane magic which dwindles in power as it manifests within a target. Enemies tend to ignore" +
        " this tower after being struck, knowing it's wraith will lighten they slow by 20%. Each enemy struck will take magic damage based on the the total amount of" +
        " times he has recently been struck by accumulating towers, which is capped at a maximun of 12 stacks. After 10 seconds of not being struck the master wizard may re-focus.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_3, TowerConstants.TOWER_RANGE_TIER_3, TowerConstants.TOWER_SPEED_TIER_3,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.DIMINISHING_MAGE_3, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_3,

        // tower base sprite / red / green / blue / alpha / scale
        null, 1f, 1f, 1f, 1f, 1f,

        // tower UI sprite / red / green / blue / alpha
        null, 1f, 1f, 1f, 1f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.DIMINISHING_MAGE3)
    ),

    DIMINISHING_MAGE4(
        // name / desc
        "Diminishing Archmage Tower",
        "This tower contains a archmage whom uses a specialized diminishing arcane magic which dwindles in power as it manifests within a target. Enemies tend to ignore" +
        " this tower after being struck, knowing it's wraith will lighten they slow by 25%. Each enemy struck will take magic damage based on the the total amount of" +
        " times he has recently been struck by accumulating towers, which is capped at a maximun of 8 stacks. After 8 seconds of not being struck the archmage may re-focus.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_4, TowerConstants.TOWER_RANGE_TIER_4, TowerConstants.TOWER_SPEED_TIER_4,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.DIMINISHING_MAGE_4, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_4,

        // tower base sprite / red / green / blue / alpha / scale
        null, 1f, 1f, 1f, 1f, 1f,

        // tower UI sprite / red / green / blue / alpha
        null, 1f, 1f, 1f, 1f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.DIMINISHING_MAGE4)
    ),

    DIMINISHING_MAGE5(
        // name / desc
        "Diminishing White Archmage Tower",
        "This tower contains a white archmage whom uses a specialized diminishing arcane magic which dwindles in power as it manifests within a target. Enemies tend to ignore" +
        " this tower after being struck, knowing it's wraith will lighten they slow by 30%. Each enemy struck will take magic damage based on the the total amount of" +
        " times he has recently been struck by accumulating towers, which is capped at a maximun of 4 stacks. After 6 seconds of not being struck the white archmage may re-focus.",

        // reward req / cost / range / speed
        null, TowerConstants.TOWER_GENERAL_COST_TIER_5, TowerConstants.TOWER_RANGE_TIER_5, TowerConstants.TOWER_SPEED_TIER_5,

        // DAMAGES: on cast / on flight / on impact
        null, null, null,

        // tile center X Y / cast center X Y
        64f, 112f, 64f, 32f,

        // tower top sprite / red / green / blue / alpha / scale
        TowerSprites.DIMINISHING_MAGE_5, 1f, 1f, 1f, 1f, TowerConstants.TOWER_SCALE_TIER_5,

        // tower base sprite / red / green / blue / alpha / scale
        null, 1f, 1f, 1f, 1f, 1f,

        // tower UI sprite / red / green / blue / alpha
        null, 1f, 1f, 1f, 1f,

        // tower AI
        new TowerBasicCasterAi().addSpell(SpellTypes.DIMINISHING_MAGE5)
    ),

    ;

    //static public int TIER1COST = 25;

    //static public TowerTypes sStarterTowerFirstTree = TowerTypes.MAGE_FIREBALL_TOWER;
    static public TowerTypes sStarterTowerFirstTree = TowerTypes.ARCHER1;
    static public TowerTypes sStarterTowerSecondTree = TowerTypes.MAGE1;
    static public TowerTypes sStarterTowerThirdTree = TowerTypes.SUPER_TOWER;

    public static TowerTypes findByName(final String pName){
        for(final TowerTypes t : values()){
            if(t.name().equals(pName)){
                return t;
            }
        }
        return null;
    }

    public final String mName;
    public final String mDescription;

    private final PlayerRewardTypes mPlayerRewardType;

    private final int mCost;
    private final int mRefund;

    private final int mRange;
    private final float mSpeed;

    private final Damages mCastDamages;
    private final Damages mFlightDamages;
    private final Damages mImpactDamages;

    private final float mTowerTileCenterX;
    private final float mTowerTileCenterY;

    private final float mTowerCastCenterX;
    private final float mTowerCastCenterY;

    private final ITowerSprites mTowerTopSprite;
    private final float mTowerTopSpriteRed;
    private final float mTowerTopSpriteGreen;
    private final float mTowerTopSpriteBlue;
    private final float mTowerTopSpriteAlpha;
    private final float mTowerTopSpriteScale;

    private final ITowerSprites mTowerBaseSprite;
    private final float mTowerBaseSpriteRed;
    private final float mTowerBaseSpriteGreen;
    private final float mTowerBaseSpriteBlue;
    private final float mTowerBaseSpriteAlpha;
    private final float mTowerBaseSpriteScale;

    private final UiLargeSprites mUiSprite;
    private final float mUiSpriteRed;
    private final float mUiSpriteGreen;
    private final float mUiSpriteBlue;
    private final float mUiSpriteAlpha;

    private final ITowerAi mTowerAi;

    TowerTypes(
            final String pName,
            final String pDescription,

            final PlayerRewardTypes pPlayerRewardType,

            final int pCost,

            final int pRange,
            final float pSpeed,

            final Damages pCastDamages,
            final Damages pFlightDamages,
            final Damages pImpactDamages,

            final float pTowerTileCenterX,
            final float pTowerTileCenterY,
            final float pTowerCastCenterX,
            final float pTowerCastCenterY,

            final ITowerSprites pTowerTopSprite,
            final float pTowerTopSpriteRed,
            final float pTowerTopSpriteGreen,
            final float pTowerTopSpriteBlue,
            final float pTowerTopSpriteAlpha,
            final float pTowerTopSpriteScale,

            final ITowerAi pTowerAi
        ) {
        this(
                pName,
                pDescription,
                pPlayerRewardType,

                pCost,

                pRange,
                pSpeed,

                pCastDamages,
                pFlightDamages,
                pImpactDamages,

                pTowerTileCenterX,
                pTowerTileCenterY,
                pTowerCastCenterX,
                pTowerCastCenterY,

                pTowerTopSprite,
                pTowerTopSpriteRed,
                pTowerTopSpriteGreen,
                pTowerTopSpriteBlue,
                pTowerTopSpriteAlpha,
                pTowerTopSpriteScale,

                null,
                1f,
                1f,
                1f,
                1f,
                1f,

                null,
                1f,
                1f,
                1f,
                1f,

                pTowerAi
        );
    }

    TowerTypes(
            final String pName,
            final String pDescription,

            final PlayerRewardTypes pPlayerRewardType,

            final int pCost,

            final int pRange,
            final float pSpeed,

            final Damages pCastDamages,
            final Damages pFlightDamages,
            final Damages pImpactDamages,

            final float pTowerTileCenterX,
            final float pTowerTileCenterY,
            final float pTowerCastCenterX,
            final float pTowerCastCenterY,

            final ITowerSprites pTowerTopSprite,
            final float pTowerTopSpriteRed,
            final float pTowerTopSpriteGreen,
            final float pTowerTopSpriteBlue,
            final float pTowerTopSpriteAlpha,
            final float pTowerTopSpriteScale,

            final ITowerSprites pTowerBaseSprite,
            final float pTowerBaseSpriteRed,
            final float pTowerBaseSpriteGreen,
            final float pTowerBaseSpriteBlue,
            final float pTowerBaseSpriteAlpha,
            final float pTowerBaseSpriteScale,

            final UiLargeSprites pUiSprite,
            final float pUiSpriteRed,
            final float pUiSpriteGreen,
            final float pUiSpriteBlue,
            final float pUiSpriteAlpha,

            final ITowerAi pTowerAi
            ) {

        mName = pName;
        mDescription = pDescription;

        mPlayerRewardType = pPlayerRewardType;

        mCost = pCost;
        mRefund = (int) Math.floor(pCost / 2);

        mRange = pRange;
        mSpeed = pSpeed;

        mCastDamages = pCastDamages;
        mFlightDamages = pFlightDamages;
        mImpactDamages = pImpactDamages;

        mTowerCastCenterX = pTowerCastCenterX;
        mTowerCastCenterY = pTowerCastCenterY;
        mTowerTileCenterX = pTowerTileCenterX;
        mTowerTileCenterY = pTowerTileCenterY;

        mTowerTopSprite = pTowerTopSprite;
        mTowerTopSpriteRed = pTowerTopSpriteRed;
        mTowerTopSpriteGreen = pTowerTopSpriteGreen;
        mTowerTopSpriteBlue = pTowerTopSpriteBlue;
        mTowerTopSpriteAlpha = pTowerTopSpriteAlpha;
        mTowerTopSpriteScale = pTowerTopSpriteScale;

        mTowerBaseSprite = pTowerBaseSprite;
        mTowerBaseSpriteRed = pTowerBaseSpriteRed;
        mTowerBaseSpriteGreen = pTowerBaseSpriteGreen;
        mTowerBaseSpriteBlue = pTowerBaseSpriteBlue;
        mTowerBaseSpriteAlpha = pTowerBaseSpriteAlpha;
        mTowerBaseSpriteScale = pTowerBaseSpriteScale;

        mUiSprite = pUiSprite;
        mUiSpriteRed = pUiSpriteRed;
        mUiSpriteGreen = pUiSpriteGreen;
        mUiSpriteBlue = pUiSpriteBlue;
        mUiSpriteAlpha = pUiSpriteAlpha;

        mTowerAi = pTowerAi;
    }

    public PlayerRewardTypes getPlayerRewardType() {
        return mPlayerRewardType;
    }

    public int getCost() {
        return mCost;
    }

    public int getRefund() {
        return mRefund;
    }

    public int getRange() {
        return mRange;
    }

    public float getSpeed() {
        return mSpeed;
    }

    public Damages getCastDamages() {
        return mCastDamages;
    }
    public Damages getFlightDamages() {
        return mFlightDamages;
    }
    public Damages getImpactDamages() {
        return mImpactDamages;
    }

    public float getTowerTileCenterX() {
        return mTowerTileCenterX;
    }

    public float getTowerTileCenterY() {
        return mTowerTileCenterY;
    }

    public float getTowerCastCenterX() {
        return mTowerCastCenterX;
    }

    public float getTowerCastCenterY() {
        return mTowerCastCenterY;
    }

    public ITowerSprites getTowerTopSprite() {
        return mTowerTopSprite;
    }

    public float getTowerTopSpriteRed() {
        return mTowerTopSpriteRed;
    }

    public float getTowerTopSpriteGreen() {
        return mTowerTopSpriteGreen;
    }

    public float getTowerTopSpriteBlue() {
        return mTowerTopSpriteBlue;
    }

    public float getTowerTopSpriteAlpha() {
        return mTowerTopSpriteAlpha;
    }

    public float getTowerTopSpriteScale() {
        return mTowerTopSpriteScale;
    }

    public ITowerSprites getTowerBaseSprite() {
        return mTowerBaseSprite;
    }

    public float getTowerBaseSpriteRed() {
        return mTowerBaseSpriteRed;
    }

    public float getTowerBaseSpriteGreen() {
        return mTowerBaseSpriteGreen;
    }

    public float getTowerBaseSpriteBlue() {
        return mTowerBaseSpriteBlue;
    }

    public float getTowerBaseSpriteAlpha() {
        return mTowerBaseSpriteAlpha;
    }

    public float getTowerBaseSpriteScale() {
        return mTowerBaseSpriteScale;
    }

    public float getUiSpriteRed() {
        return mUiSpriteRed;
    }

    public float getUiSpriteGreen() {
        return mUiSpriteGreen;
    }

    public float getUiSpriteBlue() {
        return mUiSpriteBlue;
    }

    public float getUiSpriteAlpha() {
        return mUiSpriteAlpha;
    }

    public int getUiCol() {
        return mUiSprite.getCol();
    }

    public int getUiRow() {
        return mUiSprite.getRow();
    }

    public ITowerAi getTowerAi() {
        return mTowerAi;
    }

    public TowerTypes[] getUpgrades() {
        final String search = this.name();

        for(final TowerTypesUpgrades t : TowerTypesUpgrades.values()) {
            if(t.name().equals(search)){
                return t.getUpgrades();
            }
        }

        return TowerTypesUpgrades.NONE.getUpgrades();
    }

    private enum TowerTypesUpgrades {

        /** PHYSICAL */
        ARCHER1(new TowerTypes[] { TowerTypes.ARCHER2, TowerTypes.MULTISHOT_ARCHER1, TowerTypes.TRUESHOT_ARCHER1, TowerTypes.POISON_ARCHER1, TowerTypes.IRON_ARCHER1 }),
        ARCHER2(new TowerTypes[] { TowerTypes.ARCHER3 }),
        ARCHER3(new TowerTypes[] { TowerTypes.ARCHER4 }),
        ARCHER4(new TowerTypes[] { TowerTypes.ARCHER5 }),
        ARCHER5(new TowerTypes[] { TowerTypes.SUPER_TOWER }),

        MULTISHOT_ARCHER1(new TowerTypes[] { TowerTypes.MULTISHOT_ARCHER2 }),
        MULTISHOT_ARCHER2(new TowerTypes[] { TowerTypes.MULTISHOT_ARCHER3 }),
        MULTISHOT_ARCHER3(new TowerTypes[] { TowerTypes.MULTISHOT_ARCHER4 }),
        MULTISHOT_ARCHER4(new TowerTypes[] { TowerTypes.MULTISHOT_ARCHER5 }),

        TRUESHOT_ARCHER1(new TowerTypes[] { TowerTypes.TRUESHOT_ARCHER2 }),
        TRUESHOT_ARCHER2(new TowerTypes[] { TowerTypes.TRUESHOT_ARCHER3 }),
        TRUESHOT_ARCHER3(new TowerTypes[] { TowerTypes.TRUESHOT_ARCHER4 }),
        TRUESHOT_ARCHER4(new TowerTypes[] { TowerTypes.TRUESHOT_ARCHER5 }),

        POISON_ARCHER1(new TowerTypes[] { TowerTypes.POISON_ARCHER2 }),
        POISON_ARCHER2(new TowerTypes[] { TowerTypes.POISON_ARCHER3 }),
        POISON_ARCHER3(new TowerTypes[] { TowerTypes.POISON_ARCHER4 }),
        POISON_ARCHER4(new TowerTypes[] { TowerTypes.POISON_ARCHER5 }),

        IRON_ARCHER1(new TowerTypes[] { TowerTypes.IRON_ARCHER2 }),
        IRON_ARCHER2(new TowerTypes[] { TowerTypes.IRON_ARCHER3 }),
        IRON_ARCHER3(new TowerTypes[] { TowerTypes.IRON_ARCHER4 }),
        IRON_ARCHER4(new TowerTypes[] { TowerTypes.IRON_ARCHER5 }),

        /** MAGICAL */
        MAGE1(new TowerTypes[] { TowerTypes.MAGE2, TowerTypes.FIRE_MAGE1, TowerTypes.EARTH_MAGE1, TowerTypes.ACCUMULATING_MAGE1, TowerTypes.DIMINISHING_MAGE1 }),
        MAGE2(new TowerTypes[] { TowerTypes.MAGE3 }),
        MAGE3(new TowerTypes[] { TowerTypes.MAGE4 }),
        MAGE4(new TowerTypes[] { TowerTypes.MAGE5 }),

        FIRE_MAGE1(new TowerTypes[] { TowerTypes.FIRE_MAGE2 }),
        FIRE_MAGE2(new TowerTypes[] { TowerTypes.FIRE_MAGE3 }),
        FIRE_MAGE3(new TowerTypes[] { TowerTypes.FIRE_MAGE4 }),
        FIRE_MAGE4(new TowerTypes[] { TowerTypes.FIRE_MAGE5 }),

        EARTH_MAGE1(new TowerTypes[] { TowerTypes.EARTH_MAGE2 }),
        EARTH_MAGE2(new TowerTypes[] { TowerTypes.EARTH_MAGE3 }),
        EARTH_MAGE3(new TowerTypes[] { TowerTypes.EARTH_MAGE4 }),
        EARTH_MAGE4(new TowerTypes[] { TowerTypes.EARTH_MAGE5 }),

        ACCUMULATING_MAGE1(new TowerTypes[] { TowerTypes.ACCUMULATING_MAGE2 }),
        ACCUMULATING_MAGE2(new TowerTypes[] { TowerTypes.ACCUMULATING_MAGE3 }),
        ACCUMULATING_MAGE3(new TowerTypes[] { TowerTypes.ACCUMULATING_MAGE4 }),
        ACCUMULATING_MAGE4(new TowerTypes[] { TowerTypes.ACCUMULATING_MAGE5 }),

        DIMINISHING_MAGE1(new TowerTypes[] { TowerTypes.DIMINISHING_MAGE2 }),
        DIMINISHING_MAGE2(new TowerTypes[] { TowerTypes.DIMINISHING_MAGE3 }),
        DIMINISHING_MAGE3(new TowerTypes[] { TowerTypes.DIMINISHING_MAGE4 }),
        DIMINISHING_MAGE4(new TowerTypes[] { TowerTypes.DIMINISHING_MAGE5 }),

        NONE(new TowerTypes[] { });


        private final TowerTypes[] mTowerTypes;

        private TowerTypesUpgrades(TowerTypes[] pTowerTypes) {
            mTowerTypes = pTowerTypes;
        }

        public TowerTypes[] getUpgrades() {
            return mTowerTypes;
        }
    }
}