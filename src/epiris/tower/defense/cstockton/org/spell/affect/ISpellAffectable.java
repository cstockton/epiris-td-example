package epiris.tower.defense.cstockton.org.spell.affect;

import org.anddev.andengine.entity.IEntity;

import epiris.tower.defense.cstockton.org.damage.Damages;
import epiris.tower.defense.cstockton.org.spell.ISpellCaster;

public interface ISpellAffectable {
    public float getX();
    public float getY();

    public float getCenterX();
    public float getCenterY();

    public IEntity getEntity();

    // affects have access to
    //public void modfyMovementSpeed();
    //public void snare();
    public void heal(final float pAmount, final boolean pIsPercentage);
    public int damage(final Damages pDamages);

    //public void setSnared(final ISpellAffects pSpellAffect);

    public void applyAffect(final ISpellCaster pSpellCaster, final ISpellAffects pSpellAffect);
    public void applyAffect(final ISpellCaster pSpellCaster, final SpellAffect pSpellAffect);
    public boolean hasAffect(final ISpellAffects pSpellAffect);
    public ISpellAffect getAffect(final ISpellAffects pSpellAffect);

}