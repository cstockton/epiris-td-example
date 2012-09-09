package epiris.tower.defense.cstockton.org.config;

public enum DamageTypes {

    PHYSICAL("Physical Damage", "Physical damage type."),
    MAGICAL("Magical Damage", "Magical damage type."),
    PURE("Pure Damage", "Pure damage type may not be resisted."),;

    public final String mName;
    public final String mDescription;

    private DamageTypes(final String pName, final String pDescription) {
        mName = pName;
        mDescription = pDescription;
    }
}