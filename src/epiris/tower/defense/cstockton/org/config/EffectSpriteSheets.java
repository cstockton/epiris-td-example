package epiris.tower.defense.cstockton.org.config;

public enum EffectSpriteSheets
{
    EFFECTS64_01("effects_tower_spells.png", 1024, 1024, 16, 16),
    EFFECTS64_02("effects_creep_spells.png", 1024, 1024, 16, 16),

    EFFECTS16 ("effects16.png", 128, 128, 8, 8),
    EFFECTS32 ("effects32.png", 512, 512, 16, 16),
    EFFECTS256x128 ("effects256x128.png", 1024, 1024, 4, 8),

    ;

    private final String mSheet;

    private final int mWidth;
    private final int mHeight;
    private final int mCols;
    private final int mRows;

    EffectSpriteSheets(final String pSheet, final int pWidth, final int pHeight, final int pCols, final int pRows) {
        mSheet = pSheet;

        mWidth = pWidth;
        mHeight = pHeight;
        mCols = pCols;
        mRows = pRows;
    }

    public String getSheet() {
        return mSheet;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getTileWidth() {
        return mWidth / mCols;
    }

    public int getTileHeight() {
        return mHeight / mRows;
    }

    public int getCols() {
        return mCols;
    }

    public int getRows() {
        return mRows;
    }
}