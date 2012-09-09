package epiris.tower.defense.cstockton.org.ui;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnAreaTouchListener;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import epiris.tower.defense.cstockton.org.config.Sprites;
import epiris.tower.defense.cstockton.org.config.TowerTypes;
import epiris.tower.defense.cstockton.org.config.UiSmallSprites;
import epiris.tower.defense.cstockton.org.object.Registry;


public class Hud extends HUD implements IOnAreaTouchListener {

    public static final int TOP_SPACING = 8;
    public static final int TOP_SPRITE_GAP = -56;

    public static final int MENU_ITEM_WIDTH = 96;
    public static final int MENU_ITEM_HEIGHT = 96;
    public static final int MENU_ITEM_SPACING = 24;
    public static final int MENU_WIDTH = MENU_ITEM_WIDTH;
    public static final int MENU_HEIGHT = MENU_ITEM_HEIGHT * 3;

    public final int mCameraWidth;
    public final int mCameraHeight;

    public final Menu mMenu;
    public final HUD mEmptyHud;

    public TiledSprite mLivesIcon;
    public TiledSprite mGoldIcon;
    public TiledSprite mGamePointsIcon;
    public TiledSprite mScoreIcon;
    public TiledSprite mLevelIcon;

    public ChangeableText mChangeableTextScore;
    public ChangeableText mChangeableTextGold;
    public ChangeableText mChangeableTextGamePoints;
    public ChangeableText mChangeableTextLives;
    public ChangeableText mChangeableTextLevel;

    public Hud() {
        super();

        // camera dimensions
        mCameraWidth = Registry.sGameActivity.getCameraWidth();
        mCameraHeight = Registry.sGameActivity.getCameraHeight();

        // create a menu instance
        mMenu = new Menu();

        // create a empty hud, hack for hiding this one
        mEmptyHud = new HUD();

        setOnAreaTouchListener(this);

        // create a base entity
        attachChild(new Entity());

    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, ITouchArea pTouchArea, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        //Debug.i("x=" + pTouchAreaLocalX + " y=" + pTouchAreaLocalY);
        return true;
    }

    public void hide() {
        Registry.sGameActivity.getCamera().setHUD(mEmptyHud);
        setIgnoreUpdate(true);
    }

    public void show() {
        Registry.sGameActivity.getCamera().setHUD(this);
        setIgnoreUpdate(false);
    }

    public Menu getMenu() {
        return mMenu;
    }

