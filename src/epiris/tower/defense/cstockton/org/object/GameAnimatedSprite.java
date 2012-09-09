package epiris.tower.defense.cstockton.org.object;

import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

abstract public class GameAnimatedSprite extends AnimatedSprite {
    public GameAnimatedSprite(float pX, float pY, float pTileWidth, float pTileHeight, TiledTextureRegion pTiledTextureRegion) {
        super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion);
    }

    public GameAnimatedSprite(int pX, int pY, TiledTextureRegion pTiledTextureRegion) {
        super(pX, pY, pTiledTextureRegion);
    }
}