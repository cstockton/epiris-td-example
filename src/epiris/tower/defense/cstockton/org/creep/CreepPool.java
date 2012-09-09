package epiris.tower.defense.cstockton.org.creep;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.util.GenericPool;

public class CreepPool extends GenericPool<Creep>
{

    private final TiledTextureRegion mTiledTextureRegion;

    public CreepPool(final TiledTextureRegion pTiledTextureRegion) {
        super(0, 10);

        mTiledTextureRegion = pTiledTextureRegion;

        batchAllocatePoolItems(30);
    }

    @Override
    protected Creep onAllocatePoolItem() {
        return new Creep(mTiledTextureRegion.deepCopy());
    }

    @Override
    protected void onHandleRecycleItem(final Creep pCreep) {
        pCreep.reset();
    }
}