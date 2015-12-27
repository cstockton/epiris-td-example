package epiris.tower.defense.cstockton.org.map;

import java.util.ArrayList;
import java.util.Collections;

import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import epiris.tower.defense.cstockton.org.config.MapTypes;
import epiris.tower.defense.cstockton.org.game.GameLayer;
import epiris.tower.defense.cstockton.org.object.Registry;

public class Map {

    final public static int TILE_WIDTH = 64;
    final public static int TILE_HEIGHT = 64;

    final public int MAP_WIDTH;
    final public int MAP_HEIGHT;

    final public int ROW_COUNT;
    final public int COL_COUNT;

    final public int MAX_PATH_DEPTH;

    private final MapSpawnPolicy mMapSpawnPolicy;
    private final MapTypes mMapType;
    private final ArrayList<MapNode> mVisitedNodes = new ArrayList<MapNode>();
    private final ArrayList<MapNode> mOpenNodes = new ArrayList<MapNode>();
    private final MapNode[][] mNodes;
    private final MapPath mMapPathChecker = new MapPath();

    final public boolean[][] mBlockedTiles;
    final public boolean[][] mBlockedCreepTiles;
    final public boolean[][] mBlockedTowerTiles;

    public Map(final MapTypes pMapType, final TextureManager pTextureManager) {
        super();

        // assign the map type
        mMapType = pMapType;

        // assign the way points
        mMapSpawnPolicy = mMapType.getMapSpawnPolicy();

        // fetch the stitch policy
        final MapStitchPolicy mapStitchPolicy = mMapType.getMapStitchPolicy();

        int width = 0;
        int height = 0;

        // stitch together the map
        for(final MapPatch mapPatch : mapStitchPolicy.getMapPatches()) {

            // stitch together a map
            final BitmapTextureAtlas mapPatchTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
            final TextureRegion mapPatchTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mapPatchTexture, Registry.sGameActivity, mapPatch.getPath(), 0, 0);

            // load the texture
            pTextureManager.loadTextures(mapPatchTexture);

            // attach the sprite
            Registry.sScene.getChild(GameLayer.LAYER_MAP).attachChild(new Sprite(mapPatch.getX(), mapPatch.getY(), mapPatch.getWidth(), mapPatch.getHeight(), mapPatchTextureRegion));

            // inc width/height
            width += (mapPatch.getX() - width) + mapPatch.getWidth();
            height += (mapPatch.getY() - height) + mapPatch.getHeight();

        }

        // set some finals
        MAP_WIDTH = width;
        MAP_HEIGHT = height;
        COL_COUNT = MAP_WIDTH / TILE_WIDTH;
        ROW_COUNT = MAP_HEIGHT / TILE_HEIGHT;
        MAX_PATH_DEPTH = ROW_COUNT * COL_COUNT * 100;

        // create our blocked tiles boolean arrays, blocked tiles is what
        // is allowed to change by set/get, the other two are not modified
        mBlockedTiles = new boolean[COL_COUNT][ROW_COUNT];
        mBlockedCreepTiles = new boolean[COL_COUNT][ROW_COUNT];
        mBlockedTowerTiles = new boolean[COL_COUNT][ROW_COUNT];

        // set the blocked tower tiles
        mMapType.getTowerBlockPolicy().injectPolicy(mBlockedTowerTiles);

        // set the blocked creep tiles, these are shared with blocked tower tiles
        mMapType.getCreepBlockPolicy().injectPolicy(mBlockedCreepTiles);
        mMapType.getTowerBlockPolicy().injectPolicy(mBlockedCreepTiles);

        // block the spawns
        for(final MapSpawn mp : mMapSpawnPolicy.getMapSpawns()) {
            for(final int[] wp : mp.mWayPoints) {
                mBlockedTowerTiles[wp[0]][wp[1]] = true;
            }
        }

        // create our map node list
        mNodes = new MapNode[ROW_COUNT][COL_COUNT];

