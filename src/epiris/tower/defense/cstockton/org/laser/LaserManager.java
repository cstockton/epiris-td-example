package epiris.tower.defense.cstockton.org.laser;

import java.util.ArrayList;
import java.util.Iterator;

public class LaserManager
{

    private final LaserPool mLaserPool = new LaserPool();
    private final ArrayList<Laser> mActiveLasers = new ArrayList<Laser>();

    public LaserManager()
    {
        super();
    }

    public void onUpdate(final float pSecondsElapsed)
    {
        //final for (Iterator<TowerProjectileBase> iterator = this.mLaunchQueue.iterator(); iterator.hasNext();) {
        //final Iterator<Laser> laserIterator = this.mCleanupQueue.iterator();
        //for(final Laser laser : laserIterator) {

        for (final Iterator<Laser> iterator = this.mActiveLasers.iterator(); iterator.hasNext();) {
            final Laser laser = iterator.next();

            // call the lasers updater
            laser.onLaserUpdate(pSecondsElapsed);

            if(!laser.isActive()) {

                // deactivate
                laser.deactivate();

                // remove the laser
                iterator.remove();

                this.recycleLaser(laser);
            }
        }
    }

    public Laser getLaser()
    {
        final Laser laser = this.mLaserPool.obtainPoolItem();
        mActiveLasers.add(laser);

        return laser;
    }

    public LaserManager recycleLaser(final Laser pLaser)
    {
        this.mLaserPool.recyclePoolItem(pLaser);
        return this;
    }
}