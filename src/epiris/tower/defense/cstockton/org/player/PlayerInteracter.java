package epiris.tower.defense.cstockton.org.player;

import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerInteracter
{

    public final IPlayer mPlayer;

    public PlayerInteracter(IPlayer pPlayer)
    {
        super();

        this.mPlayer = pPlayer;
    }

    public boolean requestSettings()
    {

        // player wants to open the settings
        //Registry.sGameActivity.toggleMenu();
        return true;

    }

    public boolean requestStart()
    {
        Registry.sWaveManager.start();
        return true;
    }

    public boolean requestSkip()
    {
        Registry.sWaveManager.startNextWave();
        return true;
    }

    public boolean requestFastSpeed()
    {
        Registry.sFloatFastForward = 2f;
        return true;
    }

    public boolean requestRegularSpeed()
    {
        Registry.sFloatFastForward = 1f;
        return true;
    }
}