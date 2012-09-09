package epiris.tower.defense.cstockton.org.map;

public class MapRange {
    private final int mStartCol;
    private final int mStartRow;
    private final int mEndCol;
    private final int mEndRow;

    public MapRange(final int pStartCol, final int pStartRow, final int pEndCol, final int pEndRow) {
        mStartCol = pStartCol;
        mStartRow = pStartRow;

        mEndCol = pEndCol;
        mEndRow = pEndRow;
    }

    /**
     * @return the mStartCol
     */
    public int getStartCol() {
        return mStartCol;
    }

    /**
     * @return the mStartRow
     */
    public int getStartRow() {
        return mStartRow;
    }

    /**
     * @return the mEndCol
     */
    public int getEndCol() {
        return mEndCol;
    }

    /**
     * @return the mEndRow
     */
    public int getEndRow() {
        return mEndRow;
    }
}