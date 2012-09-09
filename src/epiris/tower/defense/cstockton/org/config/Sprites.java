package epiris.tower.defense.cstockton.org.config;

import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.sprite.ISprite;
import epiris.tower.defense.cstockton.org.sprite.ISpriteSheet;

public enum Sprites implements ISprite {

    /** UI_64x64_01 */
    UI_ICON_PLAY(0, SpriteSheets.UI_64x64_01),
    UI_ICON_FAST_FORWARD(1, SpriteSheets.UI_64x64_01),
    UI_ICON_PAUSE(2, SpriteSheets.UI_64x64_01),
    UI_ICON_SKIP(3, SpriteSheets.UI_64x64_01),
    UI_ICON_CLOSE(4, SpriteSheets.UI_64x64_01),
    UI_ICON_INFO(5, SpriteSheets.UI_64x64_01),
    UI_ICON_SELL(6, SpriteSheets.UI_64x64_01),

    UI_ICON_TOWER_1(16, SpriteSheets.UI_64x64_01),
    UI_ICON_TOWER_2(17, SpriteSheets.UI_64x64_01),
    UI_ICON_TOWER_3(18, SpriteSheets.UI_64x64_01),

    TOWER_UPGRADER_HANGING_ICON_SPEED(4, SpriteSheets.UI_64x64_01),
    TOWER_UPGRADER_HANGING_ICON_DAMAGE(5, SpriteSheets.UI_64x64_01),
    TOWER_UPGRADER_HANGING_ICON_RANGE(6, SpriteSheets.UI_64x64_01),
    TOWER_UPGRADER_HANGING_ICON_SELL(7, SpriteSheets.UI_64x64_01),

    /** UI_96x96_01 */
    TOWER_UPGRADER_UI_BG (4, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UI_BORDER (0, SpriteSheets.UI_96x96_01),

    TOWER_UPGRADER_UPGRADE_SPEED_ICON (7, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_DAMAGE_ICON (8, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_RANGE_ICON (9, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_AFFECT_ICON (12, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_PURITY_ICON (13, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_ULTIMATE_ICON (14, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_BORDER (18, SpriteSheets.UI_96x96_01),
    TOWER_UPGRADER_UPGRADE_BG (3, SpriteSheets.UI_96x96_01),

    UI_HUD_TOP_BG (21, SpriteSheets.UI_96x96_01),
    UI_HUD_TOP_LEFT (22, SpriteSheets.UI_96x96_01),
    UI_HUD_TOP_RIGHT (23, SpriteSheets.UI_96x96_01),

    ;

    private final int mTileIndex;
    private final ISpriteSheet mSpriteSheet;

    Sprites(final int pTileIndex, final ISpriteSheet pSpriteSheet) {
        mTileIndex = pTileIndex;
        mSpriteSheet = pSpriteSheet;
    }

    @Override
    public TiledTextureRegion getSpriteSheetTiledTextureRegionClone() {
        return Registry.sSpriteTextureRegions[getSpriteSheet().ordinal()].deepCopy();
    }

    @Override
    public int getTileIndex() {
        return mTileIndex;
    }

    @Override
    public ISpriteSheet getSpriteSheet() {
        return mSpriteSheet;
    }
}