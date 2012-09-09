package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;

import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class TowerPlacer {

    final private TowerManager mTowerManager;
    final public Rectangle mPositionMarker;
    final public Sprite mRadiusMarker;

    public TowerTypes mTowerType;
    public Tower mSubjectTower;

    public boolean mIsDropzoneValid = false;
    public boolean mActive = false;

    public float mTouchX = 0f;
    public float mTouchY = 0f;

    public int mCurrentX;
    public int mCurrentY;
    public int mCurrentTileCol;
    public int mCurrentTileRow;
    public int mLastTileCol;
    public int mLastTileRow;

    TowerPlacer(final TowerManager pTowerManager) {
        mTowerManager = pTowerManager;

        // create our position marker
        mPositionMarker = new Rectangle(0, 0, TowerManager.TOWER_WIDTH, TowerManager.TOWER_HEIGHT);
        mPositionMarker.setColor(1, 0, 0, 0.25f);
        mPositionMarker.setVisible(false);
        mPositionMarker.setIgnoreUpdate(true);

        // setup the radius marker
        mRadiusMarker = new Sprite(0, 0, mTowerManager.getTowerCircleTextureRegion().deepCopy());
        mRadiusMarker.setAlpha(0.25f);
        mRadiusMarker.setColor(.25f, .25f, .25f);
        mRadiusMarker.setVisible(false);
        mRadiusMarker.setIgnoreUpdate(true);

        // attach the radius marker to the position marker, then to the scene
        mPositionMarker.attachChild(mRadiusMarker);

    }

    public TowerPlacer activate(final TowerTypes pTowerType, final int pInitialX, final int pInitialY) {

        // set the current tower type
        mTowerType = pTowerType;

        // create a tower
        mSubjectTower = mTowerManager.getTowerByType(pTowerType);

        // update the position
        mPositionMarker.setPosition(pInitialX, pInitialY);

        // set the position of the marker
        mRadiusMarker.setPosition(
            (-mSubjectTower.getTowerRange()) + (TowerManager.TOWER_WIDTH / 2),
            (-mSubjectTower.getTowerRange()) + (TowerManager.TOWER_HEIGHT / 2)
        );

        // set radius marker width and height
        mRadiusMarker.setWidth(mSubjectTower.getTowerRange() * 2);
        mRadiusMarker.setHeight(mSubjectTower.getTowerRange() * 2);

        // turn on updates and visibility
        mPositionMarker.setIgnoreUpdate(false);
        mPositionMarker.setVisible(true);

        // setup the radius marker
        mRadiusMarker.setIgnoreUpdate(false);
        mRadiusMarker.setVisible(true);

        // attach the radius marker to the position marker, then to the scene
        Registry.sSceneLayerTowers.attachChild(mPositionMarker);

        // set the upgrader to active
        mActive = true;

        return this;
    }

    public Tower getSubjectTower() {
        return mSubjectTower;
    }

    public TowerPlacer deactivate() {
        mActive = false;

        // turn off updates and visibility
        mPositionMarker.setIgnoreUpdate(true);
        mPositionMarker.setVisible(false);
        mRadiusMarker.setIgnoreUpdate(true);
        mRadiusMarker.setVisible(false);

        // detach the radius marker to the position marker, then to
        // the scene
        Registry.sSceneLayerTowers.detachChild(mPositionMarker);

        return this;
    }

    public boolean isActive() {
        return mActive;
    }

    public TowerTypes getTowerType() {
        return mTowerType;
    }

    public int getCurrentX() {
        return mCurrentX;
    }

    public int getCurrentY() {
        return mCurrentY;
    }

    public int getCurrentTileCol() {
        return mCurrentTileCol;
    }

    public int getCurrentTileRow() {
        return mCurrentTileRow;
    }

    public boolean isDropzoneValid() {
        return mIsDropzoneValid;
    }

    public void cancel() {
        mPositionMarker.setPosition(-1000f, -1000f);
        mPositionMarker.setColor(1, 1, 1, 1.0f);
    }

    public void update() {
        //Debug.i("TowerPlacer :: update :: mCurrentX=" + mCurrentX + " mCurrentY=" + mCurrentY + " mPositionMarker=" + mPositionMarker + "");

        if (null != mPositionMarker) {

            final int[] tiles = Registry.sMap.getTilesFromCoords((int) mTouchX, (int) (mTouchY - (164 / (Registry.sGameActivity.getCamera().getZoomFactor() * 1.5))));

            mCurrentTileCol = tiles[0];
            mCurrentTileRow = tiles[1];

            if(mLastTileCol != mCurrentTileCol || mCurrentTileRow != mLastTileRow) {
                final int[] coords = Registry.sMap.getCoordsFromTiles(tiles[0], tiles[1]);

                mLastTileCol = mCurrentTileCol;
                mLastTileRow = mCurrentTileRow;
                mCurrentX = coords[0];
                mCurrentY = coords[1];

                mIsDropzoneValid = !Registry.sMap.isTileBlocked(mCurrentTileCol, mCurrentTileRow) &&
                        !Registry.sMap.isTowerTileBlocked(mCurrentTileCol, mCurrentTileRow) &&
                        Registry.sMap.checkPaths(mCurrentTileCol, mCurrentTileRow);

                if (!mIsDropzoneValid) {
                    mPositionMarker.setColor(1, 0, 0, 1.0f);
                } else {
                    mPositionMarker.setColor(1, 1, 1, 1.0f);
                }

                mPositionMarker.setPosition(mCurrentX, mCurrentY);
            }
        }
    }

    public void setUpdateCoords(float x, float y) {
        mTouchX = x;
        mTouchY = y;
        //Debug.i("TowerPlacer :: setUpdateCoords(" + x + ", " + y + ")");
    }
}