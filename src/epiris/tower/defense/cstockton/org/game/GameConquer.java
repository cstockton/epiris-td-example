package epiris.tower.defense.cstockton.org.game;

import epiris.tower.defense.cstockton.org.object.Registry;

public class GameConquer extends Game {

    @Override
    public GameConquer initialize() {
        return this;
    }

    @Override
    public void onManagedTick() {
        if (Registry.sLocalPlayer.getLives() < 0) {
            loseGame();
        }
    }
}