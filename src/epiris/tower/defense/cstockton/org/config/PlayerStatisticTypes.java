package epiris.tower.defense.cstockton.org.config;

import java.text.MessageFormat;

public enum PlayerStatisticTypes {
    TOTAL_DAMAGE_DONE("Total Damage Done", "The total damage done of any type for all time."),
    TOTAL_CREEPS_KILLED("Total Creeps Killed", "The total number of creeps killed for all time."),
    TOTAL_CREEPS_LEAKED("Total Creeps Leaked", "The total number of creeps leaked for all time."),
    TOTAL_GAMES_PLAYED("Total Games Played", "The total number of games you have played for all time."),
    TOTAL_GAMES_WON("Total Games Won", "The total number of games you have won for all time."),
    TOTAL_GAMES_LOST("Total Games Lost", "The total number of games you have lost for all time."),
    TOTAL_SECONDS_PLAYED("Total Seconds Played", "The total number of seconds you have played the game for."),

    TOTAL_SCORE_EARNED("Total Points Scored", "The total number of points you have scored for all time."),
    TOTAL_GAME_POINTS_EARNED("Total Game Points Earned", "The total amount of of game points you have earned for all time."),
    TOTAL_GOLD_EARNED("Total Gold Earned", "The total amount of gold you have earned for all time."),

    // partial
    _DAMAGE_DONE("{0} damage done", "The total damage of type {0} done for all time."),

    _MAP_HIGHEST_SCORE_("Highest Score for {0}", "The highest score you have achieved for {0}."),
    _MAP_HIGHEST_DIFFICULTY_BEATEN_("Highest Difficulty Beaten for {0}", "The highest difficulty beaten for {0}."),
    _MAP_TOTAL_TIMES_PLAYED_("Total Times played for {0}", "The total times you have played on {0}."),
    _MAP_TOTAL_TIMES_BEATEN_("Total Times you won for {0}", "The total times you have won on {0}."),

    _TOWER_DAMAGE_DEALT("{0} Damage Dealt", "How much damage you have done with the {0} tower."),
    _TOWER_TIMES_FIRED("{0} Times Fired", "How many times the {0} has fired upon its enemies."),
    _TOWER_TIMES_CREATED("{0} Times Built", "How many times you have built the {0}."),

    ;

    final public String mName;
    final public String mDescription;

    static final public String getStatisticName(final PlayerStatisticTypes pPlayerStatisticTypes, final TowerTypes pTowerType) {
        return MessageFormat.format(pPlayerStatisticTypes.mName, pTowerType.mName);
    }

    static final public String getStatisticDescription(final PlayerStatisticTypes pPlayerStatisticTypes, final TowerTypes pTowerType) {
        return MessageFormat.format(pPlayerStatisticTypes.mDescription, pTowerType.mDescription);
    }

    static final public String getStatisticName(final PlayerStatisticTypes pPlayerStatisticTypes, final DamageTypes pDamageType) {
        return MessageFormat.format(pPlayerStatisticTypes.mName, pDamageType.mName);
    }

    static final public String getStatisticDescription(final PlayerStatisticTypes pPlayerStatisticTypes, final DamageTypes pDamageType) {
        return MessageFormat.format(pPlayerStatisticTypes.mDescription, pDamageType.mDescription);
    }

    static final public String getStatisticName(final PlayerStatisticTypes pPlayerStatisticTypes, final MapTypes pMapType) {
        return MessageFormat.format(pPlayerStatisticTypes.mName, pMapType.getTitle());
    }

    static final public String getStatisticDescription(final PlayerStatisticTypes pPlayerStatisticTypes, final MapTypes pMapType) {
        return MessageFormat.format(pPlayerStatisticTypes.mDescription, pMapType.getTitle());
    }

    PlayerStatisticTypes(final String pName, final String pDescription) {
        mName = pName;
        mDescription = pDescription;
    }

    PlayerStatisticTypes(TowerTypes pTowertype, final String pName, final String pDescription) {
        mName = pName;
        mDescription = pDescription;
    }
}