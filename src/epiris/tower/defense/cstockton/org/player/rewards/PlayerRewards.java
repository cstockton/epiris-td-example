package epiris.tower.defense.cstockton.org.player.rewards;

import java.util.ArrayList;
import java.util.HashSet;

import epiris.tower.defense.cstockton.org.config.PlayerRewardTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Debug;

public class PlayerRewards {
    final public HashSet<String> mRewardsEarned = new HashSet<String>();

    public void reset() {
        Registry.sPlayerStorageConnector.resetPlayerRewards();

        //synchronized(mRewardsEarned) {
            mRewardsEarned.clear();
        //}
    }

    public ArrayList<PlayerReward> getRewardsEarned() {
        return Registry.sPlayerStorageConnector.selectAllPlayerRewards();
    }


    public boolean isRewardEarned(final PlayerRewardTypes pPlayerRewardType) {
        for(final String reward : mRewardsEarned) {
            if(reward.equals(pPlayerRewardType.mName)) {
                return true;
            }
        }

        return false;
    }

    public void sync() {
        final ArrayList<PlayerReward> r = Registry.sPlayerStorageConnector.selectAllPlayerRewards();
        for(final PlayerReward s : r) {
            Debug.i("PlayerRewards :: sync :: add reward \"" + s.getName() + "\"");
            mRewardsEarned.add(s.getName());
        }
    }

    public void save() {
        for(PlayerRewardTypes t : PlayerRewardTypes.values()) {
            if(!mRewardsEarned.contains(t.mName)) {
                boolean satisfied = true;

                if(t.mPlayerRewardCost == null && t.mPlayerRewardPrerequisites != null) {
                    for(IPlayerRewardPrerequisite p : t.mPlayerRewardPrerequisites) {
                        if(!satisfied || !p.isSatisfied()) {
                            satisfied = false;
                            break;
                        }
                    }
                } else {
                    satisfied = false;
                }

                if(satisfied) {
                    earn(t.mName, 1);
                }
            }
        }
    }

    public void earn(final String pReward, final int pLevel) {
        earn(pReward, pLevel, true);
    }

    public void earn(final String pReward, final int pLevel, final boolean notify) {
        Registry.sPlayerStorageConnector.updatePlayerReward(pReward, pLevel);
        mRewardsEarned.add(pReward);

        if(notify) {
            Registry.sGameActivity.userNotifyLong("Reward unlocked \"" + pReward + "\" congratulations!!");
        }
    }
}