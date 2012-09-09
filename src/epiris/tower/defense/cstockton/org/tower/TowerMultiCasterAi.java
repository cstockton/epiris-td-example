package epiris.tower.defense.cstockton.org.tower;

import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;

public class TowerMultiCasterAi extends TowerCasterAi {
    final int mMaxTargets;
    final float mCastDelay;

    public TowerMultiCasterAi(final int pMaxTargets, final float pCastDelay) {
        super();

        mMaxTargets = pMaxTargets;
        mCastDelay = pCastDelay;
    }

    @Override
    public void onAttack(final Tower pTower) {
        int targetsCastUpon = 0;

        for(final ISpellTargetable target : pTower.mSpellTargetables) {
            if(++targetsCastUpon > mMaxTargets) {
                break;
            }

            for(final TowerCasterAiSpell tcas : mTowerCasterAiSpells) {
                Spell.cast(pTower, target, tcas.mSpellType, (tcas.mCastDelay * targetsCastUpon) + mCastDelay);
            }
        }
    }
}