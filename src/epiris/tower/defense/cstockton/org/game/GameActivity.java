package epiris.tower.defense.cstockton.org.game;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.ZoomCamera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.input.touch.controller.MultiTouch;
import org.anddev.andengine.extension.input.touch.controller.MultiTouchController;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector;
import org.anddev.andengine.extension.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.anddev.andengine.extension.input.touch.exception.MultiTouchException;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ClickDetector;
import org.anddev.andengine.input.touch.detector.ClickDetector.IClickDetectorListener;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.ETDPlay;
import epiris.tower.defense.cstockton.org.bgmanager.BackgroundManager;
import epiris.tower.defense.cstockton.org.bgmanager.BackgroundManagerIntervalTask;
import epiris.tower.defense.cstockton.org.config.CreepSpriteSheets;
import epiris.tower.defense.cstockton.org.config.EffectSpriteSheets;
import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.config.SpriteSheets;
import epiris.tower.defense.cstockton.org.config.TowerSpriteSheets;
import epiris.tower.defense.cstockton.org.config.UiLargeSprites;
import epiris.tower.defense.cstockton.org.config.UiSmallSprites;
import epiris.tower.defense.cstockton.org.creep.CreepFloatingTextManager;
import epiris.tower.defense.cstockton.org.creep.CreepManager;
import epiris.tower.defense.cstockton.org.effect.EffectManager;
import epiris.tower.defense.cstockton.org.map.Map;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.LocalPlayer;
import epiris.tower.defense.cstockton.org.player.PlayerInteracter;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievements;
import epiris.tower.defense.cstockton.org.player.data.PlayerData;
import epiris.tower.defense.cstockton.org.player.rewards.PlayerRewards;
import epiris.tower.defense.cstockton.org.player.statistics.PlayerStatistics;
import epiris.tower.defense.cstockton.org.player.storage.PlayerStorageConnector;
import epiris.tower.defense.cstockton.org.spell.SpellManager;
import epiris.tower.defense.cstockton.org.tower.Tower;
import epiris.tower.defense.cstockton.org.tower.TowerManager;
import epiris.tower.defense.cstockton.org.ui.Hud;
import epiris.tower.defense.cstockton.org.util.Debug;
import epiris.tower.defense.cstockton.org.wave.input.touch.HoldDetector;
import epiris.tower.defense.cstockton.org.wave.input.touch.HoldDetector.IHoldDetectorListener;

public class GameActivity extends BaseGameActivity implements IOnSceneTouchListener, IClickDetectorListener, IHoldDetectorListener, IScrollDetectorListener, IPinchZoomDetectorListener {
    public static final int SCROLL_MODE_CAMERA = 0;
    public static final int SCROLL_MODE_TOWER = 1;
    public static final int SCROLL_MODE_DISABLED = 2;

    private int mCurrentScrollMode = SCROLL_MODE_CAMERA;

    private int mCameraWidth;
    private int mCameraHeight;

    private float mPinchZoomStartedCameraZoomFactor;

    private ClickDetector mClickDetector;
    private HoldDetector mHoldDetector;
    private PinchZoomDetector mPinchZoomDetector;
    private SurfaceScrollDetector mScrollDetector;
    private ZoomCamera mZoomCamera;

