package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.map.MapBlockPolicy;
import epiris.tower.defense.cstockton.org.map.MapPatch;
import epiris.tower.defense.cstockton.org.map.MapRange;
import epiris.tower.defense.cstockton.org.map.MapSpawn;
import epiris.tower.defense.cstockton.org.map.MapSpawnPolicy;
import epiris.tower.defense.cstockton.org.map.MapStitchPolicy;

public enum MapTypes
{

    THE_VALLEY (

        "The Valley",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(new MapSpawn(new int[][] { new int[] {5, 0}, new int[] {15, 10} })),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(4, 0, 4, 0), // left edges
            new MapRange(0, 1, 4, 1),
            new MapRange(0, 2, 0, 2),
            new MapRange(0, 6, 0, 7),
            new MapRange(1, 8, 1, 8),

            new MapRange(0, 11, 1, 11), // bottom edges
            new MapRange(0, 15, 2, 15),
            new MapRange(4, 14, 4, 14),
            new MapRange(6, 14, 6, 14),
            new MapRange(7, 15, 12, 15),
            new MapRange(13, 14, 13, 14),

            new MapRange(6, 0, 6, 0), // top right edges
            new MapRange(6, 1, 8, 1),
            new MapRange(9, 0, 13, 0),
            new MapRange(12, 1, 12, 1),
            new MapRange(14, 1, 15, 1),

            new MapRange(15, 1, 15, 4), // right wall

            new MapRange(9, 5, 9, 7), // island
            new MapRange(10, 7, 15, 7),
            new MapRange(12, 8, 12, 8)

        ),

