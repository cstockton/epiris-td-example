package epiris.tower.defense.cstockton.org.tower;

import java.lang.reflect.Field;

import epiris.tower.defense.cstockton.org.util.Debug;

final public class TowerConstants {

    final static public int TOWER_GENERAL_COST_TIER_1 = 5;
    final static public int TOWER_GENERAL_COST_TIER_2 = 25;
    final static public int TOWER_GENERAL_COST_TIER_3 = 125;
    final static public int TOWER_GENERAL_COST_TIER_4 = 625;
    final static public int TOWER_GENERAL_COST_TIER_5 = 3125;
    final static public int TOWER_GENERAL_COST_TIER_6 = 6250;

    final static public int TOWER_RANGE_TIER_1 = 96;
    final static public int TOWER_RANGE_TIER_2 = 192;
    final static public int TOWER_RANGE_TIER_3 = 256;
    final static public int TOWER_RANGE_TIER_4 = 320;
    final static public int TOWER_RANGE_TIER_5 = 352;
    final static public int TOWER_RANGE_TIER_6 = 384;

    final static public int TOWER_AOE_RANGE_TIER_1 = 96;
    final static public int TOWER_AOE_RANGE_TIER_2 = 192;
    final static public int TOWER_AOE_RANGE_TIER_3 = 256;
    final static public int TOWER_AOE_RANGE_TIER_4 = 320;
    final static public int TOWER_AOE_RANGE_TIER_5 = 352;
    final static public int TOWER_AOE_RANGE_TIER_6 = 384;

    final static public float TOWER_SCALE_TIER_1 = .7f;
    final static public float TOWER_SCALE_TIER_2 = .8f;
    final static public float TOWER_SCALE_TIER_3 = .9f;
    final static public float TOWER_SCALE_TIER_4 = 1f;
    final static public float TOWER_SCALE_TIER_5 = 1.1f;
    final static public float TOWER_SCALE_TIER_6 = 1.2f;

    final static public float TOWER_SPEED_TIER_1 = 2f;
    final static public float TOWER_SPEED_TIER_2 = 1.9f;
    final static public float TOWER_SPEED_TIER_3 = 1.8f;
    final static public float TOWER_SPEED_TIER_4 = 1.7f;
    final static public float TOWER_SPEED_TIER_5 = 1.6f;
    final static public float TOWER_SPEED_TIER_6 = 1.5f;
    final static public float TOWER_SPEED_TIER_7 = 1.4f;
    final static public float TOWER_SPEED_TIER_8 = 1.3f;
    final static public float TOWER_SPEED_TIER_9 = 1.2f;
    final static public float TOWER_SPEED_TIER_10 = 1.1f;
    final static public float TOWER_SPEED_TIER_11 = 1f;
    final static public float TOWER_SPEED_TIER_12 = .95f;
    final static public float TOWER_SPEED_TIER_13 = .90f;
    final static public float TOWER_SPEED_TIER_14 = .85f;
    final static public float TOWER_SPEED_TIER_15 = .80f;

    final static public int TOWER_DAMAGE_TIER_1 = 10;
    final static public int TOWER_DAMAGE_TIER_2 = 50;
    final static public int TOWER_DAMAGE_TIER_3 = 250;
    final static public int TOWER_DAMAGE_TIER_4 = 1000;
    final static public int TOWER_DAMAGE_TIER_5 = 5000;
    final static public int TOWER_DAMAGE_TIER_6 = 10000;

    final static public float TOWER_VELOCITY_TIER_1 = 150f;
    final static public float TOWER_VELOCITY_TIER_2 = 200f;
    final static public float TOWER_VELOCITY_TIER_3 = 250f;
    final static public float TOWER_VELOCITY_TIER_4 = 300f;
    final static public float TOWER_VELOCITY_TIER_5 = 350f;
    final static public float TOWER_VELOCITY_TIER_6 = 400f;
    final static public float TOWER_VELOCITY_TIER_7 = 450f;
    final static public float TOWER_VELOCITY_TIER_8 = 500f;
    final static public float TOWER_VELOCITY_TIER_9 = 600f;
    final static public float TOWER_VELOCITY_TIER_10 = 700f;
    final static public float TOWER_VELOCITY_TIER_11 = 800f;
    final static public float TOWER_VELOCITY_TIER_12 = 900f;
    final static public float TOWER_VELOCITY_TIER_13 = 1000f;
    final static public float TOWER_VELOCITY_TIER_14 = 1100f;
    final static public float TOWER_VELOCITY_TIER_15 = 1200f;
    final static public float TOWER_VELOCITY_TIER_16 = 1300f;
    final static public float TOWER_VELOCITY_TIER_17 = 1400f;
    final static public float TOWER_VELOCITY_TIER_18 = 1600f;
    final static public float TOWER_VELOCITY_TIER_19 = 1800f;
    final static public float TOWER_VELOCITY_TIER_20 = 2000f;
    final static public float TOWER_VELOCITY_INSTANT = 2999999999f;

    final static public float getFloatByName(final String pName) {
        for(final Field field : TowerConstants.class.getDeclaredFields()) {
            if(field.getName().equals(pName)) {
                try {
                    return field.getFloat(field);
                } catch (IllegalArgumentException e) {
                    Debug.e(e);
                } catch (IllegalAccessException e) {
                    Debug.e(e);
                }
            }
        }

        return 0f;
    }
}