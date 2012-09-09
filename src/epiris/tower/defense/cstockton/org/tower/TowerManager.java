package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Debug;

import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.object.Registry;

import java.util.ArrayList;
import java.util.Random;

public class TowerManager {

    // tower upgrades control used for dragging towers
    static public boolean ENABLE_TOWER_INTERACTION = true;

    // tower width/height
    static public final int TOWER_WIDTH = 64;
    static public final int TOWER_HEIGHT = 64;

    // managed towers list and their texture regions
    private final ArrayList<Tower> mTowers = new ArrayList<Tower>();
    private final TiledTextureRegion[] mTowerTiledTextureRegions;
    private final TiledTextureRegion mTowerMenuTiledTextureRegion;

    // tower upgrader
    private final TowerUpgrader mTowerUpgrader;

    // tower info
    private final TowerInfo mTowerInfo;

    // tower placer @TODO redesign to be final / re-usable
    private final TowerPlacer mTowerPlacer;

    // circle marker for towers
    private final TextureRegion mTowerCircleTextureRegion;

    // array of blocked tiles
    //final public boolean[][] mBlockedTowerTiles;

    public TowerManager(
        final TiledTextureRegion[] pTowerTiledTextureRegions,
        final TiledTextureRegion pTowerMenuTiledTextureRegion,
        final TextureRegion pTowerCircleTextureRegion
    ) {
        mTowerTiledTextureRegions = pTowerTiledTextureRegions;

        mTowerMenuTiledTextureRegion = pTowerMenuTiledTextureRegion;
        mTowerCircleTextureRegion = pTowerCircleTextureRegion;
        mTowerUpgrader = new TowerUpgrader();
        mTowerInfo = new TowerInfo(this, pTowerMenuTiledTextureRegion.deepCopy());
        mTowerPlacer = new TowerPlacer(this);

        //mBlockedTowerTiles = new boolean[Registry.sMap.COL_COUNT][Registry.sMap.ROW_COUNT];
    }

    /*
    public void setTowerTileBlocked(int pCol, int pRow) {
        mBlockedTowerTiles[pCol][pRow] = true;
        Registry.sCreepManager.onTileChange(pCol, pRow, true);
    }

    public void setTowerTileFree(int pCol, int pRow) {
        mBlockedTowerTiles[pCol][pRow] = false;
        Registry.sCreepManager.onTileChange(pCol, pRow, false);
    }
    */

    public TiledTextureRegion getTowerTiledTextureRegionClone(final ITowerSprites pTowerSprite) {
        Debug.i("getTowerTiledTextureRegionClone id=" + pTowerSprite.getId());
        return mTowerTiledTextureRegions[pTowerSprite.getId()].deepCopy();
    }

    public TiledTextureRegion getTowerMenuTiledTextureRegionClone() {
        return mTowerMenuTiledTextureRegion.deepCopy();
    }

    public TextureRegion getTowerCircleTextureRegion() {
        return mTowerCircleTextureRegion;
    }

    public Tower getTowerFromTile(final int pCol, final int pRow) {

        // now iterate all towers
        for (final Tower tower : mTowers) {
            if(pCol == tower.mTowerCol && pRow == tower.mTowerRow) {
                return tower;
            }
        }

        return null;
    }

    public void onUpdate(final float pSecondsElapsed) {
        //Debug.d("TowerManager :: onUpdate :: this.mTowers.size(" + this.mTowers.size() + ")");

        // now iterate all towers
        for (final Tower towerBase : mTowers) {

            // make sure the tower is ready to attack before we add attackables
            if (towerBase.isTowerReady(pSecondsElapsed)) {

                // clear targetables
                towerBase.mSpellTargetables.clear();

                // fill the targetables
                Registry.sCreepManager.getSpellTargetablesInRange(towerBase.getX(), towerBase.getY(), towerBase.getTowerRange(), towerBase.mSpellTargetables);

                // attack!
                towerBase.attack();

            }
        }

        // update tower placer if needed
        if(mTowerPlacer.isActive()) {
            mTowerPlacer.update();
        }
    }

