package epiris.tower.defense.cstockton.org.ui;

import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.entity.Entity;
import org.anddev.andengine.util.HorizontalAlign;

import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.text.AlignedText;


public class GameOver extends HUD {
    public final int mCameraWidth;
    public final int mCameraHeight;

    public GameOver() {
        super();

        // camera dimensions
        mCameraWidth = Registry.sGameActivity.getCameraWidth();
        mCameraHeight = Registry.sGameActivity.getCameraHeight();

        // create a base entity
        attachChild(new Entity());

    }

    public void hide() {
        Registry.sGameActivity.getCamera().setHUD(null);
        setIgnoreUpdate(true);
    }

    public void showWin() {

    }

    public void showLose() {
        final AlignedText gameOverText = new AlignedText((mCameraWidth / 2), (mCameraHeight / 2) - 50, Registry.sFontWhite48, "Game Over", HorizontalAlign.CENTER, "Game Over".length());
        gameOverText.setColor(.8f, 0f, 0f);
        gameOverText.setText("Game Over");

        final String desc = "Press the Menu or Back button to exit";
        final AlignedText gameOverTextDesc = new AlignedText((mCameraWidth / 2), (mCameraHeight / 2), Registry.sFontWhite24, desc, HorizontalAlign.CENTER, desc.length());
        gameOverTextDesc.setColor(.8f, 0f, 0f);
        gameOverTextDesc.setText(desc);

        getLastChild().attachChild(gameOverText);
        getLastChild().attachChild(gameOverTextDesc);

        Registry.sGameActivity.getCamera().setHUD(GameOver.this);

        setIgnoreUpdate(false);
    }
}