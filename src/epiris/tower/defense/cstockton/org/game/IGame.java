package epiris.tower.defense.cstockton.org.game;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import epiris.tower.defense.cstockton.org.creep.Creep;
import epiris.tower.defense.cstockton.org.creep.CreepConfiguration;
import epiris.tower.defense.cstockton.org.wave.Wave;

public interface IGame {

    public IGame initialize();

    public boolean isGameRunning();

    public Game setGameRunning(final boolean pRunning);

    public void onLoadResources(final Engine pEngine);

    public void onLoadScene(final Scene pScene);

    public void onLoadEngine(final Engine pEngine);

    public void onUpdate(final float pSecondsElapsed);

    // game engine logic
    public int getCreepLife(final Wave pWave, final Creep pCreep, final CreepConfiguration pCreepConfiguration);

    // game events
    public void onCreepActivate(final Creep pCreep);

    public void onCreepLeak(final Creep pCreep);

    public void onCreepKill(final Creep pCreep);

}