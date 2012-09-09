package epiris.tower.defense.cstockton.org.tower;

import java.util.ArrayList;

import epiris.tower.defense.cstockton.org.config.SpellTypes;
import epiris.tower.defense.cstockton.org.spell.ISpellTargetable;
import epiris.tower.defense.cstockton.org.spell.Spell;

public class TowerCasterAi implements ITowerAi  {
    final public ArrayList<TowerCasterAiSpell> mTowerCasterAiSpells = new ArrayList<TowerCasterAiSpell>();

    public TowerCasterAi() {
        super();
    }

    public TowerCasterAi addSpell(final SpellTypes pSpellType) {
        mTowerCasterAiSpells.add(new TowerCasterAiSpell(pSpellType, 0f));

        return this;
    }

    public TowerCasterAi addSpell(final SpellTypes pSpellType, final float pCastDelay) {
        mTowerCasterAiSpells.add(new TowerCasterAiSpell(pSpellType, pCastDelay));

        return this;
    }

    @Override
    public void onAttack(final Tower pTower) {
        final ISpellTargetable target = pTower.mSpellTargetables.get(0);

        for(final TowerCasterAiSpell tcas : mTowerCasterAiSpells) {
            Spell.cast(pTower, target, tcas.mSpellType, tcas.mCastDelay);
        }
    }

    public class TowerCasterAiSpell {
        final public SpellTypes mSpellType;
        final public float mCastDelay;

        public TowerCasterAiSpell(final SpellTypes pSpellType, final float pCastDelay) {
            mSpellType = pSpellType;
            mCastDelay = pCastDelay;
        }
    }
}