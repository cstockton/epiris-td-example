package epiris.tower.defense.cstockton.org.config;

import java.util.Arrays;

import epiris.tower.defense.cstockton.org.effect.Effect;
import epiris.tower.defense.cstockton.org.effect.IEffects;
import epiris.tower.defense.cstockton.org.object.Registry;

public enum EffectTypes implements IEffects {

    /** EFFECTS 64 01 */
    SPELLS_YELLOW_BALL_FLIGHT(0, 7, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_YELLOW_BALL_IMPACT(8, 15, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_YELLOW_BALL_S70_FLIGHT(0, 7, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_YELLOW_BALL_S70_IMPACT(8, 15, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_FIRE_BALL_LARGE_FLIGHT(16, 23, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_FIRE_BALL_LARGE_IMPACT(24, 31, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_FIRE_BALL_LARGE_S70_FLIGHT(16, 23, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_FIRE_BALL_LARGE_S70_IMPACT(24, 31, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_FIRE_BALL_SMALL_FLIGHT(32, 39, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_FIRE_BALL_SMALL_IMPACT(40, 47, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_FIRE_BALL_SMALL_S70_FLIGHT(32, 39, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_FIRE_BALL_SMALL_S70_IMPACT(40, 47, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_BOULDER_FLIGHT(48, 55, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BOULDER_IMPACT(56, 63, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BOULDER_S70_FLIGHT(48, 55, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_BOULDER_S70_IMPACT(56, 63, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_BLUE_LIGHTNING_SMALL_FLIGHT(64, 71, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_LIGHTNING_SMALL_IMPACT(72, 79, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_LIGHTNING_SMALL_S70_FLIGHT(64, 71, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_BLUE_LIGHTNING_SMALL_S70_IMPACT(72, 79, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_BLUE_LIGHTNING_LARGE_FLIGHT(80, 87, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_LIGHTNING_LARGE_IMPACT(88, 95, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_LIGHTNING_LARGE_S70_FLIGHT(80, 87, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_BLUE_LIGHTNING_LARGE_S70_IMPACT(88, 95, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_ICEBOLT_SMALL_FLIGHT(96, 103, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_ICEBOLT_SMALL_IMPACT(104, 102, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),

    SPELLS_ICEBOLT_LARGE_FLIGHT(112, 119, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_ICEBOLT_LARGE_IMPACT(120, 127, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),

    SPELLS_ARROW_FLIGHT(128, 135, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_ARROW_IMPACT(136, 143, 143, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_ARROW_S50_FLIGHT(128, 135, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .5f),
    SPELLS_ARROW_S50_IMPACT(136, 143, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .5f),
    SPELLS_ARROW_S70_FLIGHT(128, 135, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_ARROW_S70_IMPACT(136, 143, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELLS_IRON_ARROW_FLIGHT(144, 151, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_IRON_ARROW_IMPACT(152, 159, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),

    SPELLS_POISON_ARROW_FLIGHT(160, 167, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_POISON_ARROW_IMPACT(168, 175, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELLS_POISON_ARROW_S70_FLIGHT(160, 167, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELLS_POISON_ARROW_S70_IMPACT(168, 175, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELL_BLUE_COMET_FLIGHT(176, 183, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_BLUE_COMET_IMPACT(184, 191, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_BLUE_COMET_S70_FLIGHT(176, 183, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELL_BLUE_COMET_S70_IMPACT(184, 191, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELL_WHITE_COMET_FLIGHT(192, 199, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_WHITE_COMET_IMPACT(200, 207, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_WHITE_COMET_S70_FLIGHT(192, 199, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELL_WHITE_COMET_S70_IMPACT(200, 207, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELL_RED_COMET_FLIGHT(208, 215, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_RED_COMET_IMPACT(216, 223, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_RED_COMET_S70_FLIGHT(208, 215, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELL_RED_COMET_S70_IMPACT(216, 223, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELL_WHITE_ENERGY_FLIGHT(224, 231, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_WHITE_ENERGY_IMPACT(232, 239, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_WHITE_ENERGY_S70_FLIGHT(224, 231, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELL_WHITE_ENERGY_S70_IMPACT(232, 239, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    SPELL_POISON_CLOUD_FLIGHT(240, 247, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_POISON_CLOUD_IMPACT(248, 255, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, 1f),
    SPELL_POISON_CLOUD_S70_FLIGHT(240, 247, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),
    SPELL_POISON_CLOUD_S70_IMPACT(248, 255, 100, EffectSpriteSheets.EFFECTS64_01, 1f, 1f, 1f, 1f, .7f),

    /** EFFECTS64 02 */
    SPELLS_GREEN_ENERGY_FLIGHT(0, 7, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, 1f),
    SPELLS_GREEN_ENERGY_IMPACT(8, 15, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, 1f),
    SPELLS_GREEN_ENERGY_S70_FLIGHT(0, 7, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, .7f),
    SPELLS_GREEN_ENERGY_S70_IMPACT(8, 15, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, .7f),

    SPELLS_BLUE_BALL_LARGE_FLIGHT(16, 23, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_BALL_LARGE_IMPACT(24, 31, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, 1f),
    SPELLS_BLUE_BALL_LARGE_S70_FLIGHT(16, 23, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, .7f),
    SPELLS_BLUE_BALL_LARGE_S70_IMPACT(24, 31, 100, EffectSpriteSheets.EFFECTS64_02, 1f, 1f, 1f, 1f, .7f),

    /** EFFECTS256x128 */
    BLACK_AOE(0, 5, 100, EffectSpriteSheets.EFFECTS256x128, 1f, 1f, 1f, 1f, 1f),

    FIRE_EXPLOSION_125(17, 31, 100, EffectSpriteSheets.EFFECTS256x128, 1f, 1f, 1f, 1f, .8f),
    FIRE_EXPLOSION_150(17, 31, 100, EffectSpriteSheets.EFFECTS256x128, 1f, 1f, 1f, 1f, .9f),
    FIRE_EXPLOSION_175(17, 31, 100, EffectSpriteSheets.EFFECTS256x128, 1f, 1f, 1f, 1f, 1f),
    FIRE_EXPLOSION_225(17, 31, 100, EffectSpriteSheets.EFFECTS256x128, 1f, 1f, 1f, 1f, 1.2f),

    ;

    private final int mTileIndexStart;
    private final int mTileIndexEnd;
    private final int mAnimationFrameDuration;

    private final float mModifierRed;
    private final float mModifierGreen;
    private final float mModifierBlue;
    private final float mModifierAlpha;
    private final float mModifierScale;

    private final EffectSpriteSheets mEffectSpriteSheet;

    EffectTypes(
        final int pTileIndexStart,
        final int pTileIndexEnd,
        final int pAnimationFrameDuration,

        final EffectSpriteSheets pEffectSpriteSheet,

        final float pModifierRed,
        final float pModifierGreen,
        final float pModifierBlue,
        final float pModifierAlpha,
        final float pModifierScale
    ) {
        mTileIndexStart = pTileIndexStart;
        mTileIndexEnd = pTileIndexEnd;
        mAnimationFrameDuration = pAnimationFrameDuration;

        mEffectSpriteSheet = pEffectSpriteSheet;

        mModifierRed = pModifierRed;
        mModifierGreen = pModifierGreen;
        mModifierBlue = pModifierBlue;
        mModifierAlpha = pModifierAlpha;
        mModifierScale = pModifierScale;
    }

    @Override
    public int getWidth() {
        return mEffectSpriteSheet.getTileWidth();
    }

    @Override
    public int getHeight() {
        return mEffectSpriteSheet.getTileHeight();
    }

    @Override
    public int getSpriteWidth() {
        return mEffectSpriteSheet.getWidth();
    }

    @Override
    public int getSpriteHeight() {
        return mEffectSpriteSheet.getHeight();
    }

    @Override
    public int getSpriteCols() {
        return mEffectSpriteSheet.getCols();
    }

    @Override
    public int getSpriteRows() {
        return mEffectSpriteSheet.getRows();
    }

    @Override
    public float getRedModifier() {
        return mModifierRed;
    }

    @Override
    public float getGreenModifier() {
        return mModifierGreen;
    }

    @Override
    public float getBlueModifier() {
        return mModifierBlue;
    }

    @Override
    public float getAlphaModifier() {
        return mModifierAlpha;
    }

    @Override
    public float getScaleModifier() {
        return mModifierScale;
    }

    @Override
    public int getTileIndexStart() {
        return mTileIndexStart;
    }

    @Override
    public int getTileIndexEnd() {
        return mTileIndexEnd;
    }

    @Override
    public int getAnimationFrameDuration() {
        return mAnimationFrameDuration;
    }

    @Override
    public int getAnimationFrameLength() {
        return (mTileIndexEnd - mTileIndexStart) + 1;
    }

    @Override
    public long[] getAnimationFrames() {
        final long[] animationFrames = new long[getAnimationFrameLength()];
        Arrays.fill(animationFrames, getAnimationFrameDuration());
        return animationFrames;
    }

    @Override
    public EffectSpriteSheets getEffectSpriteSheet() {
        return mEffectSpriteSheet;
    }

    @Override
    public Effect getEffect() {
        return Registry.sEffectManager.obtain(this);
    }

    @Override
    public void recycleEffect(final Effect pEffect) {
        Registry.sEffectManager.recycle(pEffect);
    }
}