        // build our nodes
        for (int x = COL_COUNT - 1; x >= 0; x--) {
            for (int y = ROW_COUNT - 1; y >= 0; y--) {
                mNodes[y][x] = new MapNode(x, y);
            }
        }
    }

    public Map initialize() {
        return this;
    }

    public Map setTileBlocked(int pCol, int pRow) {
        mBlockedTiles[pCol][pRow] = true;

        if(!isCreepTileBlocked(pCol, pRow)) {
            Registry.sCreepManager.onTileChange(pCol, pRow, true);
        }
        return this;
    }

    public Map setTileFree(int pCol, int pRow) {
        mBlockedTiles[pCol][pRow] = false;

        if(!isCreepTileBlocked(pCol, pRow)) {
            Registry.sCreepManager.onTileChange(pCol, pRow, true);
        }
        return this;
    }

    public MapSpawnPolicy getMapSpawnPolicy() {
        return mMapSpawnPolicy;
    }

    /*
    public int[] getWayPoint(final int pIndex) {
        return mWayPoints[pIndex];
    }

    public int[][] getWayPoints() {
        return mWayPoints;
    }

    public int getWayPointCount() {
        return mWayPoints.length;
    }
    */

    public float getWidth() {
        return MAP_WIDTH;
    }

    public float getHeight() {
        return MAP_HEIGHT;
    }

    public int getColumnCount() {
        return COL_COUNT;
    }

    public int getRowCount() {
        return ROW_COUNT;
    }

    public int getTileWidth() {
        return TILE_WIDTH;
    }

    public int getTileHeight() {
        return TILE_HEIGHT;
    }

    public boolean isTileBlocked(int pCol, int pRow) {
        if (pCol < 0 || pRow < 0) {
            return true;

        } else if(pCol >= mBlockedTiles.length) {
            return true;

        } else if(pRow >= mBlockedTiles[pCol].length) {
            return true;

        }

        return mBlockedTiles[pCol][pRow];

    }

    public boolean isTileBlocked(final int pFromTileColumn, final int pFromTileRow, final int pToTileColumn, final int pToTileRow) {
        if (pToTileColumn < 0 || pToTileRow < 0 || pToTileColumn >= COL_COUNT || pToTileRow >= ROW_COUNT) {
            return true;

        } else if (pFromTileColumn == pToTileColumn && pFromTileRow == pToTileRow) {
            return true;

        }

        return isTileBlocked(pToTileColumn, pToTileRow);
    }

    public boolean isTowerTileBlocked(int pCol, int pRow) {
        if (pCol < 0 || pRow < 0) {
            return true;

        } else if(pCol >= mBlockedTowerTiles.length) {
            return true;

        } else if(pRow >= mBlockedTowerTiles[pCol].length) {
            return true;

        }

        return mBlockedTowerTiles[pCol][pRow];

    }

    public boolean isTowerTileBlocked(final int pFromTileColumn, final int pFromTileRow, final int pToTileColumn, final int pToTileRow) {
        if (pToTileColumn < 0 || pToTileRow < 0 || pToTileColumn >= COL_COUNT || pToTileRow >= ROW_COUNT) {
            return true;

        } else if (pFromTileColumn == pToTileColumn && pFromTileRow == pToTileRow) {
            return true;

        }

        return isTowerTileBlocked(pToTileColumn, pToTileRow);
    }

    public boolean isCreepTileBlocked(int pCol, int pRow) {
        if (pCol < 0 || pRow < 0) {
            return true;

        } else if(pCol >= mBlockedCreepTiles.length) {
            return true;

        } else if(pRow >= mBlockedCreepTiles[pCol].length) {
            return true;

        }

        return mBlockedCreepTiles[pCol][pRow];

    }

    public boolean isCreepTileBlocked(final int pFromTileColumn, final int pFromTileRow, final int pToTileColumn, final int pToTileRow) {
        if (pToTileColumn < 0 || pToTileRow < 0 || pToTileColumn >= COL_COUNT || pToTileRow >= ROW_COUNT) {
            return true;

        } else if (pFromTileColumn == pToTileColumn && pFromTileRow == pToTileRow) {
            return true;

        }

        return isCreepTileBlocked(pToTileColumn, pToTileRow);
    }

    public int[] getCoordsFromTiles(final int pCol, final int pRow) {
        return new int[] { pCol * TILE_WIDTH, pRow * TILE_HEIGHT };
    }

    // IF CHANGING ALSO SEE getpathfromcoords!
    public int[] getCenterCoordsFromTiles(final int pCol, final int pRow) {
        return new int[] { (pCol * TILE_WIDTH) + (TILE_WIDTH / 2), (pRow * TILE_HEIGHT) + (TILE_HEIGHT / 2) };
    }

    public int[] getTilesFromCoords(final int pX, final int pY) {
        return new int[] { pX > 0 ? pX / TILE_WIDTH : 0, pY > 0 ? pY / TILE_HEIGHT : 0 };
    }

    public MapPath getPathFromTiles(final MapPath pMapPath, final int pFromTileColumn, final int pFromTileRow, final int pToTileColumn, final int pToTileRow) {
        return getPathFromCoords(pMapPath, pFromTileColumn * TILE_WIDTH, pFromTileRow * TILE_HEIGHT, pToTileColumn, pToTileRow);
    }

    public MapPath getPathFromCoords(final MapPath pMapPath, final int pX, final int pY, final int pToTileColumn, final int pToTileRow) {

        final int[] fromTiles = getTilesFromCoords(pX, pY);

        // reset path
        pMapPath.reset();

        if (isTileBlocked(pToTileColumn, pToTileRow) || isCreepTileBlocked(pToTileColumn, pToTileRow)) {
            return pMapPath;
        }

        mOpenNodes.clear();
        mVisitedNodes.clear();

        final ArrayList<MapNode> openNodes = mOpenNodes;
        final ArrayList<MapNode> visitedNodes = mVisitedNodes;

        final MapNode[][] nodes = mNodes;
        final MapNode fromNode = nodes[fromTiles[1]][fromTiles[0]];
        final MapNode toNode = nodes[pToTileRow][pToTileColumn];

        fromNode.mCost = 0;
        fromNode.mDepth = 0;
        toNode.mParent = null;

        visitedNodes.clear();

        openNodes.clear();
        openNodes.add(fromNode);

        int currentDepth = 0;
        while (currentDepth < MAX_PATH_DEPTH && !openNodes.isEmpty()) {

            final MapNode current = openNodes.remove(0);
            if (current == toNode) {
                break;
            }

            visitedNodes.add(current);

            for (int dX = -1; dX <= 1; dX++) {
                for (int dY = -1; dY <= 1; dY++) {
                    if ((dX == 0) && (dY == 0)) {
                        continue;
                    }
                    if ((dX != 0) && (dY != 0)) {
                        continue;
                    }

                    final int neighborTileColumn = dX + current.mTileColumn;
                    final int neighborTileRow = dY + current.mTileRow;

                    if (!isTileBlocked(fromTiles[0], fromTiles[1], neighborTileColumn, neighborTileRow) && !isCreepTileBlocked(fromTiles[0], fromTiles[1], neighborTileColumn, neighborTileRow)) {
                        final float neighborCost = current.mCost;
                        final MapNode neighbor = nodes[neighborTileRow][neighborTileColumn];

                        if (neighborCost < neighbor.mCost) {
                            if (openNodes.contains(neighbor)) {
                                openNodes.remove(neighbor);
                            }
                            if (visitedNodes.contains(neighbor)) {
                                visitedNodes.remove(neighbor);
                            }
                        }

                        if (!openNodes.contains(neighbor) && !(visitedNodes.contains(neighbor))) {
                            neighbor.mCost = neighborCost;
                            if (neighbor.mCost <= MAX_PATH_DEPTH) {

                                final float dXa = pToTileColumn - neighborTileColumn;
                                final float dYa = pToTileRow - neighborTileRow;
                                final float dDi = (float)(dXa * dXa + dYa * dYa);
                                neighbor.mExpectedRestCost = (float)Math.sqrt(dDi);

                                currentDepth = Math.max(currentDepth, neighbor.setParent(current));
                                openNodes.add(neighbor);

                                Collections.sort(openNodes);
                            }
                        }
                    }
                }
            }
        }

        if (toNode.mParent == null) {
            return pMapPath;
        }

        // set the last map node
        MapNode tmp = nodes[pToTileRow][pToTileColumn];

        // walk it backwards, prepending the tiles
        while (tmp != fromNode) {
            pMapPath.prepend(tmp.mTileColumn * TILE_WIDTH, tmp.mTileRow * TILE_HEIGHT);
            tmp = tmp.mParent;
        }

        return pMapPath;
    }

    public boolean checkPaths(final int[][] pPaths, final int blockCol, final int blockRow) {
        final int length = pPaths.length ;
        boolean checking = true;
        int index = 0;

        if(length < 2) {
            return false;
        }

        while(checking) {

            mBlockedTiles[blockCol][blockRow] = true;
            getPathFromTiles(mMapPathChecker, pPaths[index][0], pPaths[index][1], pPaths[index + 1][0], pPaths[index + 1][1]);
            mBlockedTiles[blockCol][blockRow] = false;

            if(mMapPathChecker.getLength() == 0) {
                return false;
            }

            if(++index >= (length - 1)) {
                return true;
            }
        }

        return true;
    }

    public boolean checkPaths(final int blockCol, final int blockRow) {
        for(final MapSpawn mp : mMapSpawnPolicy.getMapSpawns()) {
            if(!checkPaths(mp.mWayPoints, blockCol, blockRow)) {
                return false;
            }
        }

        return true;
    }

    private static class MapNode implements Comparable<MapNode> {

        MapNode mParent;
        int mDepth;

        final int mTileColumn;
        final int mTileRow;

        float mCost;
        float mExpectedRestCost;

        public MapNode(final int pTileColumn, final int pTileRow) {
            mTileColumn = pTileColumn;
            mTileRow = pTileRow;
        }

        public int setParent(final MapNode parent) {
            mDepth = parent.mDepth + 1;
            mParent = parent;

            return mDepth;
        }

        @Override
        public int compareTo(final MapNode pOther) {
            final float totalCost = mExpectedRestCost + mCost;
            final float totalCostOther = pOther.mExpectedRestCost + pOther.mCost;

            if (totalCost < totalCostOther) {
                return -1;
            } else if (totalCost > totalCostOther) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}