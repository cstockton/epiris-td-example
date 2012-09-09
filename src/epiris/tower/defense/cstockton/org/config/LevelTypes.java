package epiris.tower.defense.cstockton.org.config;

public enum LevelTypes {

    BATTLE_AT_TEST (
        "Battle at The TEST",
        "TEST The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",
        MapTypes.THE_VALLEY,
        WaveStrategyTypes.BASIC_WAVE_STRATEGY
    ),

    BATTLE_AT_THE_VALLEY (
        "Battle at The Valley",
        "The enemies have only one way in and one way out, this valley offers you a strategic position to slaughter the enemy, will you capitalize on it, or will you fail...",
        MapTypes.THE_VALLEY,
        WaveStrategyTypes.BASIC_WAVE_STRATEGY
    ),

    ;

    private final String mTitle;
    private final String mDescription;
    private final MapTypes mMapType;
    private final WaveStrategyTypes mWaveStrategyType;

    LevelTypes(
        final String pTitle,
        final String pDescription,
        final MapTypes pMapType,
        final WaveStrategyTypes pWaveStrategyType
    ) {
        mTitle = pTitle;
        mDescription = pDescription;
        mMapType = pMapType;
        mWaveStrategyType = pWaveStrategyType;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public MapTypes getMapType() {
        return mMapType;
    }

    public WaveStrategyTypes getWaveStrategyType() {
        return mWaveStrategyType;
    }
}