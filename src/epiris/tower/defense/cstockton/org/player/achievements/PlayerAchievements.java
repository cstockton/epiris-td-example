package epiris.tower.defense.cstockton.org.player.achievements;

import java.util.ArrayList;
import java.util.HashSet;

import epiris.tower.defense.cstockton.org.config.PlayerAchievementTypes;
import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerAchievements {
    final public HashSet<String> mAchievementsEarned = new HashSet<String>();

    public void reset() {
        Registry.sPlayerStorageConnector.resetAchievements();

        //synchronized(mAchievementsEarned) {
            mAchievementsEarned.clear();
        //}
    }

    public ArrayList<PlayerAchievement> getAchievementsEarned() {
        return Registry.sPlayerStorageConnector.selectAllAchievements();
    }


    public boolean isAchievementEarned(final PlayerAchievementTypes pPlayerAchievementType) {
        for(final String achievement : mAchievementsEarned) {
            if(achievement.equals(pPlayerAchievementType.mTitle)) {
                return true;
            }
        }

        return false;
    }

    public void sync() {
        final ArrayList<PlayerAchievement> r = Registry.sPlayerStorageConnector.selectAllAchievements();
        for(final PlayerAchievement s : r) {
            mAchievementsEarned.add(s.getAchievementTitle());
        }
    }

    public void save() {
        for(PlayerAchievementTypes t : PlayerAchievementTypes.values()) {
            if(!mAchievementsEarned.contains(t.mTitle)) {
                boolean satisfied = true;

                for(IPlayerAchievementPrerequisite p : t.mPlayerAchievementPrerequisites) {
                    if(!satisfied || !p.isSatisfied()) {
                        satisfied = false;
                        break;
                    }
                }

                if(satisfied) {
                    earn(t.mTitle);
                }
            }
        }
    }

    private void earn(final String pAchievement) {
        Registry.sPlayerStorageConnector.insertAchievement(pAchievement);
        Registry.sLocalPlayer.addGamePoints(PlayerAchievementTypes.getAchievementGamePointsByTitle(pAchievement));
        Registry.sGameActivity.userNotifyLong("Achievement unlocked \"" + pAchievement + "\" congratulations!! Additional " + PlayerAchievementTypes.getAchievementGamePointsByTitle(pAchievement) + " GamePoints earned!!");

        mAchievementsEarned.add(pAchievement);
    }
}