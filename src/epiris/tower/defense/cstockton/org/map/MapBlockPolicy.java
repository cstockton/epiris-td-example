package epiris.tower.defense.cstockton.org.map;

public class MapBlockPolicy {
    final private MapRange[] mMapRanges;

    public MapBlockPolicy(final MapRange ... pMapRanges) {
        mMapRanges = pMapRanges;
    }

    /**
     * @return the mMapRanges
     */
    public MapRange[] getMapRanges() {
        return mMapRanges;
    }

    public void injectPolicy(final boolean[][] pBools) {
        for(final MapRange mapRange : mMapRanges) {
            final int startCol = mapRange.getStartCol();
            final int startRow = mapRange.getStartRow();
            final int endCol = mapRange.getEndCol();
            final int endRow = mapRange.getEndRow();

            for (int x = startCol; x <= endCol; x++) {
                for (int y = startRow; y <= endRow; y++) {
                    pBools[x][y] = true;
                }
            }
        }
    }
}