    public Tower getTowerByType(final TowerTypes pTowerType) {
        Debug.i("TowerManager :: getTowerByType :: pTowerType(" + pTowerType + ")");

        switch (pTowerType) {

            default: {
                return new Tower(this, pTowerType);
            }
        }
    }

    synchronized public boolean startTowerPlacement(final TowerTypes pTowerType) {
        Debug.i("TowerManager :: startTowerPlacement :: pTowerType(" + pTowerType + ")");

        if (Registry.sLocalPlayer.getGold() < pTowerType.getCost()) {
            return false;
        }

        // create a tower placer
        mTowerPlacer.activate(pTowerType, -10000, -10000);

        // notify runner of our touch mode
        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_TOWER);

        // hide the hud while dragging
        Registry.sHud.hide();

        // stop the upgrader
        TowerManager.ENABLE_TOWER_INTERACTION = false;

        return true;
    }

    synchronized public boolean endTowerPlacement() {

        // check to see if we can do this
        if(TowerManager.ENABLE_TOWER_INTERACTION) {
            return false;
        }

        // check if they even have enough gold
        if (Registry.sLocalPlayer.getGold() < mTowerPlacer.getTowerType().getCost()) {
            return false;
        }

        // make sure this drop zone is valid
        if (mTowerPlacer.isDropzoneValid()) {

            // get a tower of the placed type
            final Tower towerBase = mTowerPlacer.getSubjectTower();

            // place the tower
            towerBase.place(
                mTowerPlacer.getCurrentX(),
                mTowerPlacer.getCurrentY()
            );

            // add this tower to the tower managers link list
            mTowers.add(towerBase);

            // deduct the gold
            Registry.sLocalPlayer.deductGold(mTowerPlacer.getTowerType().getCost());

        } else {

            // could not place tower there!
            Registry.sGameActivity.userNotifyShort("Invalid tower location!");

        }

        // destroy this in the current tower placer
        mTowerPlacer.deactivate();

        // re-enable upgrades
        TowerManager.ENABLE_TOWER_INTERACTION = true;

        // notify runner that scroll mode should be camera again
        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_CAMERA);

        // show the hud now
        Registry.sHud.show();

        return true;
    }

    public TowerPlacer getTowerPlacer() {
        return mTowerPlacer;
    }

    public TowerUpgrader getTowerUpgrader() {
        return mTowerUpgrader;
    }

    public TowerInfo getTowerInfo() {
        return mTowerInfo;
    }

    public Tower getRandomTower() {
        return mTowers.get(new Random().nextInt(mTowers.size()));
    }

    // @TODO use this
    public boolean buyTower(final Tower pTowerBase) {
        Registry.sLocalPlayer.addGold(pTowerBase.getTowerCost());
        mTowers.add(pTowerBase);

        return true;
    }

    public boolean sellTower() {
        return sellTower(getRandomTower());
    }

    public boolean sellTower(final Tower pTowerBase) {
        Registry.sLocalPlayer.addGold(pTowerBase.getTowerRefund());
        pTowerBase.destroy();
        mTowers.remove(pTowerBase);

        return true;
    }

    public boolean upgrade(final Tower pTowerBase, final TowerTypes pTowerType) {

        // create the new tower
        final Tower newTower = getTowerByType(pTowerType);

        // get the new coords
        final int[] coords = Registry.sMap.getCoordsFromTiles(pTowerBase.getTowerCol(), pTowerBase.getTowerRow());

        newTower.place(coords[0], coords[1]);
        pTowerBase.destroy(true);

        Registry.sLocalPlayer.deductGold(pTowerType.getCost());
        mTowers.remove(pTowerBase);
        mTowers.add(newTower);

        return true;
    }
}