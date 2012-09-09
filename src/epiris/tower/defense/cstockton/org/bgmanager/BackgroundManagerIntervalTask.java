package epiris.tower.defense.cstockton.org.bgmanager;

abstract public class BackgroundManagerIntervalTask extends BackgroundManagerTask {
    private final float mIntervalSeconds;
    private float mIntervalSecondsElapsed = 0f;


    public BackgroundManagerIntervalTask(final float pIntervalSeconds) {
        mIntervalSeconds = pIntervalSeconds;
    }

    @Override
    public void onManagerUpdate(final float pSecondsElapsed) {
        mIntervalSecondsElapsed += pSecondsElapsed;

        if(mIntervalSecondsElapsed > mIntervalSeconds) {
            onTaskDue(mIntervalSecondsElapsed);
            mIntervalSecondsElapsed = 0f;
        }
    }
}