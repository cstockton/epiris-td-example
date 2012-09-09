package epiris.tower.defense.cstockton.org.player.rewards;

import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerRewardStatisticPrerequisite implements IPlayerRewardPrerequisite {
    final String mStatisticName;
    final int mRequiredValue;

    public PlayerRewardStatisticPrerequisite(final String pStatisticName, final int pRequiredValue) {
        mStatisticName = pStatisticName;
        mRequiredValue = pRequiredValue;
    }


    public PlayerRewardStatisticPrerequisite(final PlayerStatisticTypes pStatisticType, final int pRequiredValue) {
        this(pStatisticType.mName, pRequiredValue);
    }

    @Override
    public String getName() {
        return mStatisticName + " > " + mRequiredValue;
    }

    @Override
    public boolean isSatisfied() {
        return Registry.sPlayerStatistics.getStatistic(mStatisticName) > mRequiredValue;
    }
}