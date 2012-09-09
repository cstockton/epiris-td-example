package epiris.tower.defense.cstockton.org.game;

import java.io.Serializable;

import epiris.tower.defense.cstockton.org.config.GameTypes;
import epiris.tower.defense.cstockton.org.config.MapTypes;
import epiris.tower.defense.cstockton.org.config.WaveStrategyTypes;
import android.content.Intent;

public class GameOptions implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String sItentKey = "GameOptions";

    public GameTypes mMode;
    public MapTypes mMapType;
    public WaveStrategyTypes mWaveStrategyTypes;
    public int mDifficulty;

    public GameOptions() {
        super();
    }

    public void parseintent(final Intent intent) {
        // this.mMode = Game.idToGameMode(intent.getIntExtra("gameMode", 0));
        // this.mDifficulty = intent.getIntExtra("difficultyLevel", 1);
        // this.mMapType =
        // MapTypes.getMapTypeByIndex(intent.getIntExtra("levelIndex", 0));
    }

    public GameOptions initialize() {

        return this;

    }

    public GameTypes getMode() {
        return mMode;
    }

    public MapTypes getMapType() {
        return mMapType;
    }

    public WaveStrategyTypes getWaveStrategyType() {
        return mWaveStrategyTypes;
    }

    public int getDifficulty() {
        return mDifficulty;
    }

    public GameOptions setMode(final GameTypes pMode) {
        mMode = pMode;
        return this;
    }

    public GameOptions setMapType(final MapTypes pMapType) {
        mMapType = pMapType;
        return this;
    }

    public GameOptions setWaveStrategyType(final WaveStrategyTypes pWaveStrategyType) {
        mWaveStrategyTypes = pWaveStrategyType;
        return this;
    }

    public GameOptions setDifficulty(final int pDifficulty) {
        mDifficulty = pDifficulty;
        return this;
    }

}