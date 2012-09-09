package epiris.tower.defense.cstockton.org.laser;

import org.anddev.andengine.entity.primitive.Line;

import epiris.tower.defense.cstockton.org.game.GameLayer;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.tower.Tower;

public class Laser extends Line
{

    float mActivateTimeElapsed = 0f;
    float mLifetime = .75f;
    Tower mAttachedTower;
    boolean mIsActive = false;

    public Laser(float pX1, float pY1, float pX2, float pY2)
    {
        super(pX1, pY1, pX2, pY2);

        Registry.sScene.getChild(GameLayer.LAYER_EFFECTS).attachChild(this);
    }

    public Laser(float pX1, float pY1, float pX2, float pY2, float pLineWidth)
    {
        super(pX1, pY1, pX2, pY2, pLineWidth);

        Registry.sScene.getChild(GameLayer.LAYER_EFFECTS).attachChild(this);
    }

    public void onLaserUpdate(final float pSecondsElapsed)
    {
        this.mActivateTimeElapsed += pSecondsElapsed;

        if(this.mActivateTimeElapsed > this.mLifetime) {
            this.setActive(false);
        }
    }

    public boolean isActive()
    {
        return this.mIsActive;
    }

    public Laser setActive(final boolean pActive)
    {
        this.mIsActive = pActive;
        return this;
    }

    public Laser setLifetime(final float pLifetime) {
        this.mLifetime = pLifetime;
        return this;
    }

    public void deactivate()
    {
        this.setIgnoreUpdate(true);
        this.setVisible(false);
        this.mIsActive = false;
        this.mActivateTimeElapsed = 0f;
        this.mLifetime = .75f;
    }

    public void activate(Tower pTowerBase)
    {
        this.mAttachedTower = pTowerBase;

        this.setIgnoreUpdate(false);
        this.setVisible(true);
        this.mIsActive = true;

        if(null == this.getParent()) {
            Registry.sScene.attachChild(this);
        }
    }
}