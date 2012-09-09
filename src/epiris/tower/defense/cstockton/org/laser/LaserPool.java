package epiris.tower.defense.cstockton.org.laser;

import org.anddev.andengine.util.pool.GenericPool;

public class LaserPool extends GenericPool<Laser>
{

    @Override
    protected Laser onAllocatePoolItem()
    {
        return new Laser(0, 0, 0, 0);
    }

    @Override
    protected void onHandleRecycleItem(final Laser pLaser)
    {
        //pLaser.deactivate();
    }

    @Override
    protected void onHandleObtainItem(final Laser pLaser)
    {
        pLaser.reset();
    }
}