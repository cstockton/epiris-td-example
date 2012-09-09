package epiris.tower.defense.cstockton.org.damage;

import epiris.tower.defense.cstockton.org.config.DamageTypes;

public class DamageResist {
    final public DamageTypes mDamageType;
    final public float mDamageResistance;

    public DamageResist(final DamageTypes pDamageType, final float pDamageResistance) {
        mDamageType = pDamageType;
        mDamageResistance = pDamageResistance;
    }
}
