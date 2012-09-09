package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.sprite.ISpriteSheet;

public enum SpriteSheets implements ISpriteSheet {
    UI_64x64_01 ("ui64x64.png", 1024, 1024, 16, 16),
    UI_96x96_01 ("ui96x96.png", 512, 512, 5, 5),
    UI_128x128_01 ("ui128x128.png", 1024, 1024, 8, 8),

    ;

    private final String mSheet;

    private final int mWidth;
    private final int mHeight;
    private final int mCols;
    private final int mRows;

    SpriteSheets(final String pSheet, final int pWidth, final int pHeight, final int pCols, final int pRows) {
        mSheet = pSheet;

        mWidth = pWidth;
        mHeight = pHeight;
        mCols = pCols;
        mRows = pRows;
    }

    @Override
    public String getSheet() {
        return mSheet;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public int getTileWidth() {
        return mWidth / mCols;
    }

    @Override
    public int getTileHeight() {
        return mHeight / mRows;
    }

    @Override
    public int getCols() {
        return mCols;
    }

    @Override
    public int getRows() {
        return mRows;
    }
}