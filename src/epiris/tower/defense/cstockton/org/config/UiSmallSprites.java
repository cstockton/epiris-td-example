package epiris.tower.defense.cstockton.org.config;

public enum UiSmallSprites
{

    HEART (0, 0),
    GOLD (1, 0),
    SCORE (2, 0),
    GAMEPOINTS (3, 0),
    LEVEL (4, 0),

    // debuffs
    DEBUFF_ARCHER(0, 1),

    NONE (-1, -1);

    final public static String PATH = "sprites/uismall.png";
    final public static int WIDTH = 96;
    final public static int HEIGHT = 96;
    final public static int TILE_WIDTH = 16;
    final public static int TILE_HEIGHT = 16;
    final public static int COLS = WIDTH / TILE_WIDTH;
    final public static int ROWS = HEIGHT / TILE_HEIGHT;

    final private int mCol;
    final private int mRow;

    UiSmallSprites(final int pCol, final int pRow) {
        mCol = pCol;
        mRow = pRow;
    }

    public int getCol() {
        return this.mCol;
    }

    public int getRow() {
        return this.mRow;
    }
}