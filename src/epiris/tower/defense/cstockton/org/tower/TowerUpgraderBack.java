package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.config.SpriteSheets;
import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Debug;

import java.util.ArrayList;

public class TowerUpgraderBack extends HUD {

    // constants for tower upgrader
    static public final int[] TOWERS_UI_CANCEL = new int[] { 3, 1 };
    static public final int[] TOWERS_UI_SELL = new int[] { 4, 0 };
    static public final int[] TOWERS_UI_RADIUS = new int[] { 3, 0 };

    // ui size specification
    static public final int TOWERS_UI_SPACER = 25;
    static public final int TOWERS_UI_ITEM_WIDTH = 128;
    static public final int TOWERS_UI_ITEM_HEIGHT = 128;
    static public final int TOWERS_UI_CAMERA_WIDTH = Registry.sGameActivity.getCameraWidth();
    static public final int TOWERS_UI_CAMERA_HEIGHT = Registry.sGameActivity.getCameraHeight();

    private final TowerManager mTowerManager;

    final public Rectangle mPositionMarker;
    final public Sprite mRadiusMarker;

    private final ArrayList<TiledSprite> mUpgradeSprites = new ArrayList<TiledSprite>();
    final TiledTextureRegion mTowerMenuTiledTextureRegion;
    final ChangeableText mTowerSellText;
    Tower mCurrentTowerBase;

    boolean isValidClick = false;
    int mTotalColumns;
    int mTotalRows;

    int mBoxWidth;
    int mBoxHeight;

    int mBoxX;
    int mBoxY;

