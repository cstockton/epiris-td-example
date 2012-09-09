package epiris.tower.defense.cstockton.org.spell;

import org.anddev.andengine.entity.IEntity;

public interface ISpellTargetable {
    public float getCenterX();
    public float getCenterY();

    public IEntity getEntity();

    public long getSpellTargetableId(final Spell pSpell);
    public boolean isSpellTargetableId(final long pAffectableId);

}
