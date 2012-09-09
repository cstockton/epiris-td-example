package epiris.tower.defense.cstockton.org.game;

import epiris.tower.defense.cstockton.org.object.Registry;

public class GameSurvival extends Game {

    @Override
    public GameSurvival initialize() {
        return this;
    }

    @Override
    public void onManagedTick() {
        if (Registry.sLocalPlayer.getLives() < 0) {
            winGame();
        }
    }
}