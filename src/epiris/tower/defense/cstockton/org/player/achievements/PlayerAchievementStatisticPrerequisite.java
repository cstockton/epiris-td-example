package epiris.tower.defense.cstockton.org.player.achievements;

import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerAchievementStatisticPrerequisite implements IPlayerAchievementPrerequisite {
    final String mStatisticName;
    final int mRequiredValue;

    public PlayerAchievementStatisticPrerequisite(final String pStatisticName, final int pRequiredValue) {
        mStatisticName = pStatisticName;
        mRequiredValue = pRequiredValue;
    }

    @Override
    public boolean isSatisfied() {
        //Debug.i("PlayerAchievementStatisticPrerequisite :: isSatisfied :: " + Registry.sPlayerStatistics.getStatistic(mStatisticName) + " > " + mRequiredValue);
        return Registry.sPlayerStatistics.getStatistic(mStatisticName) > mRequiredValue;
    }
}