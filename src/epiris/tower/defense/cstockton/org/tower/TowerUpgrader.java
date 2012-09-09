package epiris.tower.defense.cstockton.org.tower;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.config.Sprites;
import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.game.GameActivity;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;
import epiris.tower.defense.cstockton.org.util.Debug;

import java.util.ArrayList;

public class TowerUpgrader extends HUD {

    // const for tower bonus's
    static public final int TOWER_BONUS_DAMAGE = 1;
    static public final int TOWER_BONUS_SPEED = 2;
    static public final int TOWER_BONUS_RANGE = 3;
    static public final int TOWER_BONUS_AFFECT = 4;
    static public final int TOWER_BONUS_PURITY = 5;
    static public final int TOWER_BONUS_ULTIMATE = 6;

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

    final public Rectangle mPositionMarker;
    final public Sprite mRadiusMarker;
    final public Sprite mBorder;

    final private TiledSprite mTowerUpgraderSell;
    final private TiledSprite mTowerUpgraderInfo;
    final private TiledSprite mTowerUpgraderClose;

    final private AlignedText mTowerTitleText;
    final private AlignedText mTowerSellText;
    final private AlignedText mTowerSellTextBg;

    private final ArrayList<TiledSprite> mUpgradeSprites = new ArrayList<TiledSprite>();
    private final ArrayList<AlignedText> mUpgradeText = new ArrayList<AlignedText>();

    Tower mCurrentTowerBase;

    boolean isValidClick = false;
    int mTotalColumns;
    int mTotalRows;

    int mBoxWidth;
    int mBoxHeight;

    int mBoxX;
    int mBoxY;

    public TowerUpgrader() {

        // create our position marker
        mPositionMarker = new Rectangle(0, 0, TowerManager.TOWER_WIDTH, TowerManager.TOWER_HEIGHT);
        mPositionMarker.setColor(1, 0, 0, 0.25f);
        mPositionMarker.setVisible(false);
        mPositionMarker.setIgnoreUpdate(true);

        // setup the radius marker
        mRadiusMarker = new Sprite(0, 0, Registry.sTowerCircleTextureRegion.deepCopy());
        mRadiusMarker.setAlpha(0.25f);
        mRadiusMarker.setColor(.25f, .25f, .25f);
        mRadiusMarker.setVisible(false);
        mRadiusMarker.setIgnoreUpdate(true);

        // attach the radius marker to the position marker, then to the scene
        mPositionMarker.attachChild(mRadiusMarker);

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
        mTowerUpgraderSell = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_HEIGHT * 0) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.UI_ICON_SELL.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerUpgrader.this.sell();
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        mTowerUpgraderSell.setCurrentTileIndex(Sprites.UI_ICON_SELL.getTileIndex());
        mTowerUpgraderSell.setZIndex(Z_TOWER_HANGING_ICONS);
        registerTouchArea(mTowerUpgraderSell);
        attachChild(mTowerUpgraderSell);

