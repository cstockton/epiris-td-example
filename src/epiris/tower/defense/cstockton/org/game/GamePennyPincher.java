package epiris.tower.defense.cstockton.org.game;

import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.creep.Creep;
import epiris.tower.defense.cstockton.org.object.Registry;

public class GamePennyPincher extends Game {

    @Override
    public GamePennyPincher initialize() {
        return this;
    }

    @Override
    public void onCreepKill(final Creep pCreep) {

        // calc the worth
        final int scoreWorth = (int) (pCreep.getLifeBar().getMaxLife() * 10);
        final int gpWorth = (int) (pCreep.getLifeBar().getMaxLife() * 10);

        // award the player
        Registry.sLocalPlayer.addScore(scoreWorth);
        Registry.sLocalPlayer.addGamePoints(gpWorth);

        // floating text
        Registry.sCreepFloatingTextManager.scoreText(pCreep, scoreWorth);

        // handle our stats
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_CREEPS_KILLED, 1);
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_SCORE_EARNED, scoreWorth);
        Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_GAME_POINTS_EARNED, gpWorth);

    }

    @Override
    public void onManagedTick() {
        if (Registry.sLocalPlayer.getLives() < 0) {
            winGame();
        }
    }
}