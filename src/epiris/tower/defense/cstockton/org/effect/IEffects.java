package epiris.tower.defense.cstockton.org.effect;

import epiris.tower.defense.cstockton.org.config.EffectSpriteSheets;

public interface IEffects {
    public Effect getEffect();
    public EffectSpriteSheets getEffectSpriteSheet();
    public void recycleEffect(final Effect pEffect);

    public int getWidth();
    public int getHeight();
    public int getSpriteWidth();
    public int getSpriteHeight();
    public int getSpriteCols();
    public int getSpriteRows();

    public float getRedModifier();
    public float getGreenModifier();
    public float getBlueModifier();
    public float getAlphaModifier();
    public float getScaleModifier();

    public int getTileIndexStart();
    public int getTileIndexEnd();
    public int getAnimationFrameDuration();
    public int getAnimationFrameLength();
    public long[] getAnimationFrames();
}