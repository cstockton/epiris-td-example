package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.effect.IEffects;
import epiris.tower.defense.cstockton.org.sprite.ISpriteSheet;
import epiris.tower.defense.cstockton.org.tower.ITowerSprites;

public enum TowerSprites implements ITowerSprites {

    /** PHYSICAL */
    ARCHER1(0, TowerSpriteSheets.TOWERS01),
    ARCHER2(1, TowerSpriteSheets.TOWERS01),
    ARCHER3(2, TowerSpriteSheets.TOWERS01),
    ARCHER4(3, TowerSpriteSheets.TOWERS01),
    ARCHER5(4, TowerSpriteSheets.TOWERS01),

    MULTISHOT_ARCHER1(8, TowerSpriteSheets.TOWERS01),
    MULTISHOT_ARCHER2(9, TowerSpriteSheets.TOWERS01),
    MULTISHOT_ARCHER3(10, TowerSpriteSheets.TOWERS01),
    MULTISHOT_ARCHER4(11, TowerSpriteSheets.TOWERS01),
    MULTISHOT_ARCHER5(12, TowerSpriteSheets.TOWERS01),

    POISON_ARCHER1(16, TowerSpriteSheets.TOWERS01),
    POISON_ARCHER2(17, TowerSpriteSheets.TOWERS01),
    POISON_ARCHER3(18, TowerSpriteSheets.TOWERS01),
    POISON_ARCHER4(19, TowerSpriteSheets.TOWERS01),
    POISON_ARCHER5(20, TowerSpriteSheets.TOWERS01),

    TRUESHOT_ARCHER1(24, TowerSpriteSheets.TOWERS01),
    TRUESHOT_ARCHER2(25, TowerSpriteSheets.TOWERS01),
    TRUESHOT_ARCHER3(26, TowerSpriteSheets.TOWERS01),
    TRUESHOT_ARCHER4(27, TowerSpriteSheets.TOWERS01),
    TRUESHOT_ARCHER5(28, TowerSpriteSheets.TOWERS01),

    IRON_ARCHER1(32, TowerSpriteSheets.TOWERS01),
    IRON_ARCHER2(33, TowerSpriteSheets.TOWERS01),
    IRON_ARCHER3(34, TowerSpriteSheets.TOWERS01),
    IRON_ARCHER4(35, TowerSpriteSheets.TOWERS01),
    IRON_ARCHER5(36, TowerSpriteSheets.TOWERS01),

    ARCHER_F_1(45, TowerSpriteSheets.TOWERS01),
    ARCHER_F_2(46, TowerSpriteSheets.TOWERS01),
    ARCHER_F_3(47, TowerSpriteSheets.TOWERS01),
    ARCHER_F_4(53, TowerSpriteSheets.TOWERS01),
    ARCHER_F_5(54, TowerSpriteSheets.TOWERS01),

    /** MAGICAL */
    MAGE1(40, TowerSpriteSheets.TOWERS01),
    MAGE2(41, TowerSpriteSheets.TOWERS01),
    MAGE3(42, TowerSpriteSheets.TOWERS01),
    MAGE4(43, TowerSpriteSheets.TOWERS01),
    MAGE5(44, TowerSpriteSheets.TOWERS01),

    FIRE_MAGE1(48, TowerSpriteSheets.TOWERS01),
    FIRE_MAGE2(49, TowerSpriteSheets.TOWERS01),
    FIRE_MAGE3(50, TowerSpriteSheets.TOWERS01),
    FIRE_MAGE4(51, TowerSpriteSheets.TOWERS01),
    FIRE_MAGE5(52, TowerSpriteSheets.TOWERS01),

    EARTH_MAGE1(56, TowerSpriteSheets.TOWERS01),
    EARTH_MAGE2(57, TowerSpriteSheets.TOWERS01),
    EARTH_MAGE3(58, TowerSpriteSheets.TOWERS01),
    EARTH_MAGE4(59, TowerSpriteSheets.TOWERS01),
    EARTH_MAGE5(60, TowerSpriteSheets.TOWERS01),

    ACCUMULATING_MAGE_1(5, TowerSpriteSheets.TOWERS01),
    ACCUMULATING_MAGE_2(13, TowerSpriteSheets.TOWERS01),
    ACCUMULATING_MAGE_3(21, TowerSpriteSheets.TOWERS01),
    ACCUMULATING_MAGE_4(29, TowerSpriteSheets.TOWERS01),
    ACCUMULATING_MAGE_5(37, TowerSpriteSheets.TOWERS01),

    MAGE_E_1(6, TowerSpriteSheets.TOWERS01),
    MAGE_E_2(14, TowerSpriteSheets.TOWERS01),
    MAGE_E_3(22, TowerSpriteSheets.TOWERS01),
    MAGE_E_4(30, TowerSpriteSheets.TOWERS01),
    MAGE_E_5(38, TowerSpriteSheets.TOWERS01),

