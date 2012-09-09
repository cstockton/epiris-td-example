package epiris.tower.defense.cstockton.org.damage;

import epiris.tower.defense.cstockton.org.config.DamageTypes;

public class DamageResists {
    public DamageResist[] mDamageResists;

    public DamageResists(final DamageResist... pDamageResists) {
        mDamageResists = pDamageResists;
    }

    public DamageResist getResistByType(final DamageTypes pDamageType) {
        for(final DamageResist dr : mDamageResists) {
            if(dr.mDamageType.equals(pDamageType)) {
                return dr;
            }
        }

        return null;
    }
}