    public TowerUpgraderBack(final TowerManager pTowerManager, final TiledTextureRegion pTowerMenuTiledTextureRegion) {

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
        mTotalColumns = 3; //(int) Math.floor((TOWERS_UI_CAMERA_WIDTH - (TOWERS_UI_MIN_BORDER_WIDTH * 2)) / (TOWERS_UI_ITEM_WIDTH + (TOWERS_UI_SPACER * 2)));
        mTotalRows = 2; //(int) Math.floor((TOWERS_UI_CAMERA_HEIGHT - (TOWERS_UI_MIN_BORDER_WIDTH * 2)) / (TOWERS_UI_ITEM_HEIGHT + (TOWERS_UI_SPACER * 2)));

        // decide the width of our box
        mBoxWidth = TOWERS_UI_SPACER + (mTotalColumns * (TOWERS_UI_ITEM_WIDTH + TOWERS_UI_SPACER));
        mBoxHeight = TOWERS_UI_SPACER + (mTotalRows * (TOWERS_UI_ITEM_HEIGHT + TOWERS_UI_SPACER));

        // decide our box x/y coords
        mBoxX = (TOWERS_UI_CAMERA_WIDTH / 2) - (mBoxWidth / 2);
        mBoxY = (TOWERS_UI_CAMERA_HEIGHT / 2) - (mBoxHeight / 2);

        // create our overlay "box", just a transparent rectangle
        final Rectangle rect = new Rectangle(mBoxX, mBoxY, mBoxWidth, mBoxHeight) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                Debug.i("WTF");
                return true;
            }
        };
        rect.setColor(1, 1, 1, 0.50f);
        rect.setZIndex(0);
        attachChild(rect);
        //registerTouchArea(rect);

        /*
        // our sell button
        final TiledSprite sellButton = new TiledSprite(mBoxX + TOWERS_UI_ITEM_WIDTH + (TOWERS_UI_SPACER * 2), mBoxY + TOWERS_UI_SPACER, mTowerManager.getTowerMenuTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerUpgrader.this.sell();
                            }
                        }
                    );
                }
                return true;
            }
        };
        sellButton.setCurrentTileIndex(TOWERS_UI_SELL[0], TOWERS_UI_SELL[1]);
        sellButton.setZIndex(1);
        attachChild(sellButton);
        registerTouchArea(sellButton);

        mTowerSellText = new ChangeableText(TOWERS_UI_ITEM_WIDTH / 2, TOWERS_UI_ITEM_HEIGHT - (TOWERS_UI_ITEM_HEIGHT / 4), Registry.sFontWhite12, "", HorizontalAlign.CENTER, "XXXXX".length());
        mTowerSellText.setColor(1f, 85f, 0f);
        sellButton.attachChild(this.mTowerSellText);
*/
        mTowerSellText = new ChangeableText(TOWERS_UI_ITEM_WIDTH / 2, TOWERS_UI_ITEM_HEIGHT - (TOWERS_UI_ITEM_HEIGHT / 4), Registry.sFontWhite12, "", HorizontalAlign.CENTER, "XXXXX".length());

        sortChildren();

    }

    public boolean sell() {

        // sell this tower
        mTowerManager.sellTower(TowerUpgraderBack.this.mCurrentTowerBase);
        hide();

        return true;
    }

    public TowerUpgraderBack show(final Tower pTowerBase) {
        //Debug.i("TowerUpgrader :: show :: type=" + pTowerBase.getTowerType());

        // disable tower interaction
        TowerManager.ENABLE_TOWER_INTERACTION = false;

        // set our current tower base
        mCurrentTowerBase = pTowerBase;

        // update our sell text
        mTowerSellText.setText(Integer.toString(pTowerBase.getTowerType().getCost()));

        // set our current row/col, make sure to account for our cancel +
        // sell button
        int currentCol = 0;
        int currentRow = 0;

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

        // get this towers upgrade types
        for (final TowerTypes tt : pTowerBase.getTowerUpgradeTypes()) {

            /** final TiledSprite upgradableTower = new TiledSprite(this.mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentCol) + (TOWERS_UI_ITEM_WIDTH * currentCol),
                    mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentRow) + (TOWERS_UI_ITEM_WIDTH * currentRow),
                    mTowerManager.getTowerMenuTiledTextureRegionClone()) {
            */

            //PUT BORDERS ABOVE THESE FROM THE UI SPRITES THEY COME IN LARGE SIZE

            final float lX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentCol) + (TOWERS_UI_ITEM_WIDTH * currentCol);
            final float lY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentRow) + (TOWERS_UI_ITEM_HEIGHT * currentRow);

            final float placeX = (TOWERS_UI_ITEM_WIDTH / 2) + (lX - tt.getTowerTileCenterX());
            final float placeY = (TOWERS_UI_ITEM_HEIGHT - TOWERS_UI_SPACER) + (lY - tt.getTowerTileCenterY());

            final TiledSprite upgradableTower = new TiledSprite(placeX, placeY, Registry.sTowerTextureRegions[tt.getTowerTopSprite().getId()].deepCopy());
            upgradableTower.setScale(.90f);

            if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                upgradableTower.setAlpha(1f);

            } else {
                upgradableTower.setAlpha(.25f);

            }

            if(null != tt.getTowerBaseSprite()) {
                upgradableTower.setCurrentTileIndex(tt.getTowerBaseSprite().getInitialTileIndex());
                //Debug.i("getTowerBaseSprite=" + tt.getTowerBaseSprite().getInitialTileIndex());
            }

            if(null != tt.getTowerTopSprite()) {
                upgradableTower.setCurrentTileIndex(tt.getTowerTopSprite().getInitialTileIndex());
                //Debug.i("getTowerTopSprite=" + tt.getTowerTopSprite().getInitialTileIndex());
            }

            upgradableTower.setZIndex(100);
            attachChild(upgradableTower);
            registerTouchArea(upgradableTower);
            mUpgradeSprites.add(upgradableTower);

            final Rectangle rect = new Rectangle(lX, lY, TOWERS_UI_ITEM_WIDTH, TOWERS_UI_ITEM_HEIGHT) {
                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                        this.setAlpha(1f);

                        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                            TowerUpgraderBack.this.isValidClick = true;
                        }
                        if (TowerUpgraderBack.this.isValidClick && pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP && GameActivity.SCROLL_MODE_DISABLED == Registry.sGameActivity.getScrollMode()) {
                            Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        TowerUpgraderBack.this.upgrade(pTowerBase, tt);
                                        TowerUpgraderBack.this.isValidClick = false;
                                    }
                                }
                            );
                        }

                        return true;
                    } else {
                        this.setAlpha(.25f);

                        Registry.sGameActivity.userNotifyShort("This tower requires the \"" + tt.getPlayerRewardType() + "\" reward to be unlocked. Visit the Game Points section from the main menu for more information about earning and spending Game Points (The currency \"GP\" you see above).");

                        return false;
                    }
                }
            };
            rect.setZIndex(90);
            attachChild(rect);

            if (++currentCol >= mTotalColumns) {
                currentRow++;
                currentCol = 0;
            }
        }

        sortChildren();

        Debug.i("SpriteSheets.UI_128x128_01.ordinal()=" + SpriteSheets.UI_128x128_01.ordinal());

        final TiledSprite ts = new TiledSprite(25, 25, Registry.sSpriteTextureRegions[SpriteSheets.UI_128x128_01.ordinal()].deepCopy());
        //final TiledSprite ts = new TiledSprite(25, 25, Registry.sTowerTextureRegions[TowerTypes.ARCH_MAGE.getTowerTopSprite().getId()].deepCopy());
        ts.setZIndex(110);
        ts.setCurrentTileIndex(0);
        attachChild(ts);


        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_DISABLED);
        Registry.sGameActivity.getCamera().setHUD(TowerUpgraderBack.this);

        return this;
    }

    public TowerUpgraderBack hide() {

        for (final TiledSprite ts : mUpgradeSprites) {
            unregisterTouchArea(ts);
            detachChild(ts);
            ts.reset();
        }

        mUpgradeSprites.clear();

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

    public TowerUpgraderBack upgrade(final Tower pTowerSource, final TowerTypes pTowerType) {
        Debug.i("TowerUpgrader :: upgrade :: pTowerType=" + pTowerType);
        mTowerManager.upgrade(pTowerSource, pTowerType);
        hide();

        return this;
    }
}