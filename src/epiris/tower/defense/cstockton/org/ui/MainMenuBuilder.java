package epiris.tower.defense.cstockton.org.ui;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.HorizontalAlign;

import android.content.Intent;
import android.widget.Toast;

import epiris.tower.defense.cstockton.org.ETDAchievements;
import epiris.tower.defense.cstockton.org.ETDCredits;
import epiris.tower.defense.cstockton.org.ETDMainMenu;
import epiris.tower.defense.cstockton.org.ETDPlaySurvival;
import epiris.tower.defense.cstockton.org.ETDPreferences;
import epiris.tower.defense.cstockton.org.ETDRewards;
import epiris.tower.defense.cstockton.org.ETDStatistics;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;

public class MainMenuBuilder {

    public void buildMainMenu(final Scene pScene) {

        // clear all layers / touch areas etc
        pScene.detachChildren();
        pScene.clearEntityModifiers();
        pScene.clearTouchAreas();

        // attach a fresh layer
        pScene.attachChild(new Entity());

        int buttonIndex = 0;

        addButton("Play", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                            buildPlayMenu(pScene);
                        }
                    }
                );
            }
        });

        addButton("Barracks", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                            buildBarracksMenu(pScene);
                        }
                    }
                );
            }
        });

        addButton("Options", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDPreferences.class));
            }
        });

        addButton("Credits", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDCredits.class));
            }
        });

        addButton("Quit", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.finish();
            }
        });
    }

    public void buildBarracksMenu(final Scene pScene) {

        // clear all layers / touch areas etc
        pScene.detachChildren();
        pScene.clearEntityModifiers();
        pScene.clearTouchAreas();

        // attach a fresh layer
        pScene.attachChild(new Entity());

        int buttonIndex = 0;

        addButton("Game Points", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDRewards.class));
            }
        });

        addButton("Achievements", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDAchievements.class));
            }
        });

        addButton("Statistics", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDStatistics.class));
            }
        });

        ++buttonIndex;
        addButton("Back", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                            buildMainMenu(pScene);
                        }
                    }
                );
            }
        });
    }

    public void buildPlayMenu(final Scene pScene) {

        // clear all layers / touch areas etc
        pScene.detachChildren();
        pScene.clearEntityModifiers();
        pScene.clearTouchAreas();

        // attach a fresh layer
        pScene.attachChild(new Entity());

        int buttonIndex = 0;

        addButton("Campaign", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ETDMainMenu.sContext, "Nope, chuck testa", Toast.LENGTH_SHORT).show();
            }
        });

        addButton("Survival", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.startActivity(new Intent(ETDMainMenu.sETDMainMenu.getApplicationContext(), ETDPlaySurvival.class));
            }
        });

        ++buttonIndex;
        ++buttonIndex;

        addButton("Back", pScene, ++buttonIndex, new Runnable() {
            @Override
            public void run() {
                ETDMainMenu.sETDMainMenu.runOnUpdateThread(new Runnable() {

                        @Override
                        public void run() {
                            buildMainMenu(pScene);
                        }
                    }
                );
            }
        });
    }

    public void addButton(final String pText, final Scene pScene, final int position, final Runnable pRunnable) {
        final int cameraHeight = ETDMainMenu.sCameraHeight;
        final int cameraWidth = ETDMainMenu.sCameraWidth;

        final float alignedTextWidth = 100;
        final float alignedTextHeight = 10;

        final float positionX = cameraWidth - ETDMainMenu.sButtonBackgroundTextureRegion.getWidth() - (cameraHeight / 7);
        final float positionY = (cameraHeight / 7) * position;

        final Sprite survivalButton = new SpriteButton(positionX, positionY, ETDMainMenu.sButtonBackgroundTextureRegion.deepCopy(), pRunnable);

        pScene.getLastChild().attachChild(survivalButton);
        pScene.registerTouchArea(survivalButton);

        final AlignedText pAlignedText = new AlignedText(alignedTextWidth, alignedTextHeight, ETDMainMenu.sFont32, pText, HorizontalAlign.CENTER);
        pAlignedText.setColor(.65f, .53f, 0f);
        pAlignedText.setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        survivalButton.attachChild(pAlignedText);

    }

    class SpriteButton extends Sprite {
        final Runnable mClickRunnable;

        public SpriteButton(float pX, float pY, TextureRegion pTextureRegion, final Runnable pRunnable) {
            super(pX, pY, pTextureRegion);

            mClickRunnable = pRunnable;
        }

        @Override
        public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
            if(pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
                mClickRunnable.run();
            }
            return true;
        }
    }
}
