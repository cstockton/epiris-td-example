package epiris.tower.defense.cstockton.org.wave;

import java.util.LinkedList;

import epiris.tower.defense.cstockton.org.creep.CreepConfiguration;
import epiris.tower.defense.cstockton.org.map.MapSpawn;
import epiris.tower.defense.cstockton.org.map.MapSpawnPolicy;
import epiris.tower.defense.cstockton.org.object.Registry;

public class Wave {

    final private LinkedList<WaveOptions> mWaveOptions = new LinkedList<WaveOptions>();
    final private WaveManager mWaveManager;

    private int mTotalCreeps = 0;

    private int mCurrentWaveOptionsIndex = 0;
    private int mCurrentSpawnIndex = 0;
    private int mCurrentMapSpawnIndex = 0;

    private int mWaveNum = 0;

    private final MapSpawnPolicy mMapSpawnPolicy;
    private final MapSpawn[] mMapSpawns;
    private final boolean mFinished = false;
    private boolean mRepeatable = false;
    private WaveOptions mCurrentWaveOptions;

    private float mCurrentElapsedSinceUpdate = 0;

    public Wave(final WaveManager pWaveManager) {
        super();

        mWaveManager = pWaveManager;
        mMapSpawnPolicy = Registry.sMap.getMapSpawnPolicy();
        mMapSpawns = mMapSpawnPolicy.getMapSpawns();
    }

    public boolean isFinished() {
        return mFinished;
    }

    public Wave setRepeatable(final boolean pRepeatable) {
        mRepeatable = pRepeatable;
        return this;
    }

    public Wave setWaveNum(final int pWaveNum) {
        mWaveNum = pWaveNum;
        return this;
    }

    public int getWaveNum() {
        return mWaveNum;
    }

    public int getTotalCreeps() {
        return mTotalCreeps;
    }

    public boolean isRepeatable() {
        return mRepeatable;
    }

    public Wave addCreeps(final CreepConfiguration pCreepConfiguration, final float pCreepDelay, final int pCreepCount) {
        mTotalCreeps += pCreepCount;

        mWaveOptions.add(new WaveOptions(pCreepConfiguration, pCreepCount, pCreepDelay));
        return this;
    }

    public Wave release(final int pWaveNum) {

        // resent indexes on release
        mCurrentWaveOptionsIndex = 0;
        mCurrentSpawnIndex = 0;
        mCurrentMapSpawnIndex = 0;

        // prepare to release ur big one dog lol~
        // grab our current wave options
        mCurrentWaveOptions = mWaveOptions.get(mCurrentWaveOptionsIndex);

        // set the mCurrentElapsedSinceUpdate to 1000 to artificial induce the
        // first update
        mCurrentElapsedSinceUpdate = 1000;

        return this;

    }

    public WaveManager getWaveManager() {
        return mWaveManager;
    }

    public boolean onUpdate(final float pSecondsElapsed) {

        // we check to see if the current respected creep delay has passed
        if (mCurrentWaveOptions.getCreepDelay() <= mCurrentElapsedSinceUpdate) {

            // check to see if the current creep count exceeds the waves expected
            // creep count yet, if it does we increment our wave options index
            if (mCurrentSpawnIndex >= mCurrentWaveOptions.getCreepCount()) {

                // inc our wave opts ind
                mCurrentWaveOptionsIndex++;

                // check to see if this is the last portion of this wave
                if ((mWaveOptions.size() - 1) < mCurrentWaveOptionsIndex) {
                    return false;
                }

                // grab our new wave options
                mCurrentWaveOptions = mWaveOptions.get(mCurrentWaveOptionsIndex);

                // reset spawn index
                mCurrentSpawnIndex = 0;

            }

            // spawn the creep of the expected type
            Registry.sCreepManager.spawn(this, mMapSpawns[mCurrentMapSpawnIndex], mCurrentWaveOptions.getCreepConfiguration());

            // increment our current spawn index
            mCurrentSpawnIndex++;

            // increment map spawn index
            mCurrentMapSpawnIndex++;

            // for concurrent mode we set this to 1000f if our current mapspawn index is less then the count
            if(mMapSpawnPolicy.isConcurrent() && mCurrentMapSpawnIndex < mMapSpawns.length) {
                mCurrentElapsedSinceUpdate = 1000f;

            } else {

                // set our last update back to zero
                mCurrentElapsedSinceUpdate = 0;
            }

            // check our map spawn index
            if(mCurrentMapSpawnIndex >= mMapSpawns.length) {
                mCurrentMapSpawnIndex = 0;
            }

        } else {

            // increment our elapsed seconds since update
            mCurrentElapsedSinceUpdate += pSecondsElapsed;
        }

        return true;
    }

    public class WaveOptions {

        private final CreepConfiguration mCreepConfiguration;
        private final int mCreepCount;
        private final float mCreepDelay;

        public WaveOptions(final CreepConfiguration pCreepConfiguration, final int pCreepCount, final float pCreepDelay) {
            mCreepConfiguration = pCreepConfiguration;
            mCreepCount = pCreepCount;
            mCreepDelay = pCreepDelay;
        }

        /**
         * @return the mCreepConfiguration
         */
        public final CreepConfiguration getCreepConfiguration() {
            return mCreepConfiguration;
        }

        /**
         * @return the mCreepCount
         */
        public final int getCreepCount() {
            return mCreepCount;
        }

        /**
         * @return the pCreepDelay
         */
        public final float getCreepDelay() {
            return mCreepDelay;
        }
    }
}