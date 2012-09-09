package epiris.tower.defense.cstockton.org.map;

public class MapStitchPolicy {
    final private MapPatch[] mMapStitches;

    public MapStitchPolicy(final MapPatch ... pMapStitches) {
        mMapStitches = pMapStitches;
    }

    /**
     * @return the mMapStitches
     */
    public MapPatch[] getMapPatches() {
        return mMapStitches;
    }
}