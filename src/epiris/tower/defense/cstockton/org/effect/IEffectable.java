package epiris.tower.defense.cstockton.org.effect;

import org.anddev.andengine.entity.IEntity;

public interface IEffectable {
    public float getEffectX();
    public float getEffectY();
    public IEntity getEntity();
    public boolean isEffectable();
}