package epiris.tower.defense.cstockton.org.map;

public class MapSpawnPolicy {
    final private MapSpawn[] mMapSpawns;
    final private boolean mAllowConcurrent;

    public MapSpawnPolicy(final MapSpawn ... pMapSpawns) {
        this(false, pMapSpawns);
    }

    public MapSpawnPolicy(final boolean pAllowConcurrent, final MapSpawn ... pMapSpawns) {
        mAllowConcurrent = pAllowConcurrent;
        mMapSpawns = pMapSpawns;
    }

    public boolean isConcurrent() {
        return mAllowConcurrent;
    }

    /**
     * @return the mMapStitches
     */
    public MapSpawn[] getMapSpawns() {
        return mMapSpawns;
    }
}