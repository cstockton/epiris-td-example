package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.creep.CreepConstants;
import epiris.tower.defense.cstockton.org.damage.DamageResists;
import epiris.tower.defense.cstockton.org.util.Debug;

public enum CreepTypes {

    // PHYSICAL RESIST
    TIER_1_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_10),
    TIER_1_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_10),
    TIER_1_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_10),
    TIER_1_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_10),
    TIER_1_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_10),

    TIER_2_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_20),
    TIER_2_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_20),
    TIER_2_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_20),
    TIER_2_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_20),
    TIER_2_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_20),

    TIER_3_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_30),
    TIER_3_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_30),
    TIER_3_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_30),
    TIER_3_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_30),
    TIER_3_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_30),

    TIER_4_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_40),
    TIER_4_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_40),
    TIER_4_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_40),
    TIER_4_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_40),
    TIER_4_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_40),

    TIER_5_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_50),
    TIER_5_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_50),
    TIER_5_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_50),
    TIER_5_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_50),
    TIER_5_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_50),

    TIER_6_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_60),
    TIER_6_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_60),
    TIER_6_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_60),
    TIER_6_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_60),
    TIER_6_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_60),

    TIER_7_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_70),
    TIER_7_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_70),
    TIER_7_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_70),
    TIER_7_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_70),
    TIER_7_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_70),

    TIER_8_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_80),
    TIER_8_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_80),
    TIER_8_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_80),
    TIER_8_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_80),
    TIER_8_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_80),

    TIER_9_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_90),
    TIER_9_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_90),
    TIER_9_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_90),
    TIER_9_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_90),
    TIER_9_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_90),

    TIER_10_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_95),
    TIER_10_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_95),
    TIER_10_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_95),
    TIER_10_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_95),
    TIER_10_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_95),

    TIER_11_DARK_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.DARK_KNIGHT, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_99),
    TIER_11_BLUE_KNIGHT(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_KNIGHT, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_99),
    TIER_11_WHITE_VIKING(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_VIKING, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_99),
    TIER_11_BLUE_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.BLUE_ARCHER, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_99),
    TIER_11_GREEN_ARCHER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_ARCHER, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_PHYSICAL_99),

    // WIZARDS
    TIER_1_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_10),
    TIER_1_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_10),
    TIER_1_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_10),
    TIER_1_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_10),
    TIER_1_WHITE_WIZARD_HEALER(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_10),

    TIER_2_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_20),
    TIER_2_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_20),
    TIER_2_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_20),
    TIER_2_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_20),

    TIER_3_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_30),
    TIER_3_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_30),
    TIER_3_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_30),
    TIER_3_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_30),

    TIER_4_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_40),
    TIER_4_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_40),
    TIER_4_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_40),
    TIER_4_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_40),

    TIER_5_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_50),
    TIER_5_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_50),
    TIER_5_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_50),
    TIER_5_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_50),

    TIER_6_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_60),
    TIER_6_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_60),
    TIER_6_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_60),
    TIER_6_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_60),

    TIER_7_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_70),
    TIER_7_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_70),
    TIER_7_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_70),
    TIER_7_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_70),

    TIER_8_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_80),
    TIER_8_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_80),
    TIER_8_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_80),
    TIER_8_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_80),

    TIER_9_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_90),
    TIER_9_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_90),
    TIER_9_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_90),
    TIER_9_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_90),

    TIER_10_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_95),
    TIER_10_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_95),
    TIER_10_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_95),
    TIER_10_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_95),

    TIER_11_POOR_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.POOR_WIZARD, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_99),
    TIER_11_WHITE_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.WHITE_WIZARD, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_99),
    TIER_11_GREEN_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.GREEN_WIZARD, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_99),
    TIER_11_RED_WIZARD(CreepConstants.CREEP_SPEED_TIER_3, CreepSprites.RED_WIZARD, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_MAGICAL_99),

    // BOSSES
    TIER_1_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_10),
    TIER_1_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_10),
    TIER_1_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_1, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_10),

    TIER_2_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_20),
    TIER_2_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_20),
    TIER_2_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_2, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_20),

    TIER_3_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_30),
    TIER_3_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_30),
    TIER_3_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_3, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_30),

    TIER_4_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_40),
    TIER_4_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_40),
    TIER_4_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_4, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_40),

    TIER_5_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_50),
    TIER_5_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_50),
    TIER_5_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_5, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_50),

    TIER_6_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_60),
    TIER_6_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_60),
    TIER_6_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_6, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_60),

    TIER_7_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_70),
    TIER_7_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_70),
    TIER_7_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_7, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_70),

    TIER_8_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_80),
    TIER_8_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_80),
    TIER_8_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_8, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_80),

    TIER_9_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_90),
    TIER_9_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_90),
    TIER_9_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_9, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_90),

    TIER_10_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_95),
    TIER_10_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_95),
    TIER_10_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_10, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_95),

    TIER_11_GREEN_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.GREEN_TROLL, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_99),
    TIER_11_ICE_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.ICE_TROLL, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_99),
    TIER_11_LAVA_TROLL(CreepConstants.CREEP_SPEED_TIER_1, CreepSprites.LAVA_TROLL, CreepConstants.CREEP_SCALE_TIER_11, 1f, 1f, 1f, 1f, CreepConstants.CREEP_RESIST_BOTH_99),

    ;

    private final float mSpeed;

    private final CreepSprites mSprite;
    private final float mScale;
    private final float mSpriteRed;
    private final float mSpriteGreen;
    private final float mSpriteBlue;
    private final float mSpriteAlpha;
    private final DamageResists mDamageResists;

    private final CreepSpellTypes[] mCreepSpellTypes;

    static public CreepTypes getFromString(final String pCreepType) {
        for(final CreepTypes creepType : CreepTypes.values()) {
            if(pCreepType.equals(creepType.name())) {
                return creepType;
            }
        }

        Debug.w("Could not find a creep type by name: " + pCreepType);

        return null;
    }

    CreepTypes(
            final float pSpeed,

            final CreepSprites pSprite,
            final float pScale,
            final float pSpriteRed,
            final float pSpriteGreen,
            final float pSpriteBlue,
            final float pSpriteAlpha,

            final DamageResists pDamageResists,

            final CreepSpellTypes... pCreepSpellTypes) {
        mSpeed = pSpeed;

        mSprite = pSprite;
        mScale = pScale;
        mSpriteRed = pSpriteRed;
        mSpriteGreen = pSpriteGreen;
        mSpriteBlue = pSpriteBlue;
        mSpriteAlpha = pSpriteAlpha;

        mDamageResists = pDamageResists;

        mCreepSpellTypes = pCreepSpellTypes;

    }

    public float getSpeed() {
        return mSpeed;
    }

    public DamageResists getDamageResists() {
        return mDamageResists;
    }

    public CreepSpellTypes[] getCreepSpellTypes() {
        return mCreepSpellTypes;
    }

    public CreepSprites getSprite() {
        return mSprite;
    }

    public float getScale() {
        return mScale;
    }

    public float getSpriteRed() {
        return mSpriteRed;
    }

    public float getSpriteGreen() {
        return mSpriteGreen;
    }

    public float getSpriteBlue() {
        return mSpriteBlue;
    }

    public float getSpriteAlpha() {
        return mSpriteAlpha;
    }
}