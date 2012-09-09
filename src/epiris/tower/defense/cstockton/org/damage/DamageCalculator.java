package epiris.tower.defense.cstockton.org.damage;

import epiris.tower.defense.cstockton.org.object.Registry;

public class DamageCalculator {
    static public float getTotalDamage(final Damages pDamages, final DamageResists pDamageResists) {
        float totalDamage = 0f;

        if(null != pDamages) {
            for(final Damage dmg : pDamages.mDamages) {
                if(null != pDamageResists) {
                    final DamageResist dr = pDamageResists.getResistByType(dmg.mDamageType);

                    if(null != dr && dr.mDamageResistance > 0) {
                        totalDamage += ((1f + pDamages.mMultiplier) * dmg.mDamageAmount) * (1f - dr.mDamageResistance);

                        if(totalDamage > 0) {
                            Registry.sPlayerStatistics.addStatistic(dmg.mDamageType.mName, (int) totalDamage);
                        }

                        continue;
                    }
                }

                totalDamage += ((1f + pDamages.mMultiplier) * dmg.mDamageAmount);

                if(totalDamage > 0) {
                    Registry.sPlayerStatistics.addStatistic(dmg.mDamageType.mName, (int) totalDamage);
                }
            }
        }

        return totalDamage < 0 ? 0 : totalDamage;
    }
}
