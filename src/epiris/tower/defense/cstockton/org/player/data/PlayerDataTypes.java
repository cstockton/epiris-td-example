package epiris.tower.defense.cstockton.org.player.data;

public enum PlayerDataTypes {
    TOTAL_GAME_POINTS("Total Game Points"),
    CURRENT_CONQUER_LEVEL("Current Conquer Level"),
    UNLOCKED_DATA("Current Conquer Level"),

    ;

    final public String mName;

    PlayerDataTypes(final String pName) {
        mName = pName;
    }
}