        // creep block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(0, 0, 0, 15), // far left cliff
            new MapRange(0, 1, 0, 2), // far left cliff
            new MapRange(0, 8, 1, 11), // far left cliff - sticking out
            new MapRange(0, 0, 4, 1), // left of spawn point
            new MapRange(6, 0, 8, 1), // right of spawn point - first chunk
            new MapRange(9, 0, 11, 0), // right of spawn point - indent
            new MapRange(12, 0, 12, 1), // right of spawn point - sticking point part
            new MapRange(13, 0, 13, 0), // right of spawn point - another indent
            new MapRange(14, 0, 15, 1), // right of spawn point - last chunk
            new MapRange(9, 5, 15, 7), // island
            new MapRange(12, 8, 12, 8), // chunk sticking out in island
            new MapRange(0, 15, 15, 15), // bottom
            new MapRange(4, 14, 6, 15), // bottom - first chunk
            new MapRange(13, 14, 15, 15), // bottom - incline part 1
            new MapRange(15, 13, 15, 13) // bottom - incline part 2

        ),

        // map stitch policy
        new MapStitchPolicy(new MapPatch("maps/the_valley.png", 0, 0, 1024, 1024))

    ),

    THE_ARENA (

        "The Arena",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(false, new MapSpawn(new int[][] { new int[] {7, 0}, new int[] {7, 15} }), new MapSpawn(new int[][] { new int[] {8, 0}, new int[] {8, 15} })),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(3, 0, 5, 1), // left side
            new MapRange(2, 1, 2, 1),
            new MapRange(1, 1, 1, 14), // block all the way to the bottom
            new MapRange(2, 14, 5, 14),
            new MapRange(6, 15, 6, 15), // last chunk

            new MapRange(10, 0, 12, 1), // right side
            new MapRange(13, 1, 13, 1),
            new MapRange(14, 1, 14, 14), // block all the way to the bottom
            new MapRange(10, 14, 14, 14),
            new MapRange(9, 15, 9, 15) // last chunk

        ),

        // creep block policy // tower blocks inerhit cover everything
        new MapBlockPolicy(),

        // map stitch policy
        new MapStitchPolicy(new MapPatch("maps/the_arena.png", 0, 0, 1024, 1024))

    ),

    THE_PATHWAY (

        "The Pathway",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(false, new MapSpawn(new int[][] { new int[] {7, 0}, new int[] {7, 15} }), new MapSpawn(new int[][] { new int[] {8, 0}, new int[] {8, 15} })),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(1, 0, 1, 4), // left edges
            new MapRange(2, 5, 2, 5),
            new MapRange(3, 5, 3, 12),
            new MapRange(4, 13, 4, 13),
            new MapRange(5, 13, 5, 15),

            // startCol, startRow, endCol, endRow
            new MapRange(14, 0, 14, 4), // left edges
            new MapRange(13, 5, 13, 5),
            new MapRange(12, 5, 12, 12),
            new MapRange(11, 13, 11, 13),
            new MapRange(10, 13, 10, 15)

        ),

        // creep block policy
        new MapBlockPolicy(),

        // map stitch policy
        new MapStitchPolicy(new MapPatch("maps/the_pathway.png", 0, 0, 1024, 1024))

    ),

    BRICKS_OF_BLOOD (

        "Bricks of Blood",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(false, new MapSpawn(new int[][] { new int[] {7, 0}, new int[] {7, 15} }), new MapSpawn(new int[][] { new int[] {8, 0}, new int[] {8, 15} })),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(0, 2, 4, 2), // left side
            new MapRange(4, 0, 4, 2),

            new MapRange(11, 0, 11, 2), // right side
            new MapRange(11, 2, 15, 2)
        ),

        // creep block policy
        new MapBlockPolicy(),

        // map stitch policy
        new MapStitchPolicy(new MapPatch("maps/bricks_of_blood.png", 0, 0, 1024, 1024))

    ),

    THE_LONG_SHOT (

        "Long Shot (High performance phones only)",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(false, new MapSpawn(new int[][] { new int[] {6, 4}, new int[] {7, 28} })),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(0, 0, 15, 0), // top
            new MapRange(0, 1, 0, 31), // left
            new MapRange(15, 0, 15, 31), // right
            new MapRange(0, 31, 15, 31) // bottom

        ),

        // creep block policy
        new MapBlockPolicy(),

        // map stitch policy
        new MapStitchPolicy(new MapPatch("maps/the_long_shot_p1.png", 0, 0, 1024, 1024), new MapPatch("maps/the_long_shot_p2.png", 0, 1024, 1024, 1024))

    ),

    THE_COLISEUM (

        "The Coliseum (High performance phones only)",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",

        // map spawn policy
        new MapSpawnPolicy(
            false,
            new MapSpawn(new int[][] { new int[] {5, 5}, new int[] {26, 5}, new int[] {26, 26} })
        ),

        // tower block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(3, 3, 3, 3), // top left corner
            new MapRange(28, 3, 28, 3), // top right corner
            new MapRange(3, 28, 3, 28), // bottom left corner
            new MapRange(28, 28, 28, 28) // bottom right corner

        ),

        // creep block policy
        new MapBlockPolicy(

            // startCol, startRow, endCol, endRow
            new MapRange(2, 2, 2, 29), // left
            new MapRange(2, 2, 29, 2), // top
            new MapRange(29, 2, 29, 29), // right
            new MapRange(2, 29, 29, 29) // bottom

        ),

        // map stitch policy
        new MapStitchPolicy(
            new MapPatch("maps/the_coliseum_p1.png", 0, 0, 1024, 1024),
            new MapPatch("maps/the_coliseum_p2.png", 0, 1024, 1024, 1024),
            new MapPatch("maps/the_coliseum_p3.png", 1024, 0, 1024, 1024),
            new MapPatch("maps/the_coliseum_p4.png", 1024, 1024, 1024, 1024)
        )

    ),

    ;

    private final String mTitle;
    private final String mDescription;

    private final MapSpawnPolicy mMapSpawnPolicy;
    private final MapBlockPolicy mTowerBlockPolicy;
    private final MapBlockPolicy mCreepBlockPolicy;
    private final MapStitchPolicy mMapStitchPolicy;

    static public MapTypes getMapTypeByIndex(final int pIndex) {
        for(final MapTypes mapType : MapTypes.values()) {
            if(pIndex == mapType.ordinal()) {
                return mapType;
            }
        }

        throw new IndexOutOfBoundsException("Unknown map index " + pIndex + ")");
    }

    static public MapTypes getMapTypeByTitle(final String pTitle) {
        for(final MapTypes mapType : MapTypes.values()) {
            if(pTitle.equals(mapType.getTitle())) {
                return mapType;
            }
        }

        throw new IndexOutOfBoundsException("Unknown map title " + pTitle + ")");
    }

    MapTypes(
            final String pTitle,
            final String pDescription,

            final MapSpawnPolicy pMapSpawnPolicy,

            final MapBlockPolicy pTowerBlockPolicy,
            final MapBlockPolicy pCreepBlockPolicy,

            final MapStitchPolicy pMapStitchPolicy

        )
    {
        mTitle = pTitle;
        mDescription = pDescription;

        mMapSpawnPolicy = pMapSpawnPolicy;

        mTowerBlockPolicy = pTowerBlockPolicy;
        mCreepBlockPolicy = pCreepBlockPolicy;

        mMapStitchPolicy = pMapStitchPolicy;
    }

    public MapStitchPolicy getMapStitchPolicy() {
        return mMapStitchPolicy;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public MapSpawnPolicy getMapSpawnPolicy() {
        return mMapSpawnPolicy;
    }

    public MapBlockPolicy getCreepBlockPolicy() {
        return mCreepBlockPolicy;
    }

    public MapBlockPolicy getTowerBlockPolicy() {
        return mTowerBlockPolicy;
    }
}