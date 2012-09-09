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
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.util.GLHelper;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.text.AlignedText;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

public class ETDMainMenuOld extends BaseGameActivity
{

    public static final int FONT_BLACK_SMALL = 0;
    public static final int FONT_YELLOW_SMALL = 1;
    public static final int FONT_RED_SMALL = 2;

    private int mCameraWidth;
    private int mCameraHeight;

    private BoundCamera mBoundCamera;
    private Scene mScene;

    private Font mFontTitle;
    private Font mFontSubTitle;
    private Font mFontOption;

    private TextureRegion mBackgroundTextureRegion;
    private TextureRegion mButtonBackgroundTextureRegion;

    public int mCurrentLevel = 0;

    AlignedText mChooseMapText;
    AlignedText mDifficultyLevelText;
    public int mDifficultyLevelValue = 1;

    Sprite mChooseMapArrow1Button;
    Sprite mChooseMapArrow2Button;

    Sprite mDifficultyLevelArrow1Button;
    Sprite mDifficultyLevelArrow2Button;

    @Override
    public Engine onLoadEngine() {

        // @TODO make dynamic
        // set camera width/height
        mCameraWidth = 720;
        mCameraHeight = 480;

        // use a bound camera
        mBoundCamera = new BoundCamera(0, 0, mCameraWidth, mCameraHeight);

        // return the engine
        return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(mCameraWidth, mCameraHeight), mBoundCamera));
    }

    @Override
    public void onLoadResources()
    {

        // fonts
        final BitmapTextureAtlas fontTitleTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mFontTitle = new Font(fontTitleTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 48, true, Color.RED);

        final BitmapTextureAtlas fontSubTitleTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mFontSubTitle = new Font(fontSubTitleTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 36, true, Color.RED);

        final BitmapTextureAtlas fontOptionTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        mFontOption = new Font(fontOptionTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 18, true, Color.BLACK);

        // background
        final BitmapTextureAtlas backgroundTexture = new BitmapTextureAtlas(1024, 1024, TextureOptions.DEFAULT);
        mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(backgroundTexture, this, "gfx/gamemenu_background.png", 0, 0); // 854x480

        // buttons
        final BitmapTextureAtlas buttonsTexture = new BitmapTextureAtlas(256, 256, TextureOptions.DEFAULT);
        mButtonBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonsTexture, this, "gfx/gamemenu_bg.png", 0, 0); // createFromSource(mButtonsTexture, pTextureAtlasSource, pTextureRegionBufferManaged) createFromAsset(mButtonsTexture, this, "gfx/gamemenu_bg.png", 0, 0); // 200x50

        // load textures
        mEngine.getTextureManager().loadTextures(fontTitleTexture, fontSubTitleTexture, fontOptionTexture, backgroundTexture, buttonsTexture);
        mEngine.getFontManager().loadFonts(mFontTitle, mFontSubTitle, mFontOption);

    }

    @Override
    public Scene onLoadScene()
    {

        // scene has 1 layer
        mScene = new Scene();
        mScene.attachChild(new Entity());

        // Make the camera not exceed the bounds of the TMXEntity.
        mBoundCamera.setBounds(0, mCameraWidth, 0, mCameraHeight);
        mBoundCamera.setBoundsEnabled(true);

        // allow touch area binding
        mScene.setTouchAreaBindingEnabled(true);

        // set the background
        mScene.setBackground(new EntityBackground(new Sprite(0, 0, mCameraWidth, mCameraHeight, mBackgroundTextureRegion) {

            @Override
            protected void onInitDraw(final GL10 pGL)
            {
               super.onInitDraw(pGL);
               GLHelper.enableTextures(pGL);
               GLHelper.enableTexCoordArray(pGL);
               GLHelper.enableDither(pGL);
            }
        }));

        /*
        final AlignedText chooseMap = new AlignedText((mCameraWidth / 2), (mCameraHeight / 8) + 115, mFontOption, "Choose your play mode", HorizontalAlign.CENTER, "Choose your play mode".length());
        mScene.getLastChild().attachChild(chooseMap);
        */

        final float optionsHeight = (mCameraHeight / 8) * 3;
        final float alignedTextWidth = 100;
        final float alignedTextHeight = 10;

        final Sprite survivalButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) - 110, optionsHeight, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    //startActivity(new Intent(ETDMainMenu.this.getApplicationContext(), ETDSurvivalLevelSelect.class));
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDPlay.class));
                }
                return true;
            }
        };
        mScene.getLastChild().attachChild(survivalButton);
        mScene.registerTouchArea(survivalButton);
        survivalButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Play", HorizontalAlign.CENTER));

        Sprite gpButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) + 110, optionsHeight, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDRewards.class));
                }
                return true;
            }
        };
        mScene.getLastChild().attachChild(gpButton);
        mScene.registerTouchArea(gpButton);
        gpButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Game Points", HorizontalAlign.CENTER));

        Sprite achievementsButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) - 110, optionsHeight + 60, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDAchievements.class));
                }

                return true;
            }
        };
        mScene.getLastChild().attachChild(achievementsButton);
        mScene.registerTouchArea(achievementsButton);
        achievementsButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Achievements", HorizontalAlign.CENTER));

        Sprite statisticsButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) + 110, optionsHeight + 60, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDStatistics.class));
                }

                return true;
            }
        };
        mScene.getLastChild().attachChild(statisticsButton);
        mScene.registerTouchArea(statisticsButton);
        statisticsButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Statistics", HorizontalAlign.CENTER));

        Sprite optionsButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) - 110, optionsHeight + 120, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDPreferences.class));
                }

                return true;
            }
        };
        mScene.getLastChild().attachChild(optionsButton);
        mScene.registerTouchArea(optionsButton);
        optionsButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Options", HorizontalAlign.CENTER));

        Sprite creditsButton = new Sprite((mCameraWidth / 2) - (mButtonBackgroundTextureRegion.getWidth() / 2) + 110, optionsHeight + 120, mButtonBackgroundTextureRegion.deepCopy()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                    startActivity(new Intent(ETDMainMenuOld.this.getApplicationContext(), ETDCredits.class));
                }

                return true;
            }
        };
        mScene.getLastChild().attachChild(creditsButton);
        mScene.registerTouchArea(creditsButton);
        creditsButton.attachChild(new AlignedText(alignedTextWidth, alignedTextHeight, mFontOption, "Credits", HorizontalAlign.CENTER));


        return mScene;
    }

    @Override
    public void onLoadComplete() {

        /*
        Intent intent = new Intent(getApplicationContext(), Runner.class);
        intent.putExtra("gameMode", GameModes.SURVIVAL.getId());
        intent.putExtra("levelIndex", 0);
        intent.putExtra("difficultyLevel", 10);
        startActivity(intent);
        */

    }
}