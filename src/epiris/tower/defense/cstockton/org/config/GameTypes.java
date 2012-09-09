package epiris.tower.defense.cstockton.org.config;

public enum GameTypes {

    CONQUER(1, "Conquer", "Conquer Description"),
    SURVIVAL(1, "Survival", "Survival Description"),

    ;

    private final int mId;
    private final String mTitle;
    private final String mDescription;

    GameTypes(final int pId, final String pTitle, final String pDescription) {
        this.mId = pId;
        this.mTitle = pTitle;
        this.mDescription = pDescription;
    }

    public int getId() {
        return this.mId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }
}