package epiris.tower.defense.cstockton.org.effect;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.config.EffectTypes;
import epiris.tower.defense.cstockton.org.object.GameAnimatedSprite;
import epiris.tower.defense.cstockton.org.object.Registry;

public class Effect extends GameAnimatedSprite {

    boolean mActive = false;
    boolean mLoop = false;

    private EffectTypes mEffectType;
    private IEffectable mEffectable;

    public Effect(final TiledTextureRegion pProjectileTiledTextureRegion) {
        super(0, 0, pProjectileTiledTextureRegion);

        // set this creep to visible = false on instantiation
        setVisible(false);

        // also ignore updates
        setIgnoreUpdate(true);

    }

    public void onActivate() {}
    public void onDeactivate() {}
    public void onRecycle() {}

    @Override
    public void onManagedUpdate(float pSecondsElapsed) {
        pSecondsElapsed *= Registry.sFloatFastForward;

        if(mEffectable != null && mEffectable.isEffectable()) {
            setPosition(mEffectable.getEntity().getX() + (mEffectable.getEffectX() - (mEffectType.getWidth() / 2)), mEffectable.getEntity().getY() + (mEffectable.getEffectY() - (mEffectType.getHeight() / 2)));
        }

        setZIndex((int) getY());

        super.onManagedUpdate(pSecondsElapsed);
    }

    public void recycle() {
        onRecycle();

        mEffectType.recycleEffect(this);
    }

    public Effect setEffectType(final EffectTypes pEffectType) {
        mEffectType = pEffectType;
        return this;
    }

    public EffectTypes getEffectType() {
        return mEffectType;
    }

    public void activate() {
        boolean doAttach = true;

        if(mEffectable != null && !mEffectable.isEffectable()) {
            doAttach = false;

        } else if(mEffectable != null && mEffectable.isEffectable()) {
            setPosition(mEffectable.getEntity().getX() + (mEffectable.getEffectX() - (mEffectType.getWidth() / 2)), mEffectable.getEntity().getY() + (mEffectable.getEffectY() - (mEffectType.getHeight() / 2)));
        }

        mActive = true;

        if(doAttach) {

            // attach to the entity
            Registry.sSceneLayerEffects.attachChild(this);

            // turn on updates and visibility
            setIgnoreUpdate(false);
            setVisible(true);
            setColor(mEffectType.getRedModifier(), mEffectType.getGreenModifier(), mEffectType.getBlueModifier());
            setAlpha(mEffectType.getAlphaModifier());
            setScale(mEffectType.getScaleModifier());

            doEffect();
        }

        onActivate();
    }

    public void deactivate() {

        // stop animations
        stopAnimation();

        // turn off updates and visibility
        setIgnoreUpdate(true);
        setVisible(false);

        // this creep is now dead, so he cant be blocked
        mActive = false;

        // remove entity
        Registry.sSceneLayerEffects.detachChild(this);

        onDeactivate();

        mEffectable = null;

    }

    public void doEffect(final boolean pLoop) {
        mLoop = pLoop;

        doEffect();
    }

    public void doEffect() {

        // set the correct starting tile index
        setCurrentTileIndex(mEffectType.getTileIndexStart());

        // create the animation
        animate(
            mEffectType.getAnimationFrames(),
            mEffectType.getTileIndexStart(),
            mEffectType.getTileIndexEnd(),
            mLoop
        );
    }

    public boolean isActive() {
        return mActive;
    }

    public Effect setActive(final boolean pActive) {
        mActive = pActive;
        return this;
    }

    public Effect setLoop(final boolean pLoop) {
        mLoop = pLoop;
        return this;
    }

    public boolean isLooping() {
        return mLoop;
    }

    public Effect setTargetEntity(final IEffectable pEntity) {
        mEffectable = pEntity;
        return this;
    }

    public IEffectable getTargetEntity() {
        return mEffectable;
    }
}