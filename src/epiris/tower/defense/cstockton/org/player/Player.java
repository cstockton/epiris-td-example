package epiris.tower.defense.cstockton.org.player;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.data.PlayerDataTypes;

abstract public class Player implements IPlayer {

    public int mPlayerGold;
    public int mPlayerGamePoints;
    public int mPlayerLives;
    public int mPlayerScore;
    public int mPlayerDifficulty;

    public Player(final int pDifficultyLevel) {
        super();

        try {
            mPlayerGamePoints = Integer.parseInt(Registry.sPlayerData.getPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS));
        } catch(final NumberFormatException e) {
            mPlayerGamePoints = 0;
        }

        mPlayerGold = 9000000;
        mPlayerLives = 20;
        mPlayerScore = 0;
        mPlayerDifficulty = pDifficultyLevel;
    }

    @Override
    public Player addGamePoints(int pGamePoints) {
        mPlayerGamePoints += pGamePoints;

        Registry.sPlayerData.setPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS, Integer.toString(mPlayerGamePoints));
        return this;
    }

    @Override
    public Player deductGamePoints(int pGamePoints) {
        mPlayerGamePoints -= pGamePoints;

        Registry.sPlayerData.setPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS, Integer.toString(mPlayerGamePoints));
        return this;
    }

    @Override
    public Player setGamePoints(int pGamePoints) {
        mPlayerGamePoints = pGamePoints;

        Registry.sPlayerData.setPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS, Integer.toString(mPlayerGamePoints));
        return this;
    }

    @Override
    public int getGamePoints() {
        return mPlayerGamePoints;
    }

    @Override
    public Player addGold(int pGold) {
        mPlayerGold += pGold;
        return this;
    }

    @Override
    public Player deductGold(int pGold) {
        mPlayerGold -= pGold;
        return this;
    }

    @Override
    public Player setGold(int pGold) {
        mPlayerGold = pGold;
        return this;
    }

    @Override
    public int getGold() {
        return mPlayerGold;
    }

    @Override
    public Player addLives(int pLives) {
        mPlayerLives += pLives;
        return this;
    }

    @Override
    public Player deductLives(int pLives) {
        mPlayerLives -= pLives;
        return this;
    }

    @Override
    public Player setLives(int pLives) {
        mPlayerLives = pLives;
        return this;
    }

    @Override
    public int getLives() {
        return mPlayerLives;
    }

    @Override
    public Player addScore(int pScore) {
        mPlayerScore += pScore;
        return this;
    }

    @Override
    public Player deductScore(int pScore) {
        mPlayerScore -= pScore;
        return this;
    }

    @Override
    public Player setScore(int pScore) {
        mPlayerScore = pScore;
        return this;
    }

    @Override
    public int getScore() {
        return mPlayerScore;
    }

    @Override
    public Player addDifficulty(int pDifficulty) {
        mPlayerDifficulty += pDifficulty;
        return this;
    }

    @Override
    public Player deductDifficulty(int pDifficulty) {
        mPlayerDifficulty -= pDifficulty;
        return this;
    }

    @Override
    public Player setDifficulty(int pDifficulty) {
        mPlayerDifficulty = pDifficulty;
        return this;
    }

    @Override
    public int getDifficulty() {
        return mPlayerDifficulty;
    }
}