    @Override
    public Engine onLoadEngine() {

        // @TODO make dynamic
        // set camera width/height
        mCameraWidth = 748;
        mCameraHeight = 480;

        // use a zoom camera
        mZoomCamera = new ZoomCamera(0, 0, mCameraWidth, mCameraHeight);
        mZoomCamera.setZoomFactor(1);

        // create the engine
        final Engine engine = new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(mCameraWidth, mCameraHeight), mZoomCamera));

        // get the game options
        Registry.sGameOptions = (GameOptions) getIntent().getSerializableExtra(GameOptions.sItentKey);

        // load the game instance based off the current game mode
        Registry.sGame = Game.gameModeToGame(Registry.sGameOptions.getMode());

        // call the onload engine handler
        Registry.sGame.onLoadEngine(engine);

        // check for multi touch support
        try {
            if (MultiTouch.isSupported(this)) {
                engine.setTouchController(new MultiTouchController());
            } else {
                Toast.makeText(this, "Sorry your device does NOT support MultiTouch!\n\n(No PinchZoom is possible!)", Toast.LENGTH_LONG).show();
            }
        } catch (final MultiTouchException e) {
            Toast.makeText(this, "Sorry your Android Version does NOT support MultiTouch!\n\n(No PinchZoom is possible!)", Toast.LENGTH_LONG).show();
        }

        // return the engine
        return engine;
    }

    @Override
    public void onLoadResources() {

        // fonts
        final BitmapTextureAtlas fontWhite8Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite8 = new Font(fontWhite8Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 8, true, Color.WHITE);

        final BitmapTextureAtlas fontWhite12Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite12 = new Font(fontWhite12Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 12, true, Color.WHITE);

        final BitmapTextureAtlas fontWhite16Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite16 = new Font(fontWhite16Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 16, true, Color.WHITE);

        final BitmapTextureAtlas fontWhite24Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite24 = new Font(fontWhite24Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 24, true, Color.WHITE);

        final BitmapTextureAtlas fontWhite32Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite32 = new Font(fontWhite32Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32, true, Color.WHITE);

        final BitmapTextureAtlas fontWhite48Texture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        Registry.sFontWhite48 = new Font(fontWhite48Texture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 48, true, Color.WHITE);

        // menu
        //final BitmapTextureAtlas gameMenuBgTexture = new BitmapTextureAtlas(512, 512, TextureOptions.DEFAULT);
        //Registry.sGameMenuBgTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameMenuBgTexture, this, "sprites/uimenu.png", 0, 0);

        // tower upgrader
        final BitmapTextureAtlas towerUpgraderBorderTexture = new BitmapTextureAtlas(512, 512, TextureOptions.DEFAULT);
        Registry.sTowerUpgraderBorderTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(towerUpgraderBorderTexture, this, "gfx/tower_upgrader_bg.png", 0, 0);

        // Create the effects textures
        Registry.sSpriteTextures = new BitmapTextureAtlas[SpriteSheets.values().length];
        Registry.sSpriteTextureRegions = new TiledTextureRegion[SpriteSheets.values().length];

        // load the effects sprite sheets
        for(final SpriteSheets spriteSheet : SpriteSheets.values()) {
            final int ordinal = spriteSheet.ordinal();

            Registry.sSpriteTextures[ordinal] = new BitmapTextureAtlas(spriteSheet.getWidth(), spriteSheet.getHeight(), TextureOptions.DEFAULT);
            Registry.sSpriteTextureRegions[ordinal] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                Registry.sSpriteTextures[ordinal],
                this,
                "sprites/" + spriteSheet.getSheet(),
                0,
                0,
                spriteSheet.getCols(),
                spriteSheet.getRows()
            );
        }

        // Create the creeps textures
        //Registry.sCreepBodyPool = new CreepBodyPool[CreepSpriteSheets.values().length];
        Registry.sCreepsTextures = new BitmapTextureAtlas[CreepSpriteSheets.values().length];
        Registry.sCreepsTextureRegions = new TiledTextureRegion[CreepSpriteSheets.values().length];

        // load the creep sprite sheets
        for(final CreepSpriteSheets creepSheet : CreepSpriteSheets.values()) {
            final int ordinal = creepSheet.ordinal();

            Registry.sCreepsTextures[ordinal] = new BitmapTextureAtlas(creepSheet.getWidth(), creepSheet.getHeight(), TextureOptions.DEFAULT);
            Registry.sCreepsTextureRegions[ordinal] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                Registry.sCreepsTextures[ordinal],
                this,
                "sprites/" + creepSheet.getSheet(),
                0,
                0,
                creepSheet.getCols(),
                creepSheet.getRows()
            );
        }

        // Create the effects textures
        Registry.sEffectsTextures = new BitmapTextureAtlas[EffectSpriteSheets.values().length];
        Registry.sEffectsTextureRegions = new TiledTextureRegion[EffectSpriteSheets.values().length];

        // load the effects sprite sheets
        for(final EffectSpriteSheets effectSheet : EffectSpriteSheets.values()) {
            final int ordinal = effectSheet.ordinal();

            Registry.sEffectsTextures[ordinal] = new BitmapTextureAtlas(effectSheet.getWidth(), effectSheet.getHeight(), TextureOptions.DEFAULT);
            Registry.sEffectsTextureRegions[ordinal] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                Registry.sEffectsTextures[ordinal],
                this,
                "sprites/" + effectSheet.getSheet(),
                0,
                0,
                effectSheet.getCols(),
                effectSheet.getRows()
            );
        }

        // Create the effects textures
        Registry.sTowerTextures = new BitmapTextureAtlas[TowerSpriteSheets.values().length];
        Registry.sTowerTextureRegions = new TiledTextureRegion[TowerSpriteSheets.values().length];

        // load the effects sprite sheets
        for(final TowerSpriteSheets spriteSheet : TowerSpriteSheets.values()) {
            final int ordinal = spriteSheet.ordinal();

            Registry.sTowerTextures[ordinal] = new BitmapTextureAtlas(spriteSheet.getWidth(), spriteSheet.getHeight(), TextureOptions.DEFAULT);
            Registry.sTowerTextureRegions[ordinal] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                Registry.sTowerTextures[ordinal],
                this,
                "sprites/" + spriteSheet.getSheet(),
                0,
                0,
                spriteSheet.getCols(),
                spriteSheet.getRows()
            );
        }

        // towers circle
        final BitmapTextureAtlas towerCircleTexture = new BitmapTextureAtlas(256, 256, TextureOptions.DEFAULT);
        Registry.sTowerCircleTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(towerCircleTexture, this, "gfx/towercircle256.png", 0, 0);                                                                                                                                         // 50

        // towers small menu graphics
        final BitmapTextureAtlas uiSmallTexture = new BitmapTextureAtlas(128, 128, TextureOptions.DEFAULT);
        Registry.sUiSmallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(uiSmallTexture, this, UiSmallSprites.PATH, 0, 0, UiSmallSprites.COLS, UiSmallSprites.ROWS);                                                                                                                                               // 96

        // towers large menu graphics
        final BitmapTextureAtlas uiLargeTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
        Registry.sUiLargeTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(uiLargeTexture, this, UiLargeSprites.PATH, 0, 0, UiLargeSprites.COLS, UiLargeSprites.ROWS);

        // load textures
        mEngine.getTextureManager().loadTextures(

            // font textures
            fontWhite8Texture,
            fontWhite12Texture,
            fontWhite16Texture,
            fontWhite24Texture,
            fontWhite32Texture,
            fontWhite48Texture,

            towerUpgraderBorderTexture,
            //gameMenuBgTexture,
            towerCircleTexture,

            uiSmallTexture,
            uiLargeTexture
        );

        // load creep textures
        for(final BitmapTextureAtlas tr : Registry.sSpriteTextures) {
            mEngine.getTextureManager().loadTexture(tr);
        }

        // load creep textures
        for(final BitmapTextureAtlas tr : Registry.sCreepsTextures) {
            mEngine.getTextureManager().loadTexture(tr);
        }

        // load effect textures
        for(final BitmapTextureAtlas tr : Registry.sEffectsTextures) {
            mEngine.getTextureManager().loadTexture(tr);
        }

        // load tower textures
        for(final BitmapTextureAtlas tr : Registry.sTowerTextures) {
            mEngine.getTextureManager().loadTexture(tr);
        }

        // load fonts
        mEngine.getFontManager().loadFonts(
            Registry.sFontWhite8,
            Registry.sFontWhite12,
            Registry.sFontWhite16,
            Registry.sFontWhite24,
            Registry.sFontWhite32,
            Registry.sFontWhite48
        );

        // call the game modes onLoadResources
        Registry.sGame.onLoadResources(mEngine);

    }

    @Override
    public Scene onLoadScene() {

        // create a global static ref to this instance
        Registry.sGameActivity = this;

        // store the app context in registry
        Registry.sContext = getApplicationContext();

        // load shared prefs
        Registry.sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Debug.i("welcome_message: " + Registry.sSharedPreferences.getString("welcome_message", "word"));

        // register fps logger
        mEngine.registerUpdateHandler(new FPSLogger() {

            @Override
            protected void onLogFPS() {
                String str = String.format("FPS: %.2f (MIN: %.0f ms | MAX: %.0f ms)",
                        mFrames / mSecondsElapsed,
                        mShortestFrame * MILLISECONDSPERSECOND,
                        mLongestFrame * MILLISECONDSPERSECOND);
                //userNotifyShort(str);
                Debug.d(str);
            }
        });

        // scene has 5 layers
        Registry.sScene = new Scene();
        Registry.sScene.attachChild(new Entity());
        Registry.sScene.attachChild(new Entity());
        Registry.sScene.attachChild(new Entity());
        Registry.sScene.attachChild(new Entity());
        Registry.sScene.attachChild(new Entity());

        // load scene entitys into registry
        Registry.sSceneLayerMap = Registry.sScene.getChild(GameLayer.LAYER_MAP);
        Registry.sSceneLayerTowers = Registry.sScene.getChild(GameLayer.LAYER_TOWERS);
        Registry.sSceneLayerCreeps = Registry.sScene.getChild(GameLayer.LAYER_CREEPS);
        Registry.sSceneLayerEffects = Registry.sScene.getChild(GameLayer.LAYER_EFFECTS);
        Registry.sSceneLayerUi = Registry.sScene.getChild(GameLayer.LAYER_UI);

        // call the game modes onLoadResources
        Registry.sGame.onLoadScene(Registry.sScene);

        // load the map
        loadMap();

        // load the players
        loadPlayers();

        // load the map
        loadManagers();

        // load background manager
        loadBackgroundManager();

        // load scrolling
        loadScrolling();

        // load the map
        loadHud();

        // load scrolling
        loadUpdateHandlers();

        return Registry.sScene;

    }

    @Override
    public void onLoadComplete() {

        // init game
        Registry.sGame.initialize();

        // initialize the map
        Registry.sMap.initialize();

        // initialize the hud
        Registry.sHud.initialize();

        // initialize the wave manager
        Registry.sWaveManager.initialize();

        // notify the user
        userNotifyShort("Press the play button once your forces are ready...");

    }

    @Override
    public void onPause() {
        super.onPause();
        Debug.i("Runner :: onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Debug.i("Runner :: onResume");
    }

    @Override
    public void onResumeGame() {
        super.onResumeGame();
        Debug.i("Runner :: onResumeGame");

        Registry.sBackgroundManager.start();
    }

    @Override
    public void onPauseGame() {
        super.onPauseGame();
        Debug.i("Runner :: onGamePaused");

        Registry.sBackgroundManager.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        Debug.i("Runner :: onStop");

        Registry.sBackgroundManager.exit();
        Registry.sPlayerStorageConnector.close();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Debug.i("Runner :: onDestroy");

        Registry.sBackgroundManager.exit();
        Registry.sPlayerStorageConnector.close();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        mEngine.stop();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public void onOptionsMenuClosed(final Menu menu) {
        if(Registry.sGame.isGameRunning()) {
            mEngine.start();
        }
    }

    @Override
    public boolean onKeyDown(final int pKeyCode, final KeyEvent pEvent) {
        if (pKeyCode == KeyEvent.KEYCODE_BACK) {
            if(Registry.sGame.isGameRunning()) {
                moveTaskToBack(true);

            } else {
                finish();

            }
            return true;

        } else {
            return super.onKeyDown(pKeyCode, pEvent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                startActivity(new Intent(getApplicationContext(), ETDPlay.class));
                finish();
                return true;

            case R.id.home:
                finish();
                return true;

            case R.id.help:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(final ClickDetector pClickDetector, final TouchEvent pTouchEvent) {
        if(TowerManager.ENABLE_TOWER_INTERACTION) {
            final int[] tile = Registry.sMap.getTilesFromCoords((int) pTouchEvent.getX(), (int) pTouchEvent.getY());

            if(Registry.sMap.isTileBlocked(tile[0], tile[1])) {
                final Tower t = Registry.sTowerManager.getTowerFromTile(tile[0], tile[1]);

                if(null != t) {
                    mCurrentScrollMode = SCROLL_MODE_DISABLED;

                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                Registry.sTowerManager.getTowerUpgrader().show(t);
                            }
                        }
                    );
                }
            }
        }
    }

    @Override
    public void onHold(final HoldDetector pHoldDetector, final long pHoldTimeMilliseconds, final float pHoldX, final float pHoldY) {
        //Debug.i("onHold pHoldTimeMilliseconds=" + pHoldTimeMilliseconds + " pHoldX=" + pHoldX+ " pHoldY=" + pHoldY);
        if(pHoldTimeMilliseconds > 1000) {
            pHoldDetector.setFinished(true);

            if(TowerManager.ENABLE_TOWER_INTERACTION) {
                final int[] tile = Registry.sMap.getTilesFromCoords((int) pHoldX, (int) pHoldY);

                if(Registry.sMap.isTileBlocked(tile[0], tile[1])) {
                    mCurrentScrollMode = SCROLL_MODE_DISABLED;

                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                Registry.sTowerManager.getTowerInfo().show(Registry.sTowerManager.getTowerFromTile(tile[0], tile[1]));
                            }
                        }
                    );
                }
            }
        }
    }

    @Override
    public void onHoldFinished(final HoldDetector pHoldDetector, final long pHoldTimeMilliseconds, final float pHoldX, final float pHoldY) {
        /*
        if(pHoldTimeMilliseconds < 10000) {
            Debug.i("onholdfinished pHoldTimeMilliseconds=" + pHoldTimeMilliseconds + " pHoldX=" + pHoldX+ " pHoldY=" + pHoldY);
            if(TowerManager.ENABLE_TOWER_INTERACTION) {
                final int[] tile = Registry.sMap.getTilesFromCoords((int) pHoldX, (int) pHoldY);

                if(Registry.sMap.isTileBlocked(tile[0], tile[1])) {
                    mCurrentScrollMode = SCROLL_MODE_DISABLED;

                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                Registry.sTowerManager.getTowerInfo().show(Registry.sTowerManager.getTowerFromTile(tile[0], tile[1]));
                            }
                        }
                    );
                }
            }
        }
        */
    }

    @Override
    public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
        //Debug.i("Runner :: onSceneTouchEvent :: csm=" + mCurrentScrollMode + " pstA=" + pSceneTouchEvent.getAction() + " pstX=" +  pSceneTouchEvent.getX() + " pstY=" + pSceneTouchEvent.getY() + "");
        //Debug.i("gameact ontouch mCurrentScrollMode=" + mCurrentScrollMode);

        try {
            switch (mCurrentScrollMode) {

                default:
                case SCROLL_MODE_DISABLED: {
                    Debug.i("WOULDA CLOSED");
                    /*
                    Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                            @Override
                            public void run() {
                                Registry.sTowerManager.getTowerUpgrader().hide();
                            }
                        }
                    );
                    */
                    return false;
                }

                case SCROLL_MODE_CAMERA: {
                    if (mPinchZoomDetector != null) {
                        mPinchZoomDetector.onTouchEvent(pSceneTouchEvent);

                        if (mPinchZoomDetector.isZooming()) {
                            mScrollDetector.setEnabled(false);

                        } else {
                            mScrollDetector.setEnabled(true);

                            /*
                            if (pSceneTouchEvent.isActionDown()) {

                                if(mScrollDetector.isEnabled()) {
                                    mScrollStartX = pSceneTouchEvent.getX();
                                    mScrollStartY = pSceneTouchEvent.getY();
                                }

                                mScrollDetector.setEnabled(true);

                            } else if(pSceneTouchEvent.isActionUp()) {
                                final float distanceX = pSceneTouchEvent.getX() - mScrollStartX;
                                final float distanceY = pSceneTouchEvent.getY() - mScrollStartY;
                                final boolean allowUpgradeTrigger = (Math.abs(distanceX) > 10f || Math.abs(distanceY) > 10f);

                                if(!allowUpgradeTrigger && TowerManager.ENABLE_UPGRADES) {
                                    final int[] tile = Registry.sMap.getTilesFromCoords((int) pSceneTouchEvent.getX(), (int) pSceneTouchEvent.getY());

                                    if(Registry.sMap.isTileBlocked(tile[0], tile[1])) {
                                        mCurrentScrollMode = SCROLL_MODE_DISABLED;

                                        Registry.sGameActivity.runOnUpdateThread(new Runnable() {

                                                @Override
                                                public void run() {
                                                    Registry.sTowerManager.getTowerUpgrader().show(Registry.sTowerManager.getTowerFromTile(tile[0], tile[1]));
                                                }
                                            }
                                        );

                                        return false;
                                    }
                                }
                            }
                            */

                            /*
                            if(mClickDetector.onTouchEvent(pSceneTouchEvent) && mHoldDetector.onTouchEvent(pSceneTouchEvent)) {
                                mScrollDetector.onTouchEvent(pSceneTouchEvent);
                            }
                            */

                            mClickDetector.onTouchEvent(pSceneTouchEvent);
                            mHoldDetector.onTouchEvent(pSceneTouchEvent);
                            mScrollDetector.onTouchEvent(pSceneTouchEvent);

                        }
                    } else {
                        mScrollDetector.onTouchEvent(pSceneTouchEvent);
                    }
                }
                break;

                case SCROLL_MODE_TOWER: {
                    //Debug.i("Runner :: onSceneTouchEvent :: .getAction()=" + pSceneTouchEvent.getAction() + " getX()=" + pSceneTouchEvent.getX() + " getY()=" + pSceneTouchEvent.getY() + "");

                    if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {

                        // do this on update thread so we aren't iterating towers and then
                        // try to add this to the mTowers array list
                        runOnUpdateThread(new Runnable() {

                                @Override
                                public void run() {

                                    try {
                                        Registry.sTowerManager.endTowerPlacement();
                                    } catch(Exception e) {
                                        Debug.e(e);
                                    }
                                }
                            }
                        );

                        return false;
                    }

                    if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_MOVE) {
                        //Debug.i("GameActivity -> setUpdateCoords");
                        Registry.sTowerManager.getTowerPlacer().setUpdateCoords(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
                    }
                }
                break;
            }
        } catch (Exception e) {
            Debug.e(e);
        }

        return true;

    }

    @Override
    public void onScroll(final ScrollDetector pScollDetector, final TouchEvent pTouchEvent, final float pDistanceX, final float pDistanceY) {
        final float zoomFactor = mZoomCamera.getZoomFactor();
        mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
    }

    @Override
    public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent) {
        mPinchZoomStartedCameraZoomFactor = mZoomCamera.getZoomFactor();
    }

    @Override
    public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
        mZoomCamera.setZoomFactor(getNewZoomFactor(pZoomFactor));
    }

    @Override
    public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
        mZoomCamera.setZoomFactor(getNewZoomFactor(pZoomFactor));
    }

    private float getNewZoomFactor(final float pZoomFactor) {
        final float newZoomFactor = mPinchZoomStartedCameraZoomFactor * pZoomFactor;

        if (newZoomFactor < 0.75) {
            return (float) 0.75;

        } else if (newZoomFactor > 1.25) {
            return (float) 1.25;

        } else {
            return newZoomFactor;

        }
    }

    public void loadManagers() {

        /*
        // load the wave manager
        Registry.sWaveManager = Registry.sLevel.getWaveManager();

        // check if we need to change the game mode in wave manager
        if (Registry.sGameOptions.getMode() == GameTypes.SURVIVAL) {
            Registry.sWaveManager.setSurvivalMode(true);
        }
        */

        // load the creep manager
        Registry.sCreepManager = new CreepManager();

        // load the creep floating text manager
        Registry.sCreepFloatingTextManager = new CreepFloatingTextManager();

        // create the tower manager
        Registry.sTowerManager = new TowerManager(Registry.sTowerTextureRegions, Registry.sUiLargeTextureRegion, Registry.sTowerCircleTextureRegion);

        // load the spell manager
        Registry.sSpellManager = new SpellManager();

        // load the effect manager
        Registry.sEffectManager = new EffectManager();

    }

    public void loadBackgroundManager() {
        Registry.sBackgroundManager = new BackgroundManager();
        Registry.sBackgroundManager.addTask(new BackgroundManagerIntervalTask(10f) {

            @Override
            public void onTaskDue(final float pSecondsElapsed) {
                    Registry.sPlayerStatistics.addStatistic(PlayerStatisticTypes.TOTAL_SECONDS_PLAYED, (int) Math.floor(pSecondsElapsed));

                    Registry.sPlayerStatistics.save();
                    Registry.sPlayerAchievements.save();
                    Registry.sPlayerData.save();
                    Registry.sPlayerRewards.save();
                }
            }
        );

        Registry.sBackgroundManager.start();
    }

    public void loadMap() {

        // load the map
        Registry.sMap = new Map(Registry.sGameOptions.getMapType(), mEngine.getTextureManager());

    }

    /*
    public void loadLevel() {

        // load the level manager
        Registry.sLevelBuilder = new LevelBuilder();

        // load the level we are currently playing
        //@TODO
        Registry.sLevel = Registry.sLevelBuilder.getLevelByIndex(0);

    }
    */

    public void loadPlayers() {

        // load the player storage
        Registry.sPlayerStorageConnector = new PlayerStorageConnector(this);

        // create the player rewards system
        Registry.sPlayerRewards = new PlayerRewards();

        // create the player data system
        Registry.sPlayerData = new PlayerData();

        // create the player statistics system
        Registry.sPlayerStatistics = new PlayerStatistics();

        // create the player achievements system
        Registry.sPlayerAchievements = new PlayerAchievements();

        // create the player interaction layer
        Registry.sPlayerInteracter = new PlayerInteracter(Registry.sLocalPlayer);

        // synchronize
        Registry.sPlayerRewards.sync();
        Registry.sPlayerData.sync();
        Registry.sPlayerStatistics.sync();
        Registry.sPlayerAchievements.sync();

        // create the player
        Registry.sLocalPlayer = new LocalPlayer(Registry.sGameOptions.getDifficulty());

    }

    public void loadHud() {

        // create the user interface
        Registry.sHud = new Hud();

    }

    public void loadScrolling() {

        final float cameraBoundaryX = Registry.sMap.getWidth();
        final float cameraBoundaryY = Registry.sMap.getHeight() + 128 + 40;

        // Make the camera not exceed the bounds of the TMXEntity.
        mZoomCamera.setBounds(0, cameraBoundaryX, -40, cameraBoundaryY);
        mZoomCamera.setBoundsEnabled(true);

        // setup surface scroll
        mScrollDetector = new SurfaceScrollDetector(this);
        mScrollDetector.setEnabled(true);

        // click detector
        mClickDetector = new ClickDetector(750, this);
        mClickDetector.setEnabled(true);

        // hold detector
        mHoldDetector = new HoldDetector(751, 20, .1f, this);
        mHoldDetector.setEnabled(true);

        // register scrol/hold detectors
        Registry.sScene.setOnSceneTouchListener(this);
        Registry.sScene.registerUpdateHandler(mHoldDetector);

        // allow touch area binding
        Registry.sScene.setTouchAreaBindingEnabled(true);

        // zoom scroll
        if (MultiTouch.isSupportedByAndroidVersion()) {
            try {
                mPinchZoomDetector = new PinchZoomDetector(this);
            } catch (final MultiTouchException e) {
                mPinchZoomDetector = null;
            }
        } else {
            mPinchZoomDetector = null;
        }

        // hack for zoom/scroll issues
        Registry.sScene.registerTouchArea(null);
        Registry.sScene.clearTouchAreas();

    }

    public void loadUpdateHandlers() {

        // register some update handlers
        mEngine.registerUpdateHandler(new IUpdateHandler() {

            @Override
            public void onUpdate(float pSecondsElapsed) {
                pSecondsElapsed *= Registry.sFloatFastForward;

                try {

                    // this check is so updaters dont run while the menu scene is active, probably is a better check here
                    if(!Registry.sScene.hasChildScene()) {
                        Registry.sGame.onUpdate(pSecondsElapsed);
                        Registry.sWaveManager.onUpdate(pSecondsElapsed);
                        Registry.sCreepManager.onUpdate(pSecondsElapsed);
                        Registry.sCreepFloatingTextManager.onUpdate(pSecondsElapsed);
                        Registry.sTowerManager.onUpdate(pSecondsElapsed);
                        Registry.sSpellManager.onUpdate(pSecondsElapsed);

                        // @TODO fix this, this shouldn't be each render
                        Registry.sScene.sortChildren();
                        Registry.sSceneLayerMap.sortChildren();
                        Registry.sSceneLayerTowers.sortChildren();
                        Registry.sSceneLayerCreeps.sortChildren();
                        Registry.sSceneLayerEffects.sortChildren();
                        Registry.sSceneLayerUi.sortChildren();
                    }

                } catch(Exception e) {
                    Debug.e(e);
                }
            }

            @Override
            public void reset() {
            }
        });
    }

    public void userNotifyLong(final String str) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(Registry.sContext, str, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void userNotifyShort(final String str) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(Registry.sContext, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setScrollMode(int pScrollMode) {
        mCurrentScrollMode = pScrollMode;
    }

    public int getScrollMode() {
        return mCurrentScrollMode;
    }

    public int getCameraWidth() {
        return mCameraWidth;
    }

    public int getCameraHeight() {
        return mCameraHeight;
    }

    public ZoomCamera getCamera() {
        return mZoomCamera;
    }
}