    public void initialize() {


        /*
        final TiledSprite leftBorder = new TiledSprite(0, TOP_SPRITE_GAP, Registry.sSpriteTextureRegions[Sprites.UI_HUD_TOP_LEFT.getSpriteSheet().ordinal()].deepCopy());
        leftBorder.setCurrentTileIndex(Sprites.UI_HUD_TOP_LEFT.getTileIndex());
        leftBorder.setZIndex(-10);

        final TiledSprite rightBorder = new TiledSprite(mCameraWidth - 96, TOP_SPRITE_GAP, Registry.sSpriteTextureRegions[Sprites.UI_HUD_TOP_RIGHT.getSpriteSheet().ordinal()].deepCopy());
        rightBorder.setCurrentTileIndex(Sprites.UI_HUD_TOP_RIGHT.getTileIndex());
        rightBorder.setZIndex(-10);
        */

        final TiledSprite bg = new TiledSprite(0, TOP_SPRITE_GAP, Registry.sSpriteTextureRegions[Sprites.UI_HUD_TOP_BG.getSpriteSheet().ordinal()].deepCopy());
        bg.setCurrentTileIndex(Sprites.UI_HUD_TOP_BG.getTileIndex());
        bg.setZIndex(-15);
        bg.setWidth(mCameraWidth);

        getLastChild().attachChild(bg);
        //getLastChild().attachChild(leftBorder);
        //getLastChild().attachChild(rightBorder);

        // the width const
        final float widthConst = (mCameraWidth / 8);

        // the gold icon
        int order = 0;
        mGoldIcon = new TiledSprite((widthConst * order) + TOP_SPACING, TOP_SPACING, Registry.sUiSmallTextureRegion.deepCopy());
        mGoldIcon.setCurrentTileIndex(UiSmallSprites.GOLD.getCol(), UiSmallSprites.GOLD.getRow());
        mChangeableTextGold = new ChangeableText((widthConst * order) + (TOP_SPACING * 4), TOP_SPACING, Registry.sFontWhite12, Integer.toString(Registry.sLocalPlayer.getGold()), 7);
        mChangeableTextGold.setColor(0f, 0f, 0f);
        order++;

        // the score icon
        mScoreIcon = new TiledSprite((widthConst * order) + TOP_SPACING, TOP_SPACING, Registry.sUiSmallTextureRegion.deepCopy());
        mScoreIcon.setCurrentTileIndex(UiSmallSprites.SCORE.getCol(), UiSmallSprites.SCORE.getRow());
        mChangeableTextScore = new ChangeableText((widthConst * order) + (TOP_SPACING * 4), TOP_SPACING, Registry.sFontWhite12, Integer.toString(Registry.sLocalPlayer.getScore()), 7);
        mChangeableTextScore.setColor(0f, 0f, 0f);
        order++;

        // game points icon
        mGamePointsIcon = new TiledSprite((widthConst * order) + TOP_SPACING, TOP_SPACING, Registry.sUiSmallTextureRegion.deepCopy());
        mGamePointsIcon.setCurrentTileIndex(UiSmallSprites.GAMEPOINTS.getCol(), UiSmallSprites.GAMEPOINTS.getRow());
        mChangeableTextGamePoints = new ChangeableText((widthConst * order) + (TOP_SPACING * 4), TOP_SPACING, Registry.sFontWhite12, Integer.toString(Registry.sLocalPlayer.getGamePoints()), 7);
        mChangeableTextGamePoints.setColor(0f, 0f, 0f);
        order++;

        // add a spacer
        order++;
        order++;
        order++;

        // the level icon
        mLevelIcon = new TiledSprite((widthConst * order) + TOP_SPACING, TOP_SPACING, Registry.sUiSmallTextureRegion.deepCopy());
        mLevelIcon.setCurrentTileIndex(UiSmallSprites.LEVEL.getCol(), UiSmallSprites.LEVEL.getRow());
        mChangeableTextLevel = new ChangeableText((widthConst * order) + (TOP_SPACING * 4), TOP_SPACING, Registry.sFontWhite12, Integer.toString(Registry.sWaveManager.getCurrentWave()), 4);
        mChangeableTextLevel.setColor(0f, 0f, 0f);
        order++;

        // the lives icon
        mLivesIcon = new TiledSprite((widthConst * order) + TOP_SPACING, TOP_SPACING, Registry.sUiSmallTextureRegion.deepCopy());
        mLivesIcon.setCurrentTileIndex(UiSmallSprites.HEART.getCol(), UiSmallSprites.HEART.getRow());
        mChangeableTextLives = new ChangeableText((widthConst * order) + (TOP_SPACING * 4), TOP_SPACING, Registry.sFontWhite12, Integer.toString(Registry.sLocalPlayer.getLives()), 3);
        mChangeableTextLives.setColor(0f, 0f, 0f);
        order++;

        // attach the hud icons
        getLastChild().attachChild(mLivesIcon);
        getLastChild().attachChild(mLevelIcon);
        getLastChild().attachChild(mGoldIcon);
        getLastChild().attachChild(mGamePointsIcon);
        getLastChild().attachChild(mScoreIcon);

        // attach the hud text
        getLastChild().attachChild(mChangeableTextScore);
        getLastChild().attachChild(mChangeableTextGold);
        getLastChild().attachChild(mChangeableTextGamePoints);
        getLastChild().attachChild(mChangeableTextLives);
        getLastChild().attachChild(mChangeableTextLevel);

        // initialize the menu
        mMenu.init(this);

        // set the heads up display
        Registry.sGameActivity.getCamera().setHUD(this);

        // register some update handlers
        registerUpdateHandler(new TimerHandler(1, true, new ITimerCallback() {

            @Override
            public void onTimePassed(final TimerHandler pTimerHandler) {
                Hud.this.mChangeableTextScore.setText(Integer.toString(Registry.sLocalPlayer.getScore()));
                Hud.this.mChangeableTextGamePoints.setText(Integer.toString(Registry.sLocalPlayer.getGamePoints()));
                Hud.this.mChangeableTextGold.setText(Integer.toString(Registry.sLocalPlayer.getGold()));
                Hud.this.mChangeableTextLives.setText(Integer.toString(Registry.sLocalPlayer.getLives()));
                Hud.this.mChangeableTextLevel.setText(Integer.toString(Registry.sWaveManager.getCurrentWave()));
            }
        }));
    }

    public class Menu extends Entity {

        public static final int MENU_ITEM_WIDTH = 64;
        public static final int MENU_ITEM_HEIGHT = 64;

