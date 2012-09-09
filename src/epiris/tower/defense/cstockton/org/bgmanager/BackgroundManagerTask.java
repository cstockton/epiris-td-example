package epiris.tower.defense.cstockton.org.bgmanager;


abstract public class BackgroundManagerTask implements IBackgroundManagerTask {

    @Override
    public void onManagerUpdate(final float pSecondsElapsed) {
        onTaskDue(pSecondsElapsed);
    }
}