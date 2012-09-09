package epiris.tower.defense.cstockton.org.config;

import epiris.tower.defense.cstockton.org.player.rewards.*;

/*

JUST MAKE A BUTTON ON PREQ

PLAYER REWARD TYPES HAVE A PLAYERREWARDEFFECTS ? SCRAP THAT VALUE CRAP
        TowerUnlockEffect ( 1 level, TowerType )
        DamageBonusEffect ( max level, damage type, bonus %, bonus const, bonust multiplier )
        LifeBonusEffect ( max level, bonus, bonus const, bonus multiplier)
*/
public enum PlayerRewardTypes {
    TOWER_UNLOCK_HIGH_MARKSMAN(
        "Unlock High Marksman Tower",
        "This reward grants access to build the medium archer tower.",
        "You unlocked this reward, which granted you access to build the medium archer tower.",
        new PlayerRewardLevelCost(1, 100, 0, 1f),
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.ARCHER4)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.PHYSICALLY_ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.MAGICALLY_DELICIOUS_1000),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_CREEPS_KILLED, 10),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE, 1000)
        }
    ),

    TOWER_UNLOCK_MASTER_MARKSMAN(
        "Unlock Master Marksman Tower",
        "This reward grants access to build the heavy archer tower.",
        "You unlocked this reward, which granted you access to build the heavy archer tower.",
        null,
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.ARCHER5)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.PHYSICALLY_ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.MAGICALLY_DELICIOUS_1000),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_CREEPS_KILLED, 10),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE, 5000)
        }
    ),

    PLAYER_INCREASE_STARTING_LIFES(
        "Increase your total lifes",
        "Increase your total lifes",
        new PlayerRewardLevelCost(20, 100, 0, 1f),
        null,
        null
    ),

    PLAYER_INCREASE_STARTING_GOLD(
        "Increase your total gold",
        "Increase your total gold",
        new PlayerRewardLevelCost(20, 50, 0, 1f),
        null,
        null
    ),

    /*
    TOWER_UNLOCK_PHYSICAL_ARCHER_MEDIUM(
        "Unlock PHYSICAL_ARCHER_MEDIUM",
        "INITIAL desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM",
        "desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM desc PHYSICAL_ARCHER_MEDIUM",
        null,
        null,
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.PHYSICAL_ARCHER_MEDIUM)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.PHYSICALLY_ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.ABUSIVE_1000),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_DAMAGE_DONE, 1500),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_CREEPS_KILLED, 52),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_CREEPS_LEAKED, 251)
        }
    ),

    TOWER_UNLOCK_PHYSICAL_ARCHER_HEAVY(
        "Unlock PHYSICAL_ARCHER_HEAVY",
        "desc PHYSICAL_ARCHER_HEAVY",
        null,
        null,
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.PHYSICAL_ARCHER_MEDIUM)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.PHYSICALLY_ABUSIVE_10000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.ABUSIVE_1000),
            new PlayerRewardStatisticPrerequisite(PlayerStatisticTypes.TOTAL_CREEPS_LEAKED, 251)
        }
    ),
    TOWER_SPEED_BONUS_PHYSICAL(
         "Bonus to all speed etc",
         "bonus to all speeds",
         new IPlayerRewardEffect[] {
             new PlayerRewardTowerUnlockEffect(TowerTypes.PHYSICAL_ARCHER_MEDIUM)
         },
         new PlayerRewardLevelCost(10, 25, 0, 1f),
         new PlayerRewardLevelValue(10, 0, 1f),
         null
     ),

    TOWER_DAMAGE_BONUS_PHYSICAL(
        "Bonus to all physical damage",
        "INITIAL Level {0} Cost {1} Cost Next {2} Value {3} Value Next {4}.",
        "Level {0} Cost {1} Cost Next {2} Value {3} Value Next {4}.",
        new PlayerRewardLevelCost(10, 25, 0, 1f),
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.PHYSICAL_ARCHER_MEDIUM)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.PHYSICALLY_ABUSIVE_1000),
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.ABUSIVE_1000)
        }
    ),

    TOWER_DAMAGE_BONUS_MAGICAL(
        "Bonus to all magical damage",
        "bonus to all magical damage",
        new PlayerRewardLevelCost(10, 25, 0, 1f),
        new PlayerRewardLevelValue(10, 0, 1f),
        new IPlayerRewardEffect[] {
            new PlayerRewardTowerUnlockEffect(TowerTypes.PHYSICAL_ARCHER_MEDIUM)
        },
        new IPlayerRewardPrerequisite[] {
            new PlayerRewardAchievementPrerequisite(PlayerAchievementTypes.MAGICALLY_DELICIOUS_1000)
        }
    ),

    TOWER_UNLOCK_ULTI(
        "unlock ulti tower",
        "gives u the ulti tower",
        "You have unlocked the ulti tower.",
        new PlayerRewardLevelCost(1, 100, 0, 1f),
        null,
        null
    ),

    PLAYER_INCREASE_STARTING_LIFES(
        "Increase your total lifes",
        "Increase your total lifes",
        new PlayerRewardLevelCost(20, 100, 0, 1f),
        null,
        null
    ),

    PLAYER_INCREASE_STARTING_GOLD(
        "Increase your total gold",
        "Increase your total gold",
        new PlayerRewardLevelCost(20, 50, 0, 1f),
        null,
        null
    ),
    */

    ;

    final public String mName;
    final public String mDescription;
    final public String mDescriptionUpgrade;

    final public IPlayerRewardCost mPlayerRewardCost;
    final public IPlayerRewardEffect[] mPlayerRewardEffects;
    final public IPlayerRewardPrerequisite[] mPlayerRewardPrerequisites;

    public static String getTowerTypeAsUnlockReward(TowerTypes pTowerType) {
        return pTowerType.mName + "_unlock";
    }

    public static String getTowerTypeAsBonusDamageReward(TowerTypes pTowerType) {
        return pTowerType.mName + "_damage";
    }

    public static String getTowerTypeAsBonusSpeedReward(TowerTypes pTowerType) {
        return pTowerType.mName + "_speed";
    }

    public static String getTowerTypeAsBonusRangeReward(TowerTypes pTowerType) {
        return pTowerType.mName + "_range";
    }

    static public String getDescriptionByName(final String pName) {
        for(PlayerRewardTypes t : PlayerRewardTypes.values()) {
            if(t.mName.equals(pName)) {
                return t.mDescription;
            }
        }

        return null;
    }

    static public PlayerRewardTypes getByName(final String pName) {
        for(PlayerRewardTypes t : PlayerRewardTypes.values()) {
            if(t.mName.equals(pName)) {
                return t;
            }
        }

        return null;
    }

    PlayerRewardTypes(final String pName, final String pDescription, final IPlayerRewardCost pPlayerRewardCost, final IPlayerRewardEffect[] pPlayerRewardEffects, final IPlayerRewardPrerequisite[] pPlayerRewardPrerequisites) {
        this(pName, pDescription, pDescription, pPlayerRewardCost, pPlayerRewardEffects, pPlayerRewardPrerequisites);
    }

    PlayerRewardTypes(final String pName, final String pDescription, final String pDescriptionUpgrade, final IPlayerRewardCost pPlayerRewardCost, final IPlayerRewardEffect[] pPlayerRewardEffects, final IPlayerRewardPrerequisite[] pPlayerRewardPrerequisites) {
        mName = pName;
        mDescription = pDescription;
        mDescriptionUpgrade = pDescriptionUpgrade;

        mPlayerRewardCost = pPlayerRewardCost;
        mPlayerRewardEffects = pPlayerRewardEffects;
        mPlayerRewardPrerequisites = pPlayerRewardPrerequisites;
    }

    public boolean hasCost() {
        return null != mPlayerRewardCost;
    }

    public boolean hasEffects() {
        return null != mPlayerRewardEffects;
    }

    public boolean hasPrerequisites() {
        return null != mPlayerRewardPrerequisites;
    }


}