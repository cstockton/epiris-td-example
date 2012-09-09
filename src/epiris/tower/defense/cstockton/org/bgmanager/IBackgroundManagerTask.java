package epiris.tower.defense.cstockton.org.bgmanager;

public interface IBackgroundManagerTask {
    public void onManagerUpdate(final float pSecondsElapsed);
    public void onTaskDue(final float pSecondsElapsed);
}