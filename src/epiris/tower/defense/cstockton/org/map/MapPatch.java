package epiris.tower.defense.cstockton.org.map;

public class MapPatch {
    private final String mPath;
    private final int mX;
    private final int mY;
    private final int mWidth;
    private final int mHeight;

    public MapPatch(final String pPath, final int pX, final int pY, final int pWidth, final int pHeight) {
        mPath = pPath;
        mX = pX;
        mY = pY;
        mWidth = pWidth;
        mHeight = pHeight;
    }

    /**
     * @return the mPath
     */
    public String getPath() {
        return mPath;
    }

    /**
     * @return the mX
     */
    public int getX() {
        return mX;
    }

    /**
     * @return the mY
     */
    public int getY() {
        return mY;
    }

    /**
     * @return the mWidth
     */
    public int getWidth() {
        return mWidth;
    }

    /**
     * @return the mHeight
     */
    public int getHeight() {
        return mHeight;
    }
}