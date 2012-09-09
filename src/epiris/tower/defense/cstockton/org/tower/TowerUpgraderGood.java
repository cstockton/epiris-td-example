package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.config.Sprites;
import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;
import epiris.tower.defense.cstockton.org.util.Debug;

import java.util.ArrayList;

public class TowerUpgraderGood extends HUD {

    // constants for tower upgrader
    static public final int[] TOWERS_UI_CANCEL = new int[] { 3, 1 };
    static public final int[] TOWERS_UI_SELL = new int[] { 4, 0 };
    static public final int[] TOWERS_UI_RADIUS = new int[] { 3, 0 };

    // z indexs
    static public final int Z_BACKGROUND = 100;
    static public final int Z_BACKGROUND_REC = 200;
    static public final int Z_TOWER_BG = 300;
    static public final int Z_TOWER = 400;
    static public final int Z_TOWER_BORDER = 500;
    static public final int Z_TOWER_HANGING_ICONS = 600;
    static public final int Z_TOWER_TEXT = 700;

    // ui size specification
    static public final int TOWERS_UI_SPACER = 16;
    static public final int TOWERS_UI_ITEM_WIDTH = 96;
    static public final int TOWERS_UI_ITEM_HEIGHT = TOWERS_UI_ITEM_WIDTH;
    static public final int TOWERS_UI_CAMERA_WIDTH = Registry.sGameActivity.getCameraWidth();
    static public final int TOWERS_UI_CAMERA_HEIGHT = Registry.sGameActivity.getCameraHeight();

    private final TowerManager mTowerManager;

    final public Rectangle mPositionMarker;
    final public Sprite mRadiusMarker;
    final public Sprite mBorder;

    final private TiledSprite mTowerUpgradeDamage;
    final private TiledSprite mTowerUpgradeSpeed;
    final private TiledSprite mTowerUpgradeRange;
    final private TiledSprite mTowerSell;

    final private AlignedText mTowerTitleText;
    //final private AlignedText mTowerUpgradeDamageLevelText;
    //final private AlignedText mTowerUpgradeSpeedLevelText;
    final private AlignedText mTowerUpgradeRangeLevelText;
    //final private AlignedText mTowerUpgradeDamageCostText;
    //final private AlignedText mTowerUpgradeSpeedCostText;
    final private AlignedText mTowerUpgradeRangeCostText;
    final private AlignedText mTowerSellText;

    private final ArrayList<TiledSprite> mUpgradeSprites = new ArrayList<TiledSprite>();
    private final ArrayList<AlignedText> mUpgradeText = new ArrayList<AlignedText>();
    final TiledTextureRegion mTowerMenuTiledTextureRegion;
    Tower mCurrentTowerBase;

    boolean isValidClick = false;
    int mTotalColumns;
    int mTotalRows;

    int mBoxWidth;
    int mBoxHeight;

    int mBoxX;
    int mBoxY;

