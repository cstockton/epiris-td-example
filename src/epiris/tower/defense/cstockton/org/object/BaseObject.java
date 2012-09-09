package epiris.tower.defense.cstockton.org.object;


public abstract class BaseObject extends AllocationGuard {
    static ObjectRegistry sSystemRegistry = new ObjectRegistry();

    public BaseObject() {
        super();
    }

    /**
     * Update this object.
     * @param timeDelta  The duration since the last update (in seconds).
     * @param parent  The parent of this object (may be NULL).
     */
    public void update(float timeDelta, BaseObject parent) {
        // Base class does nothing.
    }


    public abstract void reset();

}