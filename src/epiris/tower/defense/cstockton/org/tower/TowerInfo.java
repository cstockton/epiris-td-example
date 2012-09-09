package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.Debug;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;

import java.util.ArrayList;

public class TowerInfo extends HUD {

    // constants for tower info
    static public final int[] TOWERS_UI_CANCEL = new int[] { 3, 1 };

    // ui size specification
    static public final int TOWERS_UI_MIN_BORDER_WIDTH = 25;
    static public final int TOWERS_UI_SPACER = 25;
    static public final int TOWERS_UI_ITEM_WIDTH = 64;
    static public final int TOWERS_UI_ITEM_HEIGHT = 64;
    static public final int TOWERS_UI_CAMERA_WIDTH = Registry.sGameActivity.getCameraWidth();
    static public final int TOWERS_UI_CAMERA_HEIGHT = Registry.sGameActivity.getCameraHeight();

    private final TowerManager mTowerManager;

    final public Rectangle mPositionMarker;
    final public Sprite mRadiusMarker;

    private final ArrayList<TiledSprite> mUpgradeSprites = new ArrayList<TiledSprite>();
    final TiledTextureRegion mTowerMenuTiledTextureRegion;
    final AlignedText mTowerTitleText;
    final AlignedText mTowerDescText;
    final AlignedText mTowerStatsText;

    public Tower mCurrentTowerBase;

    boolean isValidClick = false;
    int mTotalColumns;
    int mTotalRows;

    int mBoxWidth;
    int mBoxHeight;

    int mBoxX;
    int mBoxY;

