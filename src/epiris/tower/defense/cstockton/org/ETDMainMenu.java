package epiris.tower.defense.cstockton.org;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.EntityBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.config.MainMenuSpriteSheets;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;
import epiris.tower.defense.cstockton.org.ui.MainMenuBuilder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

public class ETDMainMenu extends BaseGameActivity
{

    // camera
    public static int sCameraWidth = 720;
    public static int sCameraHeight = 480;

    // textures
    public static TextureRegion sBackgroundTextureRegion;
    public static TextureRegion sButtonBackgroundTextureRegion;

    public static TiledTextureRegion[] sSpriteTextureRegions;

    // fonts
    public static Font sFont32;

    // create a global static ref to this instance
    public static ETDMainMenu sETDMainMenu;
    public static Context sContext;

    // camera stuff
    private BoundCamera mBoundCamera;
    private Scene mScene;

    // main menu helpers
    final MainMenuBuilder mMainMenuBuilder = new MainMenuBuilder();

    @Override
    public Engine onLoadEngine() {

        // @TODO make dynamic
        // set camera width/height
        sCameraWidth = 720;
        sCameraHeight = 480;

        // use a bound camera
        mBoundCamera = new BoundCamera(0, 0, sCameraWidth, sCameraHeight);

        // return the engine
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(sCameraWidth, sCameraHeight), mBoundCamera));
    }

    @Override
    public void onLoadResources()
    {

        // load fonts first
        FontFactory.setAssetBasePath("font/");
        final BitmapTextureAtlas fontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        //mFont32 = FontFactory.createFromAsset(fontTexture, this, "Ruritania.ttf", 32, true, Color.WHITE);
        sFont32 = FontFactory.createFromAsset(fontTexture, this, "OldeEnglish.ttf", 32, true, Color.WHITE);

        // background
        final BitmapTextureAtlas backgroundTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
        sBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(backgroundTexture, this, "gfx/main_menu_background.png", 0, 0); // 854x480

        // buttons bg
        final BitmapTextureAtlas buttonsTexture = new BitmapTextureAtlas(256, 256, TextureOptions.DEFAULT);
        sButtonBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonsTexture, this, "gfx/main_menu_button_bg.png", 0, 0); // createFromSource(mButtonsTexture, pTextureAtlasSource, pTextureRegionBufferManaged) createFromAsset(mButtonsTexture, this, "gfx/gamemenu_bg.png", 0, 0); // 200x50

        // sprite sheets

        // sprites
        final BitmapTextureAtlas[] spriteTextures = new BitmapTextureAtlas[MainMenuSpriteSheets.values().length];
        sSpriteTextureRegions = new TiledTextureRegion[MainMenuSpriteSheets.values().length];

        // load the effects sprite sheets
        for(final MainMenuSpriteSheets spriteSheet : MainMenuSpriteSheets.values()) {
            final int ordinal = spriteSheet.ordinal();

            spriteTextures[ordinal] = new BitmapTextureAtlas(spriteSheet.getWidth(), spriteSheet.getHeight(), TextureOptions.DEFAULT);
            sSpriteTextureRegions[ordinal] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(
                spriteTextures[ordinal],
                this,
                "sprites/" + spriteSheet.getSheet(),
                0,
                0,
                spriteSheet.getCols(),
                spriteSheet.getRows()
            );
        }

        // load textures
        mEngine.getTextureManager().loadTextures(

            // fonts
            fontTexture,

            // images
            backgroundTexture,

            // sprites
            buttonsTexture

        );

        // load textures
        for(final BitmapTextureAtlas tr : spriteTextures) {
            mEngine.getTextureManager().loadTexture(tr);
        }

        // load fonts
        mEngine.getFontManager().loadFonts(sFont32);

    }

    @Override
    public Scene onLoadScene()
    {

        // add refs
        sETDMainMenu = this;
        sContext = getApplicationContext();

        // scene has 1 layer
        mScene = new Scene();
        mScene.attachChild(new Entity());

        // Make the camera not exceed the bounds of the TMXEntity.
        mBoundCamera.setBounds(0, sCameraWidth, 0, sCameraHeight);
        mBoundCamera.setBoundsEnabled(true);

        // allow touch area binding
        mScene.setTouchAreaBindingEnabled(true);

        // set the background
        mScene.setBackground(new EntityBackground(new Sprite(0, 0, sCameraWidth, sCameraHeight, sBackgroundTextureRegion) {

            @Override
            protected void onInitDraw(final GL10 pGL)
            {
               super.onInitDraw(pGL);
               GLHelper.enableTextures(pGL);
               GLHelper.enableTexCoordArray(pGL);
               GLHelper.enableDither(pGL);
            }
        }));

        return mScene;

    }

    @Override
    public void onLoadComplete() {

        ETDMainMenu.sETDMainMenu.runOnUpdateThread(new Runnable() {

            @Override
            public void run() {

                // create the main menu builder
                mMainMenuBuilder.buildMainMenu(mScene);

            }
        });

    }
}