        public Menu() {
            super(0, 0);

            // position of the menu
            final int pStartX = Hud.this.mCameraWidth - Menu.MENU_ITEM_WIDTH - MENU_ITEM_SPACING;
            final int pY = Hud.this.mCameraHeight - Menu.MENU_ITEM_HEIGHT - MENU_ITEM_SPACING;

            // attach the tower buttons
            attachChild(new MenuSprite(TowerTypes.sStarterTowerThirdTree, pStartX - (MENU_ITEM_HEIGHT * 0), pY, Sprites.UI_ICON_TOWER_3.getTileIndex(), Sprites.UI_ICON_TOWER_3.getSpriteSheetTiledTextureRegionClone()));
            attachChild(new MenuSprite(TowerTypes.sStarterTowerSecondTree, pStartX - (MENU_ITEM_HEIGHT * 1) - MENU_ITEM_SPACING, pY, Sprites.UI_ICON_TOWER_2.getTileIndex(), Sprites.UI_ICON_TOWER_2.getSpriteSheetTiledTextureRegionClone()));
            attachChild(new MenuSprite(TowerTypes.sStarterTowerFirstTree, pStartX - (MENU_ITEM_HEIGHT * 2) - 50, pY, Sprites.UI_ICON_TOWER_1.getTileIndex(), Sprites.UI_ICON_TOWER_1.getSpriteSheetTiledTextureRegionClone()));

            final TiledSprite playButton = new TiledSprite(MENU_ITEM_SPACING, pY, Sprites.UI_ICON_PLAY.getSpriteSheetTiledTextureRegionClone()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {

                        if (getTextureRegion().getCurrentTileColumn() == Sprites.UI_ICON_PLAY.getTileIndex()) {

                            // request the game to start
                            Registry.sPlayerInteracter.requestStart();
                            Registry.sPlayerInteracter.requestRegularSpeed();

                            // set tile index to the fast forward button
                            setCurrentTileIndex(Sprites.UI_ICON_FAST_FORWARD.getTileIndex());

                        } else {

                            // request the fast speed
                            Registry.sPlayerInteracter.requestFastSpeed();

                            // set tile index to the play button
                            setCurrentTileIndex(Sprites.UI_ICON_PLAY.getTileIndex());

                        }
                    }
                    return true;
                }
            };
            playButton.setCurrentTileIndex(Sprites.UI_ICON_PLAY.getTileIndex());
            attachChild(playButton);

            final TiledSprite skipButton = new TiledSprite(MENU_ITEM_SPACING + 64 + MENU_ITEM_SPACING, pY, Sprites.UI_ICON_SKIP.getSpriteSheetTiledTextureRegionClone()) {

                @Override
                public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                    if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {

                        // request the next level
                        Registry.sPlayerInteracter.requestSkip();

                    }
                    return true;
                }
            };
            skipButton.setCurrentTileIndex(Sprites.UI_ICON_SKIP.getTileIndex());
            attachChild(skipButton);

        }

        public void init(Scene scene) {
            scene.getLastChild().attachChild(this);

            for (int i = 0; i < getChildCount(); i++) {
                scene.registerTouchArea((TiledSprite) getChild(i));
            }
        }

        public void show() {
            setVisible(true);
            setIgnoreUpdate(false);
        }

        public void hide() {
            setVisible(false);
            setIgnoreUpdate(true);
        }

        public class MenuSprite extends TiledSprite {

            private final TowerTypes mTowerType;
            private boolean mHasTowerPlacer;

            public MenuSprite(final TowerTypes pTowerType, final float pX, final float pY, final int pTileIndex, final TiledTextureRegion pTextureRegion) {
                super(pX, pY, MENU_ITEM_WIDTH, MENU_ITEM_HEIGHT, pTextureRegion);
                mTowerType = pTowerType;
                setCurrentTileIndex(pTileIndex);
            }

            @Override
            public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
                    if (Registry.sTowerManager.startTowerPlacement(MenuSprite.this.mTowerType)) {
                        MenuSprite.this.mHasTowerPlacer = true;
                    } else {
                        Registry.sGameActivity.userNotifyShort("Not enough gold!");
                        MenuSprite.this.mHasTowerPlacer = false;
                    }

                    return mHasTowerPlacer;
                }

                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    if (mHasTowerPlacer) {
                        Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                @Override
                                public void run() {
                                    Registry.sTowerManager.endTowerPlacement();
                                }
                            }
                        );

                        mHasTowerPlacer = false;

                        return false;
                    }
                }

                if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
                    if (mHasTowerPlacer) {
                        //Registry.sTowerManager.getTowerPlacer().setUpdateCoords(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                        return true;

                    } else {
                        return false;
                    }
                }

                return true;
            }
        };
    }
}
