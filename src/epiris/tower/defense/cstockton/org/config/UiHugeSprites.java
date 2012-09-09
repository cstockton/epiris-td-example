package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.sprite.ISpriteSheet;

public enum UiHugeSprites
{

    BORDER_1 (0, 0, SpriteSheets.UI_128x128_01),
    BORDER_2 (0, 1, SpriteSheets.UI_128x128_01),
    NONE (-1, -1, SpriteSheets.UI_128x128_01);

    final private ISpriteSheet mSpriteSheet;
    final private int mCol;
    final private int mRow;

    UiHugeSprites(
            final int pCol,
            final int pRow,
            final ISpriteSheet pSpriteSheet
        )
    {
        mCol = pCol;
        mRow = pRow;
        mSpriteSheet = pSpriteSheet;
    }

    public ISpriteSheet getSpriteSheet() {
        return mSpriteSheet;
    }

    public int getCol() {
        return mCol;
    }

    public int getRow() {
        return mRow;
    }
}