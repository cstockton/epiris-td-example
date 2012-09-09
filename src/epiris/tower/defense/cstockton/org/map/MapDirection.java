package epiris.tower.defense.cstockton.org.map;

public enum MapDirection {

    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);

    private final int mDeltaX;
    private final int mDeltaY;

    private MapDirection(final int pDeltaX, final int pDeltaY) {
        this.mDeltaX = pDeltaX;
        this.mDeltaY = pDeltaY;
    }

    public static MapDirection fromCoords(final float pSourceX, final float pSourceY, final float pTargetX, final float pTargetY) {
        if(pSourceX > pTargetX) {
            return LEFT;
        } else if(pSourceX < pTargetX) {
            return RIGHT;
        } else if(pSourceY > pTargetY) {
            return UP;
        } else {
            return DOWN;
        }
    }

    public static MapDirection fromDelta(final int pDeltaX, final int pDeltaY) {
        if(pDeltaX == 0) {
            if(pDeltaY == 1) {
                return DOWN;
            } else if(pDeltaY == -1) {
                return UP;
            }
        } else if (pDeltaY == 0) {
            if(pDeltaX == 1) {
                return RIGHT;
            } else if(pDeltaX == -1) {
                return LEFT;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getDeltaX() {
        return this.mDeltaX;
    }

    public int getDeltaY() {
        return this.mDeltaY;
    }
}