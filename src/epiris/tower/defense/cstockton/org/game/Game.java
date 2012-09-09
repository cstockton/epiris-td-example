package epiris.tower.defense.cstockton.org.game;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import epiris.tower.defense.cstockton.org.config.GameTypes;
import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.creep.Creep;
import epiris.tower.defense.cstockton.org.creep.CreepConfiguration;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.ui.GameOver;
import epiris.tower.defense.cstockton.org.util.Debug;
import epiris.tower.defense.cstockton.org.wave.Wave;
import epiris.tower.defense.cstockton.org.wave.WaveManager;

abstract public class Game implements IGame {
    protected float mTickAccumulator = 0f;
    protected float mTickInterval = 1f;
    protected boolean mRunning = true;

    static public GameTypes idToGameMode(final int pGameModeId) {
        for (final GameTypes pGameMode : GameTypes.values()) {
            if (pGameModeId == pGameMode.getId()) {
                return pGameMode;
            }
        }

        return GameTypes.SURVIVAL;
    }

    static public IGame gameModeToGame(final GameTypes pGameMode) {
        switch (pGameMode) {

        default:
            case SURVIVAL: {
                return new GameSurvival();
            }
        }
    }

    public Game() {
        super();
    }

    public Game setTickInterval(final float pTickInterval) {
        mTickInterval = pTickInterval;

        return this;
    }

    public float getTickInterval() {
        return mTickInterval;
    }

    public Game winGame() {
        endGame(true);

        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GAMES_WON, 1);

        return this;
    }

    public Game loseGame() {
        endGame(false);

        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GAMES_LOST, 1);

        return this;
    }

    public Game endGame(final boolean pWon) {
        mRunning = false;

        Registry.sGameActivity.getEngine().stop();

        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GAMES_PLAYED, 1);

        final GameOver gameOver = new GameOver();
        gameOver.showLose();

        return this;
    }

    @Override
    public int getCreepLife(final Wave pWave, final Creep pCreep, final CreepConfiguration pCreepConfiguration) {
        final int waveNum = pWave.getWaveNum();
        final int life = (int) ((((100 + Math.pow(waveNum, 1.20 + (waveNum / 90))) * waveNum * Registry.sGameOptions.getDifficulty()) / pWave.getTotalCreeps()) * (1f + pCreepConfiguration.mLifeBonus));

        return life;
    }

    @Override
    public void onCreepActivate(final Creep pCreep) {

    }

    @Override
    public void onCreepLeak(final Creep pCreep) {

        // add total creeps leaked, deduct a life
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_CREEPS_LEAKED, 1);

        // the creep was leaked! deduct a life
        Registry.sLocalPlayer.deductLives(1);

    }

    @Override
    public void onCreepKill(final Creep pCreep) {

        // fetch the creep type
        //final CreepTypes creepType = pCreep.getCreepType();

        // calc the worth
        final int goldWorth = (int) Math.ceil(1 + (pCreep.getLifeBar().getMaxLife() / 20));
        final int scoreWorth = (int) (pCreep.getLifeBar().getMaxLife() / 5);
        final int gpWorth = (int) (pCreep.getLifeBar().getMaxLife() / 10);

        // award the player
        Registry.sLocalPlayer.addGold(goldWorth);
        Registry.sLocalPlayer.addScore(scoreWorth);
        Registry.sLocalPlayer.addGamePoints(gpWorth);

        // floating text
        Registry.sCreepFloatingTextManager.goldText(pCreep, goldWorth);
        Registry.sCreepFloatingTextManager.scoreText(pCreep, scoreWorth);

        // handle our stats
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_CREEPS_KILLED, 1);
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GOLD_EARNED, goldWorth);
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_SCORE_EARNED, scoreWorth);
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GAME_POINTS_EARNED, gpWorth);

    }

    @Override
    public boolean isGameRunning() {
        return mRunning;
    }

    @Override
    public Game setGameRunning(final boolean pRunning) {
        mRunning = pRunning;
        return this;
    }

    @Override
    public Game initialize() {

        /*

        // load the level manager
        Registry.sLevelBuilder = new LevelBuilder();

        // load the level we are currently playing
        //@TODO
        Registry.sLevel = Registry.sLevelBuilder.getLevelByIndex(0);

        // load the wave manager
        Registry.sWaveManager = Registry.sLevel.getWaveManager();

        // check if we need to change the game mode in wave manager
        if (Registry.sGameOptions.getMode() == GameTypes.SURVIVAL) {
            Registry.sWaveManager.setSurvivalMode(true);
        }
        */

        return this;
    }

    @Override
    public void onLoadResources(final Engine pEngine) { }

    @Override
    public void onLoadScene(final Scene pScene) {
        //Registry.sLevel = new Level()


        Registry.sWaveManager = new WaveManager(Registry.sGameOptions.getWaveStrategyType());

    }

    @Override
    public void onLoadEngine(final Engine pEngine) {
        //Registry.sLevel = new Level()
        //Registry.sLevel = new Level();


    }

    @Override
    public void onUpdate(final float pSecondsElapsed) {
        mTickAccumulator += pSecondsElapsed;

        while(mTickAccumulator >= mTickInterval) {
            onManagedTick();

            mTickAccumulator -= mTickInterval;
        }
    }

    public abstract void onManagedTick();
}