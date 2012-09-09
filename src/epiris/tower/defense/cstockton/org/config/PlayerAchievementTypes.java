package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.player.achievements.IPlayerAchievementPrerequisite;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievementStatisticPrerequisite;

public enum PlayerAchievementTypes {

    ABUSIVE_10("Abusive 10", "Do 10 damage of any type", 100000, new PlayerAchievementStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, 10)),

    ABUSIVE_1000("Abusive 1,000", "Do 1,000 damage of any type", 10, new PlayerAchievementStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, 1000)),
    ABUSIVE_10000("Abusive 10,000", "Do 10,000 damage of any type", 25, new PlayerAchievementStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, 10000)),
    ABUSIVE_100000("Abusive 100,000", "Do 100,000 damage of any type", 50, new PlayerAchievementStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, 100000)),
    ABUSIVE_1000000("Abusive 1,000,000", "Do 1,000,000 damage of any type", 100, new PlayerAchievementStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE.mName, 1000000)),

    PHYSICALLY_ABUSIVE_1000("Physically Abusive 1,000", "Do 1,000 physical damage", 10, new PlayerAchievementStatisticPrerequisite(DamageTypes.PHYSICAL.mName, 1000)),
    MAGICALLY_DELICIOUS_1000("Magically Delicious 1,000", "Do 1,000 magical damage", 25, new PlayerAchievementStatisticPrerequisite(DamageTypes.MAGICAL.mName, 1000)),
    PURE_OVERLOAD_1000("Pure Overload 1,000", "Do 1,000 elemental damage", 50, new PlayerAchievementStatisticPrerequisite(DamageTypes.PURE.mName, 1000)),

    PHYSICALLY_ABUSIVE_10000("Physically Abusive 10,000", "Do 10,000 physical damage", 10, new PlayerAchievementStatisticPrerequisite(DamageTypes.PHYSICAL.mName, 10000)),
    MAGICALLY_DELICIOUS_10000("Magically Delicious 10,000", "Do 10,000 magical damage", 25, new PlayerAchievementStatisticPrerequisite(DamageTypes.MAGICAL.mName, 10000)),
    PURE_OVERLOAD_10000("Pure Overload 10,000", "Do 10,000 elemental damage", 50, new PlayerAchievementStatisticPrerequisite(DamageTypes.PURE.mName, 10000)),

    PHYSICALLY_ABUSIVE_100000("Physically Abusive 100,000", "Do 100,000 physical damage", 10, new PlayerAchievementStatisticPrerequisite(DamageTypes.PHYSICAL.mName, 100000)),
    MAGICALLY_DELICIOUS_100000("Magically Delicious 100,000", "Do 100,000 magical damage", 25, new PlayerAchievementStatisticPrerequisite(DamageTypes.MAGICAL.mName, 100000)),
    PURE_OVERLOAD_100000("Pure Overload 100,000", "Do 100,000 elemental damage", 50, new PlayerAchievementStatisticPrerequisite(DamageTypes.PURE.mName, 100000)),

    ;

    final public String mTitle;
    final public String mDescription;
    final public int mGamePoints;
    final public IPlayerAchievementPrerequisite[] mPlayerAchievementPrerequisites;

    PlayerAchievementTypes(final String pTitle, final String pDescription, final int pGamePoints, final IPlayerAchievementPrerequisite ... pPlayerAchievementPrerequisites) {
        mTitle = pTitle;
        mDescription = pDescription;
        mGamePoints = pGamePoints;
        mPlayerAchievementPrerequisites = pPlayerAchievementPrerequisites;
    }

    static public int getAchievementGamePointsByTitle(final String pTitle) {
        for(PlayerAchievementTypes t : PlayerAchievementTypes.values()) {
            if(t.mTitle.equals(pTitle)) {
                return t.mGamePoints;
            }
        }

        return 0;
    }

    static public String getAchievementDescriptionByTitle(final String pTitle) {
        for(PlayerAchievementTypes t : PlayerAchievementTypes.values()) {
            if(t.mTitle.equals(pTitle)) {
                return t.mDescription;
            }
        }

        return null;
    }
}