    public TowerUpgraderGood(final TowerManager pTowerManager, final TiledTextureRegion pTowerMenuTiledTextureRegion) {

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
        mTotalColumns = 3;
        mTotalRows = 2;

        // decide the width of our box
        mBoxWidth = TOWERS_UI_SPACER + (mTotalColumns * (TOWERS_UI_ITEM_WIDTH + TOWERS_UI_SPACER));
        mBoxHeight = TOWERS_UI_SPACER + (mTotalRows * (TOWERS_UI_ITEM_HEIGHT + TOWERS_UI_SPACER));

        // decide our box x/y coords
        mBoxX = (TOWERS_UI_CAMERA_WIDTH / 2) - (mBoxWidth / 2);
        mBoxY = ((TOWERS_UI_CAMERA_HEIGHT / 2) - (mBoxHeight / 2) - 64); // -64 for hanging tower icons

        // our border
        mBorder = new Sprite(mBoxX, mBoxY, Registry.sTowerUpgraderBorderTextureRegion.deepCopy());
        mBorder.setZIndex(Z_BACKGROUND);
        attachChild(mBorder);

        // create our heading text
        mTowerTitleText = new AlignedText(mBoxX + (mBoxWidth / 2), mBoxY - 24, Registry.sFontWhite24, "", HorizontalAlign.CENTER, 128);
        mTowerTitleText.setZIndex(Z_TOWER_TEXT);
        mTowerTitleText.setColor(.5f, .5f, .5f);
        attachChild(mTowerTitleText);

        // create the sale button sprite
        mTowerUpgradeDamage = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_HEIGHT * 0) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.TOWER_UPGRADER_HANGING_ICON_DAMAGE.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                return false;
            }
        };

        mTowerUpgradeDamage.setCurrentTileIndex(Sprites.TOWER_UPGRADER_HANGING_ICON_DAMAGE.getTileIndex());
        mTowerUpgradeDamage.setZIndex(Z_TOWER_HANGING_ICONS);
        attachChild(mTowerUpgradeDamage);

        // create the sale button sprite
        mTowerUpgradeSpeed = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_HEIGHT * 1) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.TOWER_UPGRADER_HANGING_ICON_SPEED.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                return false;
            }
        };

        mTowerUpgradeSpeed.setCurrentTileIndex(Sprites.TOWER_UPGRADER_HANGING_ICON_SPEED.getTileIndex());
        mTowerUpgradeSpeed.setZIndex(Z_TOWER_HANGING_ICONS);
        attachChild(mTowerUpgradeSpeed);

        // create the sale button sprite
        mTowerUpgradeRange = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 2) + (TOWERS_UI_ITEM_HEIGHT * 2) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.TOWER_UPGRADER_HANGING_ICON_RANGE.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                return false;
            }
        };

        mTowerUpgradeRange.setCurrentTileIndex(Sprites.TOWER_UPGRADER_HANGING_ICON_RANGE.getTileIndex());
        mTowerUpgradeRange.setZIndex(Z_TOWER_HANGING_ICONS);
        attachChild(mTowerUpgradeRange);

        // create our upgrade cost text
        mTowerUpgradeRangeCostText = new AlignedText(mTowerUpgradeRange.getX() + 32, mTowerUpgradeRange.getY() + 4, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 9);
        mTowerUpgradeRangeCostText.setZIndex(Z_TOWER_TEXT);
        mTowerUpgradeRangeCostText.setColor(1f, .84f, 0f);
        attachChild(mTowerUpgradeRangeCostText);

        // create our upgrade cost text
        mTowerUpgradeRangeLevelText = new AlignedText(mTowerUpgradeRange.getX() + 32, mTowerUpgradeRange.getY() + 48, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 24);
        mTowerUpgradeRangeLevelText.setZIndex(Z_TOWER_TEXT);
        mTowerUpgradeRangeLevelText.setColor(1f, 1f, 1f);
        attachChild(mTowerUpgradeRangeLevelText);

        // create the sale button sprite
        mTowerSell = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalColumns) + (TOWERS_UI_ITEM_HEIGHT * mTotalColumns),
            mBoxY + TOWERS_UI_SPACER + (((TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows)) / 2) - (TOWERS_UI_SPACER / 2) - 32,
            Sprites.TOWER_UPGRADER_HANGING_ICON_SELL.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerUpgraderGood.this.sell();
                            }
                        }
                    );
                }
                return true;
            }
        };

        mTowerSell.setCurrentTileIndex(Sprites.TOWER_UPGRADER_HANGING_ICON_SELL.getTileIndex());
        mTowerSell.setZIndex(Z_TOWER_HANGING_ICONS);
        attachChild(mTowerSell);

        // create our sell text
        mTowerSellText = new AlignedText(mTowerSell.getX() + 32, mTowerSell.getY() + 4, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 9);
        mTowerSellText.setZIndex(Z_TOWER_TEXT);
        mTowerSellText.setColor(1f, .84f, 0f);
        attachChild(mTowerSellText);

        sortChildren();

    }

    public boolean sell() {

        // sell this tower
        mTowerManager.sellTower(TowerUpgraderGood.this.mCurrentTowerBase);
        hide();

        return true;
    }

    public TowerUpgraderGood show(final Tower pTowerBase) {
        //Debug.i("TowerUpgrader :: show :: type=" + pTowerBase.getTowerType());

        // disable tower interaction
        TowerManager.ENABLE_TOWER_INTERACTION = false;

        // set our current tower base
        mCurrentTowerBase = pTowerBase;

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


        // set the title text
        mTowerTitleText.setText(pTowerBase.getTowerType().mName);

        // set the sale price
        mTowerSellText.setText("+" + Integer.toString(pTowerBase.getTowerType().getCost() / 2));

        mTowerUpgradeRangeCostText.setText("-40000");
        mTowerUpgradeRangeLevelText.setText("+1500%");

        // attach the radius marker to the position marker, then to the scene
        Registry.sSceneLayerTowers.attachChild(mPositionMarker);

        // get this towers upgrade types
        for (final TowerTypes tt : pTowerBase.getTowerUpgradeTypes()) {
            final float lX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentCol) + (TOWERS_UI_ITEM_WIDTH * currentCol);
            final float lY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentRow) + (TOWERS_UI_ITEM_HEIGHT * currentRow);

            // create the upgradable tower sprite
            final TiledSprite tower = new TiledSprite(lX, lY, Registry.sTowerTextureRegions[tt.getTowerTopSprite().getId()].deepCopy()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                        this.setAlpha(1f);

                        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                            TowerUpgraderGood.this.isValidClick = true;
                        }
                        if (TowerUpgraderGood.this.isValidClick && pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP && GameActivity.SCROLL_MODE_DISABLED == Registry.sGameActivity.getScrollMode()) {
                            Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        TowerUpgraderGood.this.upgrade(pTowerBase, tt);
                                        TowerUpgraderGood.this.isValidClick = false;
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

            // create the border bg
            final TiledSprite towerBackground = new TiledSprite(lX, lY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UI_BG.getSpriteSheet().ordinal()].deepCopy()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                        this.setAlpha(1f);

                        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                            TowerUpgraderGood.this.isValidClick = true;
                        }
                        if (TowerUpgraderGood.this.isValidClick && pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP && GameActivity.SCROLL_MODE_DISABLED == Registry.sGameActivity.getScrollMode()) {
                            Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        TowerUpgraderGood.this.upgrade(pTowerBase, tt);
                                        TowerUpgraderGood.this.isValidClick = false;
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

            // create our sell text
            final AlignedText towerSellText = new AlignedText(lX + (TOWERS_UI_ITEM_WIDTH / 2), lY + 6, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 9);
            towerSellText.setColor(1f, .84f, 0f);
            towerSellText.setText("-" + Integer.toString(pTowerBase.getTowerType().getCost()));

            // create the tower border
            final TiledSprite towerBorder = new TiledSprite(lX, lY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UI_BG.getSpriteSheet().ordinal()].deepCopy());

            // set alphas
            if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                tower.setAlpha(1f);
                towerBackground.setAlpha(1f);
                towerBorder.setAlpha(1f);

            } else {
                tower.setAlpha(.25f);
                towerBackground.setAlpha(.25f);
                towerBorder.setAlpha(.25f);

            }

            // set tile index's
            towerBackground.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UI_BG.getTileIndex());
            towerBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UI_BORDER.getTileIndex());

            // set tile index's for tower, which is slightly different
            if(null != tt.getTowerBaseSprite()) {
                tower.setCurrentTileIndex(tt.getTowerBaseSprite().getInitialTileIndex());
            }
            if(null != tt.getTowerTopSprite()) {
                tower.setCurrentTileIndex(tt.getTowerTopSprite().getInitialTileIndex());
            }

            // set zindex's
            towerBackground.setZIndex(Z_TOWER_BG);
            tower.setZIndex(Z_TOWER);
            towerBorder.setZIndex(Z_TOWER_BORDER);
            towerSellText.setZIndex(Z_TOWER_TEXT);

            // set tower specific widths
            tower.setWidth(TOWERS_UI_ITEM_WIDTH);
            tower.setHeight(TOWERS_UI_ITEM_WIDTH);

            // attach all
            attachChild(towerBackground);
            attachChild(tower);
            attachChild(towerBorder);
            attachChild(towerSellText);


            // register touch areas
            registerTouchArea(towerBackground);
            registerTouchArea(tower);

            mUpgradeSprites.add(towerBackground);
            mUpgradeSprites.add(tower);
            mUpgradeSprites.add(towerBorder);
            mUpgradeText.add(towerSellText);

            if (++currentCol >= mTotalColumns) {
                currentRow++;
                currentCol = 0;
            }
        }

        sortChildren();

        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_DISABLED);
        Registry.sGameActivity.getCamera().setHUD(TowerUpgraderGood.this);

        return this;
    }

    public TowerUpgraderGood hide() {

        for (final TiledSprite ts : mUpgradeSprites) {
            unregisterTouchArea(ts);
            detachChild(ts);
            ts.reset();
        }

        for (final AlignedText at : mUpgradeText) {
            detachChild(at);
            at.reset();
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

    public TowerUpgraderGood upgrade(final Tower pTowerSource, final TowerTypes pTowerType) {
        Debug.i("TowerUpgrader :: upgrade :: pTowerType=" + pTowerType);
        mTowerManager.upgrade(pTowerSource, pTowerType);
        hide();

        return this;
    }
}