package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.R;

public enum WaveStrategyTypes {

    SURVIVAL(
        "Survival",
        "There is no limit here, enemies will come forever.",
        R.xml.wave_strategy_survival
    ),

    BASIC_WAVE_STRATEGY(
        "Basic Wave Strategy",
        "This is a basic wave strategy",
        R.xml.wave_strategy_01
    ),

    ;

    private final String mTitle;
    private final String mDescription;
    private final int mXml;

    WaveStrategyTypes(
        final String pTitle,
        final String pDescription,
        final int pXml
    ) {

        mTitle = pTitle;
        mDescription = pDescription;
        mXml = pXml;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getXml() {
        return mXml;
    }
}