package epiris.tower.defense.cstockton.org.spell;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.GenericPool;

public class SpellPool extends GenericPool<Spell> {

    public SpellPool() {
        super(0, 30);
    }

    @Override
    protected Spell onAllocatePoolItem() {
        final Spell s = new Spell();
        Registry.sSpellManager.addSpell(s);

        return s;
    }

    @Override
    protected void onHandleRecycleItem(final Spell pSpell) {
        pSpell.reset();
    }
}