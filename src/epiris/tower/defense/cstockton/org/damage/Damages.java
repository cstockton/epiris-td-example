package epiris.tower.defense.cstockton.org.damage;

public class Damages implements Cloneable {
    public Damage[] mDamages;
    public float mMultiplier;

    public Damages(final Damage... pDamages) {
        this(0f, pDamages);
    }

    public Damages(final float pMultiplier, final Damage... pDamages) {
        mMultiplier = pMultiplier;
        mDamages = pDamages;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch(final CloneNotSupportedException e) {
            return this;
        }
    }
}