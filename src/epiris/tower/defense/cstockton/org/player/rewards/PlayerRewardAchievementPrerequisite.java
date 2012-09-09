package epiris.tower.defense.cstockton.org.player.rewards;

import epiris.tower.defense.cstockton.org.config.PlayerAchievementTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerRewardAchievementPrerequisite implements IPlayerRewardPrerequisite {
    final PlayerAchievementTypes mPlayerAchievementType;

    public PlayerRewardAchievementPrerequisite(final PlayerAchievementTypes pPlayerAchievementType) {
        mPlayerAchievementType = pPlayerAchievementType;
    }

    @Override
    public String getName() {
        return "Achieve > " + mPlayerAchievementType.mTitle;
    }

    @Override
    public boolean isSatisfied() {
        //Debug.i("PlayerAchievementStatisticPrerequisite :: isSatisfied :: " + Registry.sPlayerStatistics.getStatistic(mStatisticName) + " > " + mRequiredValue);
        return Registry.sPlayerAchievements.isAchievementEarned(mPlayerAchievementType);
    }
}