package epiris.tower.defense.cstockton.org.spell;

import java.util.ArrayList;
import java.util.Iterator;

import epiris.tower.defense.cstockton.org.util.GenericPool;

public class SpellManager {
    final private ArrayList<ISpells> mActiveSpells = new ArrayList<ISpells>();
    final private GenericPool<Spell> mPool = new SpellPool();

    final public Spell obtain() {
        return mPool.obtainPoolItem();
    }

    final public void recycle(final Spell pSpell) {
        mPool.recyclePoolItem(pSpell);
    }

    public void onUpdate(final float pSecondsElapsed) {
        final ArrayList<ISpells> activeSpells = new ArrayList<ISpells>(mActiveSpells);

        for(final Iterator<ISpells> iterator = activeSpells.iterator(); iterator.hasNext();) {
            final ISpells spell = iterator.next();

            if(spell.isUpdating()) {
                spell.onUpdate(pSecondsElapsed);
            }
        }
    }

    public ISpells addSpell(ISpells pSpell) {
        mActiveSpells.add(pSpell);
        return pSpell;
    }
}