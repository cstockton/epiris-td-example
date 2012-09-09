package epiris.tower.defense.cstockton.org.player.achievements;

public class PlayerAchievement {
    final private String mAchievementTitle;
    final private String mAchievementDescription;
    final private String mAchievementDate;

    public PlayerAchievement(final String pAchievementTitle, final String pAchievementDescription, final String pAchievementDate) {
        mAchievementTitle = pAchievementTitle;
        mAchievementDescription = pAchievementDescription;
        mAchievementDate = pAchievementDate;
    }

    public String getAchievementTitle() {
        return mAchievementTitle;
    }

    public String getAchievementDescription() {
        return mAchievementDescription;
    }

    public String getAchievementDate() {
        return mAchievementDate;
    }
}