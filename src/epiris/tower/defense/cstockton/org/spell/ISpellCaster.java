package epiris.tower.defense.cstockton.org.spell;

import org.anddev.andengine.entity.IEntity;

public interface ISpellCaster {
    public IEntity getEntity();

    public float getX();
    public float getY();

    public float getCenterX();
    public float getCenterY();

}