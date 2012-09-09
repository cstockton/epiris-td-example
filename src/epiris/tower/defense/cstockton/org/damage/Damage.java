package epiris.tower.defense.cstockton.org.damage;

import epiris.tower.defense.cstockton.org.config.DamageTypes;

public class Damage {
    final public DamageTypes mDamageType;
    final public int mDamageAmount;

    public Damage(final DamageTypes pDamageType, final int pDamageAmount) {
        mDamageType = pDamageType;
        mDamageAmount = pDamageAmount;
    }
}
