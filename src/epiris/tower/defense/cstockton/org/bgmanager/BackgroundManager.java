package epiris.tower.defense.cstockton.org.bgmanager;

import java.util.ArrayList;
import org.anddev.andengine.util.Debug;


public class BackgroundManager {
    private final BackgroundManagerThread mBackgroundManagerThread = new BackgroundManagerThread();
    private final ArrayList<IBackgroundManagerTask> mIBackgroundManagerTasks = new ArrayList<IBackgroundManagerTask>();

    private long mLastTick = -1;
    private boolean mRunning = false;
    private boolean mExiting = false;

    public BackgroundManager() {
        mBackgroundManagerThread.start();
    }

    public void start() {
        if(!mRunning) {
            mLastTick = System.nanoTime();
            mRunning = true;
        }
    }

    public void stop() {
        mRunning = false;
    }

    public void exit() {
        mExiting = true;
    }

    public void reset() {
        this.mIBackgroundManagerTasks.clear();
    }

    /*synchronized*/ public void addTask(final IBackgroundManagerTask pIBackgroundManagerTask) {
        this.mIBackgroundManagerTasks.add(pIBackgroundManagerTask);
    }

    /*synchronized*/ protected void onUpdate(final long pNanosecondsElapsed) throws InterruptedException {
        final float pSecondsElapsed = (float)pNanosecondsElapsed / 1000000000;

        final ArrayList<IBackgroundManagerTask> tasks = mIBackgroundManagerTasks;
        final int runnableCount = tasks.size();

        for(int i = runnableCount - 1; i >= 0; i--) {
            tasks.get(i).onManagerUpdate(pSecondsElapsed);
        }
    }

    void onTickUpdate() throws InterruptedException {
        if(mRunning) {
            final long now = System.nanoTime();
            final long nanosecondsElapsed = now - mLastTick;
            mLastTick = now;

            onUpdate(nanosecondsElapsed);
        } else {
            if(mExiting) {
                throw new InterruptedException("Exit requested, throwing interrupt");
            }

            Thread.sleep(1000);
        }
    }

    private class BackgroundManagerThread extends Thread {
        public BackgroundManagerThread() {
            super("BackgroundManagerThread");
        }

        @Override
        public void run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

            try {
                while(true) {
                    BackgroundManager.this.onTickUpdate();
                }
            } catch (final InterruptedException e) {
                Debug.d("BackgroundManagerThread :: run :: background manager caught exception", e);
                this.interrupt();
            }
        }
    }
}