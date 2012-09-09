package epiris.tower.defense.cstockton.org.creep;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.object.Registry;

public class CreepBody extends AnimatedSprite {

    public CreepBody(final TiledTextureRegion pCreepTiledTextureRegion) {
        super(0, 0, pCreepTiledTextureRegion);

        // set this creep to visible = false on instantiation
        setVisible(false);

        // also ignore updates
        setIgnoreUpdate(true);

    }

    public CreepBody activate(final Creep pCreep) {
        //Debug.d("CreepBody :: activate :: pCreepType=" + pCreepType);

        // setup sprite
        setScale(pCreep.mScale);
        setAlpha(pCreep.mSpriteAlpha);
        setColor(pCreep.mSpriteRed, pCreep.mSpriteGreen, pCreep.mSpriteBlue);

        // attach to the scene now
        Registry.sSceneLayerCreeps.attachChild(this);

        // turn on updates and visibility
        setIgnoreUpdate(false);
        setVisible(true);

        return this;
    }

    public CreepBody deactivate() {
        //Debug.d("CreepBody :: deactivate");

        // turn off updates and visibility
        setIgnoreUpdate(true);
        setVisible(false);

        // set position
        setPosition(-500, -500);

        // remove entity
        Registry.sSceneLayerCreeps.detachChild(this);

        // reset
        reset();

        return this;
    }


    @Override
    public void onManagedUpdate(float pSecondsElapsed) {

        // respect the fast forward
        if (Registry.sFloatFastForward > 1) {
            pSecondsElapsed *= Registry.sFloatFastForward;
        }

        // call the parent update method
        super.onManagedUpdate(pSecondsElapsed);

    }
}