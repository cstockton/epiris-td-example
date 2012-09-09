package epiris.tower.defense.cstockton.org.effect;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.pool.GenericPool;

public class EffectPool extends GenericPool<Effect>
{

    private final TiledTextureRegion mTiledTextureRegion;

    public EffectPool(final TiledTextureRegion pTiledTextureRegion) {
        super(0, 30);

        mTiledTextureRegion = pTiledTextureRegion;

        batchAllocatePoolItems(30);
    }

    @Override
    protected Effect onAllocatePoolItem() {
        return new Effect(mTiledTextureRegion.deepCopy());
    }

    @Override
    protected void onHandleRecycleItem(final Effect pEffect) {
        pEffect.reset();
    }
}