        // create the sale button sprite
        mTowerUpgraderInfo = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_HEIGHT * 1) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.UI_ICON_INFO.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerUpgrader.this.hide();
                                Registry.sTowerManager.getTowerInfo().show(TowerUpgrader.this.mCurrentTowerBase);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        mTowerUpgraderInfo.setCurrentTileIndex(Sprites.UI_ICON_INFO.getTileIndex());
        mTowerUpgraderInfo.setZIndex(Z_TOWER_HANGING_ICONS);
        registerTouchArea(mTowerUpgraderInfo);
        attachChild(mTowerUpgraderInfo);

        // create the sale button sprite
        mTowerUpgraderClose = new TiledSprite(
            mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 2) + (TOWERS_UI_ITEM_HEIGHT * 2) + ((96-64) / 2),
            mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * mTotalRows) + (TOWERS_UI_ITEM_WIDTH * mTotalRows),
            Sprites.UI_ICON_CLOSE.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.hide();
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        mTowerUpgraderClose.setCurrentTileIndex(Sprites.UI_ICON_CLOSE.getTileIndex());
        mTowerUpgraderClose.setZIndex(Z_TOWER_HANGING_ICONS);
        registerTouchArea(mTowerUpgraderClose);
        attachChild(mTowerUpgraderClose);

        // create our sell text
        mTowerSellText = new AlignedText(mTowerUpgraderSell.getX() + 32, mTowerUpgraderSell.getY(), Registry.sFontWhite16, "", HorizontalAlign.CENTER, 9);
        mTowerSellText.setZIndex(Z_TOWER_TEXT);
        mTowerSellText.setColor(1f, .84f, 0f);
        attachChild(mTowerSellText);

        // create our sell text
        mTowerSellTextBg = new AlignedText(mTowerUpgraderSell.getX() + 32, mTowerUpgraderSell.getY(), Registry.sFontWhite16, "", HorizontalAlign.CENTER, 9);
        mTowerSellTextBg.setZIndex(Z_TOWER_TEXT - 10);
        mTowerSellTextBg.setColor(0, 0f, 0f);
        mTowerSellTextBg.setScale(1.1f);
        attachChild(mTowerSellTextBg);

        final AlignedText towerUpgraderSellText = new AlignedText(mTowerUpgraderSell.getX() + 32, mTowerUpgraderSell.getY() + 44, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 4);
        towerUpgraderSellText.setZIndex(Z_TOWER_TEXT);
        towerUpgraderSellText.setColor(.0f, .0f, .0f);
        towerUpgraderSellText.setText("Sell");
        attachChild(towerUpgraderSellText);

        final AlignedText towerUpgraderInfoText = new AlignedText(mTowerUpgraderInfo.getX() + 32, mTowerUpgraderInfo.getY() + 44, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 4);
        towerUpgraderInfoText.setZIndex(Z_TOWER_TEXT);
        towerUpgraderInfoText.setColor(.0f, .0f, .0f);
        towerUpgraderInfoText.setText("Info");
        attachChild(towerUpgraderInfoText);

        final AlignedText towerUpgraderClose = new AlignedText(mTowerUpgraderClose.getX() + 32, mTowerUpgraderClose.getY() + 44, Registry.sFontWhite16, "", HorizontalAlign.CENTER, 5);
        towerUpgraderClose.setZIndex(Z_TOWER_TEXT);
        towerUpgraderClose.setColor(.0f, .0f, .0f);
        towerUpgraderClose.setText("Close");
        attachChild(towerUpgraderClose);

        sortChildren();

    }

    public boolean sell() {

        // sell this tower
        Registry.sTowerManager.sellTower(TowerUpgrader.this.mCurrentTowerBase);
        hide();

        return true;
    }

    public TowerUpgrader show(final Tower pTowerBase) {
        //Debug.i("TowerUpgrader :: show :: type=" + pTowerBase.getTowerType());

        if(null == pTowerBase) {
            return this;
        }

        // disable tower interaction
        TowerManager.ENABLE_TOWER_INTERACTION = false;

        // set our current tower base
        mCurrentTowerBase = pTowerBase;

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
        final String gold = "+" + Integer.toString(pTowerBase.getTowerType().getCost() / 2);
        mTowerSellText.setText(gold);
        mTowerSellTextBg.setText(gold);

        // attach the radius marker to the position marker, then to the scene
        Registry.sSceneLayerTowers.attachChild(mPositionMarker);


        if(pTowerBase.getTowerUpgradeTypes().length > 0) {
            buildTowerUpgrades();

        } else {
            buildTowerUpgradesIcons();

        }

        sortChildren();

        Registry.sGameActivity.setScrollMode(GameActivity.SCROLL_MODE_DISABLED);
        Registry.sGameActivity.getCamera().setHUD(TowerUpgrader.this);

        return this;
    }

    public TowerUpgrader hide() {

        synchronized (mUpgradeSprites) {
            for (final TiledSprite ts : mUpgradeSprites) {
                unregisterTouchArea(ts);
                detachChild(ts);
                //ts.reset();
            }

            for (final AlignedText at : mUpgradeText) {
                detachChild(at);
                //at.reset();
            }
        }

        mUpgradeSprites.clear();
        mUpgradeText.clear();

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

    public TowerUpgrader upgrade(final Tower pTowerSource, final TowerTypes pTowerType) {
        Debug.i("TowerUpgrader :: upgrade :: pTowerType=" + pTowerType);
        Registry.sTowerManager.upgrade(pTowerSource, pTowerType);
        hide();

        return this;
    }

    public void handleUpgrade(final int pBonusType, final AlignedText pAlignedText) {
        final int cost = getUpgradeCost(pBonusType);
        if(cost < Registry.sLocalPlayer.getGold()) {

            if(isUpgradeable(pBonusType)) {

                Registry.sLocalPlayer.deductGold(cost);

                increaseBonusLevel(pBonusType);
                mCurrentTowerBase.onTowerBonusChange();
                updateUpgradeText(pBonusType, pAlignedText);

            } else {
                Registry.sGameActivity.userNotifyShort("This upgrade has reached the maximun level.");
            }
        } else {
            Registry.sGameActivity.userNotifyShort("This upgrade requires " + cost + " gold, but you only have " + Registry.sLocalPlayer.getGold() + ".");

        }
    }

    public void updateUpgradeText(final int pBonusType, final AlignedText pAlignedText) {
        switch(pBonusType) {
            case TOWER_BONUS_RANGE: {
                pAlignedText.setText(getUpgradeAffect(pBonusType) + " -" + getUpgradeCost(pBonusType));

                // update radius marker
                mRadiusMarker.setWidth(mCurrentTowerBase.getTowerRange() * 2);
                mRadiusMarker.setHeight(mCurrentTowerBase.getTowerRange() * 2);

                // set the position of the marker
                mRadiusMarker.setPosition(
                    (-mCurrentTowerBase.getTowerRange()) + (TowerManager.TOWER_WIDTH / 2),
                    (-mCurrentTowerBase.getTowerRange()) + (TowerManager.TOWER_HEIGHT / 2)
                );

            } break;

            default: {
                pAlignedText.setText(getUpgradeAffect(pBonusType) + " -" + getUpgradeCost(pBonusType));
            } break;
        }
    }

    public void increaseBonusLevel(final int pBonusType) {
        switch(pBonusType) {
            case TOWER_BONUS_DAMAGE: {
                mCurrentTowerBase.increaseDamageBonusLevel();
            } break;

            case TOWER_BONUS_SPEED: {
                mCurrentTowerBase.increaseSpeedBonusLevel();
            } break;

            case TOWER_BONUS_RANGE: {
                mCurrentTowerBase.increaseRangeBonusLevel();
            } break;

            case TOWER_BONUS_AFFECT: {
                mCurrentTowerBase.increaseAffectBonusLevel();
            } break;

            case TOWER_BONUS_PURITY: {
                mCurrentTowerBase.increasePurityBonusLevel();
            } break;

            case TOWER_BONUS_ULTIMATE: {
                mCurrentTowerBase.increaseUltimateBonusLevel();
            } break;
        }
    }

    public String getUpgradeAffect(final int pBonusType) {
        switch(pBonusType) {

            default:
            case TOWER_BONUS_DAMAGE: {
                return "+" + mCurrentTowerBase.getDamageBonusValue() + "%";
            }

            case TOWER_BONUS_SPEED: {
                return "+" + mCurrentTowerBase.getSpeedBonusValue() + "%";
            }

            case TOWER_BONUS_RANGE: {
                return "+" + mCurrentTowerBase.getRangeBonusValue();
            }

            case TOWER_BONUS_AFFECT: {
                return "+" + mCurrentTowerBase.getAffectBonusValue() + "%";
            }

            case TOWER_BONUS_PURITY: {
                return "+" + mCurrentTowerBase.getPurityBonusValue();
            }

            case TOWER_BONUS_ULTIMATE: {
                return "Lvl " + mCurrentTowerBase.getUltimateBonusValue();
            }
        }
    }

    public int getUpgradeCost(final int pBonusType) {
        final int towerCost = mCurrentTowerBase.getTowerCost();

        switch(pBonusType) {
            case TOWER_BONUS_DAMAGE: {
                final int currentLevel = mCurrentTowerBase.getDamageBonusLevel();

                return (currentLevel + 1) * towerCost;
            }

            case TOWER_BONUS_SPEED: {
                final int currentLevel = mCurrentTowerBase.getSpeedBonusLevel();

                return (currentLevel + 1) * towerCost;
            }

            case TOWER_BONUS_RANGE: {
                final int currentLevel = mCurrentTowerBase.getRangeBonusLevel();

                return (currentLevel + 1) * towerCost;
            }

            case TOWER_BONUS_AFFECT: {
                final int currentLevel = mCurrentTowerBase.getAffectBonusLevel();

                return (currentLevel + 1) * towerCost;
            }

            case TOWER_BONUS_PURITY: {
                final int currentLevel = mCurrentTowerBase.getPurityBonusLevel();

                return (currentLevel + 1) * towerCost;
            }

            case TOWER_BONUS_ULTIMATE: {
                final int currentLevel = mCurrentTowerBase.getUltimateBonusLevel();

                return (((currentLevel + 1) * towerCost) * 10) * 2;
            }

            default: {
                return 0;
            }
        }
    }

    public boolean isUpgradeable(final int pBonusType) {
        switch(pBonusType) {
            case TOWER_BONUS_DAMAGE: {
                return true;
            }

            case TOWER_BONUS_SPEED: {
                return mCurrentTowerBase.getSpeedBonusLevel() < 20;
            }

            case TOWER_BONUS_RANGE: {
                return mCurrentTowerBase.getRangeBonusLevel() < 20;
            }

            case TOWER_BONUS_AFFECT: {
                return true;
            }

            case TOWER_BONUS_PURITY: {
                return true;
            }

            case TOWER_BONUS_ULTIMATE: {
                return true;
            }

            default: {
                return false;
            }
        }
    }

    public void buildTowerUpgradesIcons() {

        // DAMAGE
        int curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_WIDTH * 0);
        int curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_HEIGHT * 0);

        final AlignedText upgradeDamageText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradeDamageLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradeDamage = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_DAMAGE_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_DAMAGE, upgradeDamageLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradeDamageBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // SPEED
        curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_WIDTH * 1);
        curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_HEIGHT * 0);

        final AlignedText upgradeSpeedText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradeSpeedLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradeSpeed = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_SPEED_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_SPEED, upgradeSpeedLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradeSpeedBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // RANGE
        curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 2) + (TOWERS_UI_ITEM_WIDTH * 2);
        curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_HEIGHT * 0);

        final AlignedText upgradeRangeText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradeRangeLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradeRange = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_RANGE_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_RANGE, upgradeRangeLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradeRangeBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // ARMOR PEN
        curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 0) + (TOWERS_UI_ITEM_WIDTH * 0);
        curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_HEIGHT * 1);

        final AlignedText upgradeAffectText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradeAffectLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradeAffect = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_AFFECT_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_AFFECT, upgradeAffectLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradeAffectBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // MAGIC PEN
        curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_WIDTH * 1);
        curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_HEIGHT * 1);

        final AlignedText upgradePurityText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradePurityLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradePurity = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_PURITY_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_PURITY, upgradePurityLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradePurityBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // ULTIMATE BOOST
        curX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 2) + (TOWERS_UI_ITEM_WIDTH * 2);
        curY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * 1) + (TOWERS_UI_ITEM_HEIGHT * 1);

        final AlignedText upgradeUltimateText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + 6, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 12);
        final AlignedText upgradeUltimateLvlText = new AlignedText(curX + (TOWERS_UI_ITEM_WIDTH / 2), curY + TOWERS_UI_ITEM_HEIGHT - 19, Registry.sFontWhite12, "", HorizontalAlign.CENTER, 18);
        final TiledSprite upgradeUltimate = new TiledSprite(curX, curY, Sprites.TOWER_UPGRADER_UPGRADE_ULTIMATE_ICON.getSpriteSheetTiledTextureRegionClone()) {

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                                TowerUpgrader.this.handleUpgrade(TOWER_BONUS_ULTIMATE, upgradeUltimateLvlText);
                            }
                        }
                    );

                    return false;
                }

                return true;
            }
        };

        final TiledSprite upgradeUltimateBorder = new TiledSprite(curX, curY, Registry.sSpriteTextureRegions[Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getSpriteSheet().ordinal()].deepCopy());

        // setup
        upgradeDamageBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());
        upgradeSpeedBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());
        upgradeRangeBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());
        upgradeAffectBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());
        upgradePurityBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());
        upgradeUltimateBorder.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_BORDER.getTileIndex());

        upgradeDamageBorder.setZIndex(Z_TOWER_BORDER);
        upgradeSpeedBorder.setZIndex(Z_TOWER_BORDER);
        upgradeRangeBorder.setZIndex(Z_TOWER_BORDER);
        upgradeAffectBorder.setZIndex(Z_TOWER_BORDER);
        upgradePurityBorder.setZIndex(Z_TOWER_BORDER);
        upgradeUltimateBorder.setZIndex(Z_TOWER_BORDER);

        attachChild(upgradeDamageBorder);
        attachChild(upgradeSpeedBorder);
        attachChild(upgradeRangeBorder);
        attachChild(upgradeAffectBorder);
        attachChild(upgradePurityBorder);
        attachChild(upgradeUltimateBorder);

        mUpgradeSprites.add(upgradeDamageBorder);
        mUpgradeSprites.add(upgradeSpeedBorder);
        mUpgradeSprites.add(upgradeRangeBorder);
        mUpgradeSprites.add(upgradeAffectBorder);
        mUpgradeSprites.add(upgradePurityBorder);
        mUpgradeSprites.add(upgradeUltimateBorder);

        upgradeDamage.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_DAMAGE_ICON.getTileIndex());
        upgradeSpeed.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_SPEED_ICON.getTileIndex());
        upgradeRange.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_RANGE_ICON.getTileIndex());
        upgradeAffect.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_AFFECT_ICON.getTileIndex());
        upgradePurity.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_PURITY_ICON.getTileIndex());
        upgradeUltimate.setCurrentTileIndex(Sprites.TOWER_UPGRADER_UPGRADE_ULTIMATE_ICON.getTileIndex());

        upgradeDamage.setZIndex(Z_TOWER);
        upgradeSpeed.setZIndex(Z_TOWER);
        upgradeRange.setZIndex(Z_TOWER);
        upgradePurity.setZIndex(Z_TOWER);
        upgradeAffect.setZIndex(Z_TOWER);
        upgradeUltimate.setZIndex(Z_TOWER);

        attachChild(upgradeDamage);
        attachChild(upgradeSpeed);
        attachChild(upgradeRange);
        attachChild(upgradePurity);
        attachChild(upgradeAffect);
        attachChild(upgradeUltimate);

        mUpgradeSprites.add(upgradeDamage);
        mUpgradeSprites.add(upgradeSpeed);
        mUpgradeSprites.add(upgradeRange);
        mUpgradeSprites.add(upgradePurity);
        mUpgradeSprites.add(upgradeAffect);
        mUpgradeSprites.add(upgradeUltimate);

        upgradeDamageText.setZIndex(Z_TOWER_TEXT);
        upgradeDamageLvlText.setZIndex(Z_TOWER_TEXT);
        upgradeSpeedText.setZIndex(Z_TOWER_TEXT);
        upgradeSpeedLvlText.setZIndex(Z_TOWER_TEXT);
        upgradeRangeText.setZIndex(Z_TOWER_TEXT);
        upgradeRangeLvlText.setZIndex(Z_TOWER_TEXT);
        upgradeAffectText.setZIndex(Z_TOWER_TEXT);
        upgradeAffectLvlText.setZIndex(Z_TOWER_TEXT);
        upgradePurityText.setZIndex(Z_TOWER_TEXT);
        upgradePurityLvlText.setZIndex(Z_TOWER_TEXT);
        upgradeUltimateText.setZIndex(Z_TOWER_TEXT);
        upgradeUltimateLvlText.setZIndex(Z_TOWER_TEXT);

        final float c1 = .1f;
        final float c2 = .1f;
        final float c3 = .1f;

        upgradeDamageText.setColor(c1, c2, c3);
        upgradeDamageLvlText.setColor(c1, c2, c3);
        upgradeSpeedText.setColor(c1, c2, c3);
        upgradeSpeedLvlText.setColor(c1, c2, c3);
        upgradeRangeText.setColor(c1, c2, c3);
        upgradeRangeLvlText.setColor(c1, c2, c3);
        upgradeAffectText.setColor(c1, c2, c3);
        upgradeAffectLvlText.setColor(c1, c2, c3);
        upgradePurityText.setColor(c1, c2, c3);
        upgradePurityLvlText.setColor(c1, c2, c3);
        upgradeUltimateText.setColor(c1, c2, c3);
        upgradeUltimateLvlText.setColor(c1, c2, c3);

        upgradeDamageText.setText("Damage");
        upgradeSpeedText.setText("Speed");
        upgradeRangeText.setText("Range");
        upgradeAffectText.setText("Affects");
        upgradePurityText.setText("Purity");
        upgradeUltimateText.setText("Ultimate");

        updateUpgradeText(TOWER_BONUS_DAMAGE, upgradeDamageLvlText);
        updateUpgradeText(TOWER_BONUS_SPEED, upgradeSpeedLvlText);
        updateUpgradeText(TOWER_BONUS_RANGE, upgradeRangeLvlText);
        updateUpgradeText(TOWER_BONUS_AFFECT, upgradeAffectLvlText);
        updateUpgradeText(TOWER_BONUS_PURITY, upgradePurityLvlText);
        updateUpgradeText(TOWER_BONUS_ULTIMATE, upgradeUltimateLvlText);

        attachChild(upgradeDamageText);
        attachChild(upgradeDamageLvlText);
        attachChild(upgradeSpeedText);
        attachChild(upgradeSpeedLvlText);
        attachChild(upgradeRangeText);
        attachChild(upgradeRangeLvlText);
        attachChild(upgradeAffectText);
        attachChild(upgradeAffectLvlText);
        attachChild(upgradePurityText);
        attachChild(upgradePurityLvlText);
        attachChild(upgradeUltimateText);
        attachChild(upgradeUltimateLvlText);

        mUpgradeText.add(upgradeDamageText);
        mUpgradeText.add(upgradeDamageLvlText);
        mUpgradeText.add(upgradeSpeedText);
        mUpgradeText.add(upgradeSpeedLvlText);
        mUpgradeText.add(upgradeRangeText);
        mUpgradeText.add(upgradeRangeLvlText);
        mUpgradeText.add(upgradeAffectText);
        mUpgradeText.add(upgradeAffectLvlText);
        mUpgradeText.add(upgradePurityText);
        mUpgradeText.add(upgradePurityLvlText);
        mUpgradeText.add(upgradeUltimateText);
        mUpgradeText.add(upgradeUltimateLvlText);

        registerTouchArea(upgradeDamage);
        registerTouchArea(upgradeSpeed);
        registerTouchArea(upgradeRange);
        registerTouchArea(upgradeAffect);
        registerTouchArea(upgradePurity);
        registerTouchArea(upgradeUltimate);

    }

    public void buildTowerUpgrades() {

        // set our current row/col
        int currentCol = 0;
        int currentRow = 0;

        // get this towers upgrade types
        for (final TowerTypes tt : mCurrentTowerBase.getTowerUpgradeTypes()) {
            final float lX = mBoxX + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentCol) + (TOWERS_UI_ITEM_WIDTH * currentCol);
            final float lY = mBoxY + TOWERS_UI_SPACER + (TOWERS_UI_SPACER * currentRow) + (TOWERS_UI_ITEM_HEIGHT * currentRow);

            // create the upgradable tower sprite
            final TiledSprite tower = new TiledSprite(lX, lY, Registry.sTowerTextureRegions[tt.getTowerTopSprite().getId()].deepCopy()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if(null == tt.getPlayerRewardType() || Registry.sPlayerRewards.isRewardEarned(tt.getPlayerRewardType())) {
                        this.setAlpha(1f);

                        if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                            TowerUpgrader.this.isValidClick = true;
                        }
                        if (TowerUpgrader.this.isValidClick && pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP && GameActivity.SCROLL_MODE_DISABLED == Registry.sGameActivity.getScrollMode()) {
                            Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        TowerUpgrader.this.upgrade(mCurrentTowerBase, tt);
                                        TowerUpgrader.this.isValidClick = false;
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
                            TowerUpgrader.this.isValidClick = true;
                        }
                        if (TowerUpgrader.this.isValidClick && pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP && GameActivity.SCROLL_MODE_DISABLED == Registry.sGameActivity.getScrollMode()) {
                            Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        TowerUpgrader.this.upgrade(mCurrentTowerBase, tt);
                                        TowerUpgrader.this.isValidClick = false;
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
            towerSellText.setText("-" + Integer.toString(mCurrentTowerBase.getTowerType().getCost()));

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
    }
}