package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.util.Debug;

public enum CreepSprites {

    // creeps 01 col1
    DARK_KNIGHT(0, 0, CreepSpriteSheets.CREEPS01),
    POOR_WIZARD(0, 4, CreepSpriteSheets.CREEPS01),
    WHITE_WIZARD(0, 8, CreepSpriteSheets.CREEPS01),
    GREEN_WIZARD(0, 12, CreepSpriteSheets.CREEPS01),

    // creeps 01 col2
    WHITE_VIKING(8, 0, CreepSpriteSheets.CREEPS01),
    ICE_TROLL(8, 4, CreepSpriteSheets.CREEPS01),
    LAVA_TROLL(8, 8, CreepSpriteSheets.CREEPS01),
    BLUE_KNIGHT(8, 12, CreepSpriteSheets.CREEPS01),

    // creeps 02 col1
    RED_WIZARD(0, 0, CreepSpriteSheets.CREEPS02),
    BLUE_ARCHER(0, 4, CreepSpriteSheets.CREEPS02),
    GREEN_ARCHER(0, 8, CreepSpriteSheets.CREEPS02),
    GREEN_TROLL(0, 12, CreepSpriteSheets.CREEPS02),

    ;

    private final int mSpriteCol;
    private final int mSpriteRow;
    private final CreepSpriteSheets mCreepSpriteSheet;

    static private final int WIDTH = 64;
    static private final int HEIGHT = 64;

    static private final int SPRITE_WIDTH = 1024;
    static private final int SPRITE_HEIGHT = 1024;

    static private final int SPRITE_COLS = SPRITE_WIDTH / WIDTH;
    static private final int SPRITE_ROWS = SPRITE_HEIGHT / HEIGHT;

    static private final int FRAME_LENGTH = 8;
    static private final int FRAME_TIME = 125;

    static private final int RIGHT = 0;
    static private final int LEFT = 1;
    static private final int DOWN = 2;
    static private final int UP = 3;

    static public CreepSprites getFromString(final String pCreepSprite) {
        for(final CreepSprites creepSprite : CreepSprites.values()) {
            if(pCreepSprite.equals(creepSprite.name())) {
                return creepSprite;
            }
        }

        Debug.w("Could not find a creep type by name: " + pCreepSprite);

        return null;
    }

    static public int getWidth() {
        return WIDTH;
    }

    static public int getHeight() {
        return HEIGHT;
    }

    static public int getSpriteWidth() {
        return SPRITE_WIDTH;
    }

    static public int getSpriteHeight() {
        return SPRITE_HEIGHT;
    }

    static public int getSpriteCols() {
        return SPRITE_COLS;
    }

    static public int getSpriteRows() {
        return SPRITE_ROWS;
    }

    static public int getFrameLength() {
        return FRAME_LENGTH;
    }

    static public int getFrameTime() {
        return FRAME_TIME;
    }

    static public long[] getAnimationFrameDurations() {
        return new long[] { FRAME_TIME, FRAME_TIME, FRAME_TIME, FRAME_TIME, FRAME_TIME, FRAME_TIME, FRAME_TIME, FRAME_TIME };
    }

    CreepSprites(final int pSpriteCol, final int pSpriteRow, final CreepSpriteSheets pCreepSpriteSheet) {
        mSpriteCol = pSpriteCol;
        mSpriteRow = pSpriteRow;
        mCreepSpriteSheet = pCreepSpriteSheet;
    }

    public int getCol() {
        return mSpriteCol;
    }

    public int getRow() {
        return mSpriteRow;
    }

    public CreepSpriteSheets getSpriteSheet() {
        return mCreepSpriteSheet;
    }

    public int getUpAnimationTileIndex() {
        return (mSpriteRow + UP) * SPRITE_COLS + mSpriteCol;
    }

    public int getUpAnimationEndTileIndex() {
        return ((mSpriteRow + UP) * SPRITE_COLS + mSpriteCol) + (FRAME_LENGTH - 1);
    }

    public int getRightAnimationTileIndex() {
        return (mSpriteRow + RIGHT) * SPRITE_COLS + mSpriteCol;
    }

    public int getRightAnimationEndTileIndex() {
        return ((mSpriteRow + RIGHT) * SPRITE_COLS + mSpriteCol) + (FRAME_LENGTH - 1);
    }

    public int getDownAnimationTileIndex() {
        return (mSpriteRow + DOWN) * SPRITE_COLS + mSpriteCol;
    }

    public int getDownAnimationEndTileIndex() {
        return ((mSpriteRow + DOWN) * SPRITE_COLS + mSpriteCol) + (FRAME_LENGTH - 1);
    }

    public int getLeftAnimationTileIndex() {
        return (mSpriteRow + LEFT) * SPRITE_COLS + mSpriteCol;
    }

    public int getLeftAnimationEndTileIndex() {
        return ((mSpriteRow + LEFT) * SPRITE_COLS + mSpriteCol) + (FRAME_LENGTH - 1);
    }
}