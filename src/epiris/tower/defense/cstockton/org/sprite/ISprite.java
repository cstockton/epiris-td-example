package epiris.tower.defense.cstockton.org.sprite;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

public interface ISprite {
    public int getTileIndex();
    public ISpriteSheet getSpriteSheet();
    public TiledTextureRegion getSpriteSheetTiledTextureRegionClone();
}
