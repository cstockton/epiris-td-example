package epiris.tower.defense.cstockton.org.config;

public enum UiLargeSprites
{

    TOWER_PHYSICAL (0, 0),
    TOWER_PHYSICAL_ARCHER (0, 1),
    TOWER_PHYSICAL_ARCHER_MEDIUM (0, 2),
    TOWER_PHYSICAL_ARCHER_HEAVY (0, 3),

    TOWER_MAGICAL (1, 0),
    TOWER_MAGICAL_MAGE (1, 1),
    TOWER_MAGICAL_MAGE_MEDIUM (1, 2),
    TOWER_MAGICAL_MAGE_HEAVY (1, 3),

    TOWER_PURE (2, 0),
    TOWER_PURE_LIGHTNING (2, 1),
    TOWER_PURE_LIGHTNING_MEDIUM (2, 2),
    TOWER_PURE_LIGHTNING_HEAVY (2, 3),

    PLAY (3, 0),
    FAST (4, 0),
    SKIP (5, 0),
    SETTINGS (3, 1),

    /*
    ARCHER_TOWER (0, 0),
    ICE_ARCHER_TOWER (0, 1),

    MAGE_TOWER (1, 0),
    ELEMENTAL_TOWER (2, 0),

    CANCEL (3, 0),
    SELL (4, 0),

    SETTINGS (5, 0),

    ZOOMOUT (3, 3),
    ZOOMIN (4, 3),

    PLAY (3, 4),
    PAUSE (4, 4),
    FAST (5, 4),
    SKIP (3, 5),
    */

    NONE (-1, -1);

    final public static String PATH = "sprites/uilarge.png";
    final public static int WIDTH = 1024;
    final public static int HEIGHT = 1024;
    final public static int TILE_WIDTH = 64;
    final public static int TILE_HEIGHT = 64;
    final public static int COLS = WIDTH / TILE_WIDTH;
    final public static int ROWS = HEIGHT / TILE_HEIGHT;

    final private int mCol;
    final private int mRow;

    UiLargeSprites(
            final int pCol,
            final int pRow
        )
    {
        this.mCol = pCol;
        this.mRow = pRow;
    }

    public int getCol()
    {
        return this.mCol;
    }

    public int getRow()
    {
        return this.mRow;
    }
}