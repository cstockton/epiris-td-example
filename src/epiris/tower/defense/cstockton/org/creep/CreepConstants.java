package epiris.tower.defense.cstockton.org.creep;

import java.lang.reflect.Field;

import epiris.tower.defense.cstockton.org.config.DamageTypes;
import epiris.tower.defense.cstockton.org.damage.DamageResist;
import epiris.tower.defense.cstockton.org.damage.DamageResists;
import epiris.tower.defense.cstockton.org.util.Debug;

final public class CreepConstants {

    final static public DamageResists CREEP_RESIST_NONE = new DamageResists();
    final static public DamageResists CREEP_RESIST_PHYSICAL_10 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .10f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_20 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .20f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_30 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .30f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_40 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .40f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_50 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .50f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_60 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .60f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_70 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .70f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_80 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .80f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_90 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .90f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_95 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .95f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_99 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .99f));
    final static public DamageResists CREEP_RESIST_PHYSICAL_999 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .999f));

    final static public DamageResists CREEP_RESIST_MAGICAL_10 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .10f));
    final static public DamageResists CREEP_RESIST_MAGICAL_20 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .20f));
    final static public DamageResists CREEP_RESIST_MAGICAL_30 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .30f));
    final static public DamageResists CREEP_RESIST_MAGICAL_40 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .40f));
    final static public DamageResists CREEP_RESIST_MAGICAL_50 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .50f));
    final static public DamageResists CREEP_RESIST_MAGICAL_60 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .60f));
    final static public DamageResists CREEP_RESIST_MAGICAL_70 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .70f));
    final static public DamageResists CREEP_RESIST_MAGICAL_80 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .80f));
    final static public DamageResists CREEP_RESIST_MAGICAL_90 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .90f));
    final static public DamageResists CREEP_RESIST_MAGICAL_95 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .95f));
    final static public DamageResists CREEP_RESIST_MAGICAL_99 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .99f));
    final static public DamageResists CREEP_RESIST_MAGICAL_999 = new DamageResists(new DamageResist(DamageTypes.MAGICAL, .999f));

    final static public DamageResists CREEP_RESIST_BOTH_10 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .10f), new DamageResist(DamageTypes.MAGICAL, .10f));
    final static public DamageResists CREEP_RESIST_BOTH_20 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .20f), new DamageResist(DamageTypes.MAGICAL, .20f));
    final static public DamageResists CREEP_RESIST_BOTH_30 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .30f), new DamageResist(DamageTypes.MAGICAL, .30f));
    final static public DamageResists CREEP_RESIST_BOTH_40 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .40f), new DamageResist(DamageTypes.MAGICAL, .40f));
    final static public DamageResists CREEP_RESIST_BOTH_50 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .50f), new DamageResist(DamageTypes.MAGICAL, .50f));
    final static public DamageResists CREEP_RESIST_BOTH_60 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .60f), new DamageResist(DamageTypes.MAGICAL, .60f));
    final static public DamageResists CREEP_RESIST_BOTH_70 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .70f), new DamageResist(DamageTypes.MAGICAL, .70f));
    final static public DamageResists CREEP_RESIST_BOTH_80 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .80f), new DamageResist(DamageTypes.MAGICAL, .80f));
    final static public DamageResists CREEP_RESIST_BOTH_90 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .90f), new DamageResist(DamageTypes.MAGICAL, .90f));
    final static public DamageResists CREEP_RESIST_BOTH_95 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .95f), new DamageResist(DamageTypes.MAGICAL, .95f));
    final static public DamageResists CREEP_RESIST_BOTH_99 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .99f), new DamageResist(DamageTypes.MAGICAL, .99f));
    final static public DamageResists CREEP_RESIST_BOTH_999 = new DamageResists(new DamageResist(DamageTypes.PHYSICAL, .999f), new DamageResist(DamageTypes.MAGICAL, .999f));

    final static public float CREEP_SCALE_TIER_1 = .50f;
    final static public float CREEP_SCALE_TIER_2 = .60f;
    final static public float CREEP_SCALE_TIER_3 = .70f;
    final static public float CREEP_SCALE_TIER_4 = .80f;
    final static public float CREEP_SCALE_TIER_5 = .90f;
    final static public float CREEP_SCALE_TIER_6 = 1.0f;
    final static public float CREEP_SCALE_TIER_7 = 1.1f;
    final static public float CREEP_SCALE_TIER_8 = 1.2f;
    final static public float CREEP_SCALE_TIER_9 = 1.3f;
    final static public float CREEP_SCALE_TIER_10 = 1.4f;
    final static public float CREEP_SCALE_TIER_11 = 1.5f;

    final static public float CREEP_SPEED_TIER_1 = 10f;
    final static public float CREEP_SPEED_TIER_2 = 25f;
    final static public float CREEP_SPEED_TIER_3 = 50f;
    final static public float CREEP_SPEED_TIER_4 = 75f;
    final static public float CREEP_SPEED_TIER_5 = 100f;
    final static public float CREEP_SPEED_TIER_6 = 125f;
    final static public float CREEP_SPEED_TIER_7 = 150f;
    final static public float CREEP_SPEED_TIER_8 = 175f;
    final static public float CREEP_SPEED_TIER_9 = 200f;
    final static public float CREEP_SPEED_TIER_10 = 225f;
    final static public float CREEP_SPEED_TIER_11 = 250f;

    final static public float getFloatByName(final String pName) {
        for(final Field field : CreepConstants.class.getDeclaredFields()) {
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

    final static public DamageResists getDamageResistsByName(final String pName) {
        for(final Field field : CreepConstants.class.getDeclaredFields()) {
            if(field.getName().equals(pName)) {
                try {
                    return (DamageResists) field.get(field);
                } catch (IllegalArgumentException e) {
                    Debug.e(e);
                } catch (IllegalAccessException e) {
                    Debug.e(e);
                }
            }
        }

        return CREEP_RESIST_NONE;
    }
}