    public TowerInfo(final TowerManager pTowerManager, final TiledTextureRegion pTowerMenuTiledTextureRegion) {

        // ref our tower manager
        mTowerManager = pTowerManager;

        // create our position marker
        mPositionMarker = new Rectangle(0, 0, TowerManager.TOWER_WIDTH, TowerManager.TOWER_HEIGHT);
        mPositionMarker.setColor(1, 0, 0, 0.25f);
        mPositionMarker.setVisible(false);
        mPositionMarker.setIgnoreUpdate(true);

        // setup the radius marker
        mRadiusMarker = new Sprite(0, 0, mTowerManager.getTowerCircleTextureRegion().deepCopy());
        mRadiusMarker.setAlpha(0.25f);
        mRadiusMarker.setColor(.25f, .25f, .25f);
        mRadiusMarker.setVisible(false);
        mRadiusMarker.setIgnoreUpdate(true);

        // attach the radius marker to the position marker, then to the scene
        mPositionMarker.attachChild(mRadiusMarker);

        // assign our textures/font
        mTowerMenuTiledTextureRegion = pTowerMenuTiledTextureRegion;

        // calculate the total columns/rows
        mTotalColumns = (int) Math.floor((TOWERS_UI_CAMERA_WIDTH - (TOWERS_UI_MIN_BORDER_WIDTH * 2)) / (TOWERS_UI_ITEM_WIDTH + (TOWERS_UI_SPACER * 2)));
        mTotalRows = (int) Math.floor((TOWERS_UI_CAMERA_HEIGHT - (TOWERS_UI_MIN_BORDER_WIDTH * 2)) / (TOWERS_UI_ITEM_HEIGHT + (TOWERS_UI_SPACER * 2)));

        // decide the width of our box
        mBoxWidth = (TOWERS_UI_SPACER) + ((1 + mTotalColumns) * (TOWERS_UI_ITEM_WIDTH + TOWERS_UI_SPACER));
        mBoxHeight = (TOWERS_UI_SPACER) + ((1 + mTotalRows) * (TOWERS_UI_ITEM_HEIGHT + TOWERS_UI_SPACER));

        // decide our box x/y coords
        mBoxX = (TOWERS_UI_CAMERA_WIDTH / 2) - (mBoxWidth / 2);
        mBoxY = (TOWERS_UI_CAMERA_HEIGHT / 2) - (mBoxHeight / 2);

        // create our overlay "box", just a transparent rectangle
        final Rectangle rect = new Rectangle(mBoxX, mBoxY, mBoxWidth, mBoxHeight);
        rect.setColor(1, 1, 1, 0.50f);
        attachChild(rect);

        // our cancel button
        final TiledSprite cancelButton = new TiledSprite(mBoxX + TOWERS_UI_SPACER, mBoxY + TOWERS_UI_SPACER, mTowerManager.getTowerMenuTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerInfo.this.hide();
                            }
                        }
                    );
                }
                return true;
            }
        };
        cancelButton.setCurrentTileIndex(TOWERS_UI_CANCEL[0], TOWERS_UI_CANCEL[1]);
        attachChild(cancelButton);
        registerTouchArea(cancelButton);

        mTowerTitleText = new AlignedText(mBoxX + (mBoxWidth / 2), mBoxY + 50, Registry.sFontWhite24, "Tower Info", HorizontalAlign.CENTER, 64);
        mTowerTitleText.setColor(0f, 0f, 0f);
        attachChild(mTowerTitleText);

        mTowerDescText = new AlignedText(mBoxX + (mBoxWidth / 2), mBoxY + 50 + 25, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 255);
        mTowerDescText.setColor(0f, 0f, 0f);
        attachChild(mTowerDescText);

        mTowerStatsText = new AlignedText(mBoxX + (mBoxWidth / 2), mBoxY + mBoxHeight - 50, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 255);
        mTowerStatsText.setColor(0f, 0f, 0f);
        attachChild(mTowerStatsText);

    }

    public boolean sell() {

        // sell this tower
        mTowerManager.sellTower(TowerInfo.this.mCurrentTowerBase);
        hide();

        return true;
    }

    public TowerInfo show(final Tower pTowerBase) {
        //Debug.i("TowerUpgrader :: show :: type=" + pTowerBase.getTowerType());

        // disable tower interaction
        TowerManager.ENABLE_TOWER_INTERACTION = false;

        // set our current tower base
        mCurrentTowerBase = pTowerBase;

        // update our title/desc text
        mTowerTitleText.setText(AlignedText.wrapText(pTowerBase.getTowerType().mName, 35));
        mTowerDescText.setText(AlignedText.wrapText(pTowerBase.getTowerType().mDescription, 50));
        mTowerStatsText.setText("Cost: 25G -- Refund: 12G\nSpeed: 10 -- Damage: 250 -- Range: 4015");

        // find the coords
        final int[] coords = Registry.sMap.getCoordsFromTiles(pTowerBase.mTowerCol, pTowerBase.mTowerRow);

        // update the position
        mPositionMarker.setPosition(coords[0], coords[1]);
        mPositionMarker.setColor(1, 1, 1, 1.0f);

        // turn on updates and visibility
        mPositionMarker.setIgnoreUpdate(false);
        mPositionMarker.setVisible(true);

        // setup the radius marker
        mRadiusMarker.setIgnoreUpdate(false);
        mRadiusMarker.setVisible(true);
        mRadiusMarker.setWidth(pTowerBase.getTowerRange() * 2);
        mRadiusMarker.setHeight(pTowerBase.getTowerRange() * 2);

        // set the position of the marker
        mRadiusMarker.setPosition(
            (-pTowerBase.getTowerRange()) + (TowerManager.TOWER_WIDTH / 2),
            (-pTowerBase.getTowerRange()) + (TowerManager.TOWER_HEIGHT / 2)
        );

        // attach the radius marker to the position marker, then to the scene
        Registry.sSceneLayerTowers.attachChild(mPositionMarker);

        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_DISABLED);
        Registry.sGameActivity.getCamera().setHUD(TowerInfo.this);

        return this;
    }

    public TowerInfo hide() {

        for (final TiledSprite ts : mUpgradeSprites) {
            TowerInfo.this.unregisterTouchArea(ts);
            TowerInfo.this.detachChild(ts);
            ts.reset();
        }

        TowerInfo.this.mUpgradeSprites.clear();

        // turn off updates and visibility
        mPositionMarker.setIgnoreUpdate(true);
        mPositionMarker.setVisible(false);
        mRadiusMarker.setIgnoreUpdate(true);
        mRadiusMarker.setVisible(false);

        // detach the radius marker to the position marker, then to
        // the scene
        Registry.sSceneLayerTowers.detachChild(mPositionMarker);

        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_CAMERA);
        Registry.sGameActivity.getCamera().setHUD(Registry.sHud);

        // enable tower interaction
        TowerManager.ENABLE_TOWER_INTERACTION = true;

        return this;
    }

    public TowerInfo upgrade(final Tower pTowerSource, final TowerTypes pTowerType) {
        Debug.i("TowerUpgrader :: upgrade :: pTowerType=" + pTowerType);
        TowerInfo.this.mTowerManager.upgrade(pTowerSource, pTowerType);
        hide();

        return this;
    }
}