    DIMINISHING_MAGE_1(7, TowerSpriteSheets.TOWERS01),
    DIMINISHING_MAGE_2(15, TowerSpriteSheets.TOWERS01),
    DIMINISHING_MAGE_3(23, TowerSpriteSheets.TOWERS01),
    DIMINISHING_MAGE_4(31, TowerSpriteSheets.TOWERS01),
    DIMINISHING_MAGE_5(39, TowerSpriteSheets.TOWERS01),

    ;

    private final int mInitialTileIndex;
    private final int mRotationTileIndexStart;
    private final int mRotationTileIndexEnd;
    private final boolean mRotationMirror;

    private final ISpriteSheet mTowerSpriteSheets;

    private final float mPlacementOffsetX;
    private final float mPlacementOffsetY;

    private final IEffects mOnIdleSpellEffect;
    private final IEffects mOnAttackSpellEffect;
    private final IEffects mOnAttackCompleteSpellEffect;

    TowerSprites(final ISpriteSheet pTowerSpriteSheets) {
        this(-1, pTowerSpriteSheets);
    }

    TowerSprites(
        final int pTileIndex,

        final ISpriteSheet pTowerSpriteSheets
    ) {
        this(pTileIndex, -1, -1, false, pTowerSpriteSheets, 0f, 0f, null, null, null);
    }

    TowerSprites(
        final int pRotationTileIndexStart,
        final int pRotationTileIndexEnd,
        final boolean pRotationMirror,

        final ISpriteSheet pTowerSpriteSheets
    ) {
        this(pRotationTileIndexStart, pRotationTileIndexStart, pRotationTileIndexEnd, pRotationMirror, pTowerSpriteSheets, 0f, 0f, null, null, null);
    }

    TowerSprites(
        final int pInitialTileIndex,
        final int pRotationTileIndexStart,
        final int pRotationTileIndexEnd,
        final boolean pRotationMirror,

        final ISpriteSheet pTowerSpriteSheets,

        final float pPlacementOffsetX,
        final float pPlacementOffsetY,

        final IEffects pOnIdleSpellEffect,
        final IEffects pOnAttackSpellEffect,
        final IEffects pOnAttackCompleteSpellEffect
    ) {
        mInitialTileIndex = pInitialTileIndex;

        mRotationTileIndexStart = pRotationTileIndexStart;
        mRotationTileIndexEnd = pRotationTileIndexEnd;
        mRotationMirror = pRotationMirror;

        mTowerSpriteSheets = pTowerSpriteSheets;

        mPlacementOffsetX = pPlacementOffsetX;
        mPlacementOffsetY = pPlacementOffsetY;

        mOnIdleSpellEffect = pOnIdleSpellEffect;
        mOnAttackSpellEffect = pOnAttackSpellEffect;
        mOnAttackCompleteSpellEffect = pOnAttackCompleteSpellEffect;
    }

    @Override
    public int getId() {
        return mTowerSpriteSheets.ordinal();
    }

    @Override
    public int getWidth() {
        return mTowerSpriteSheets.getTileWidth();
    }

    @Override
    public int getHeight() {
        return mTowerSpriteSheets.getTileHeight();
    }

    @Override
    public int getSpriteWidth() {
        return mTowerSpriteSheets.getWidth();
    }

    @Override
    public int getSpriteHeight() {
        return mTowerSpriteSheets.getTileHeight();
    }

    @Override
    public int getSpriteCols() {
        return mTowerSpriteSheets.getCols();
    }

    @Override
    public int getSpriteRows() {
        return mTowerSpriteSheets.getRows();
    }

    @Override
    public IEffects getOnIdleSpellEffect() {
        return mOnIdleSpellEffect;
    }

    @Override
    public IEffects getOnAttackSpellEffect() {
        return mOnAttackSpellEffect;
    }

    @Override
    public IEffects getOnAttackCompleteSpellEffect() {
        return mOnAttackCompleteSpellEffect;
    }

    @Override
    public int getInitialTileIndex() {
        return mInitialTileIndex;
    }

    @Override
    public int getRotationTileIndexStart() {
        return mRotationTileIndexStart;
    }

    @Override
    public int getRotationTileIndexEnd() {
        return mRotationTileIndexEnd;
    }

    @Override
    public int getRotationTileIndexLength() {
        return (mRotationTileIndexEnd - mRotationTileIndexStart) + 1;
    }

    @Override
    public boolean getRotationTileMirror() {
        return mRotationMirror;
    }

    @Override
    public float getPlacementOffsetX() {
        return mPlacementOffsetX;
    }

    @Override
    public float getPlacementOffsetY() {
        return mPlacementOffsetY;
    }
}