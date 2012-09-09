package epiris.tower.defense.cstockton.org.player.statistics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import epiris.tower.defense.cstockton.org.config.DamageTypes;
import epiris.tower.defense.cstockton.org.config.PlayerStatisticTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.util.Debug;

public class PlayerStatistics {
    final Map<String, Integer> mStatistics = new ConcurrentHashMap<String, Integer>();

    public void sync() {
        final Map<String, Integer> r = Registry.sPlayerStorageConnector.selectAllStatistics();
        for(final Map.Entry<String, Integer> s : r.entrySet()) {
            mStatistics.put(s.getKey(), s.getValue());
            //Debug.v("PlayerStatistics :: assignStatistic :: loading stat " + s.getKey() + "=" + s.getValue());
        }
    }

    public void reset() {
        Registry.sPlayerStorageConnector.resetStatistics();

        //synchronized(mStatistics) {
            mStatistics.clear();
        //}
    }

    public void save() {
        //synchronized(mStatistics) {
            for(final Map.Entry<String, Integer> s : mStatistics.entrySet()) {
                Registry.sPlayerStorageConnector.updateStatistic(s.getKey(), s.getValue());
                //Debug.v("PlayerStatistics :: save :: update stat " + s.getKey() + "=" + s.getValue());
            }
        //}
    }

    public Map<String, Integer> getStatistics() {
        //synchronized(mStatistics) {
            return mStatistics;
        //}
    }

    public int getStatistic(final String pStatisticName) {
        if(mStatistics.containsKey(pStatisticName)) {
            return mStatistics.get(pStatisticName);
        }

        return -1;
    }

    public String getStatisticDescription(final String pStatisticName) {

        // first look for the stat in stat types
        for(final PlayerStatisticTypes t : PlayerStatisticTypes.values()) {
            if(t.mName.equals(pStatisticName)) {
                return t.mDescription;
            }
        }

        // also check damage types
        for(final DamageTypes t : DamageTypes.values()) {
            if(t.mName.equals(pStatisticName)) {
                return t.mDescription;
            }
        }

        Debug.w("PlayerStatistics :: getStatisticDescription :: unable to find description for statistic name: " + pStatisticName);

        return pStatisticName;
    }

    public void setStatistic(final PlayerStatisticTypes pStatisticType, final int pStatisticValue) {
        setStatistic(pStatisticType.mName, pStatisticValue);
    }

    public void addStatistic(final PlayerStatisticTypes pStatisticType, final int pStatisticValue) {
        addStatistic(pStatisticType.mName, pStatisticValue);
    }

    public void setStatistic(final String pStatisticName, final int pStatisticValue) {
        //synchronized(mStatistics) {
            mStatistics.put(pStatisticName, pStatisticValue);
        //}
    }

    public void addStatistic(final String pStatisticName, final int pStatisticValue) {
        //synchronized(mStatistics) {
            if(mStatistics.containsKey(pStatisticName)) {
                mStatistics.put(pStatisticName, mStatistics.get(pStatisticName) + pStatisticValue);
            } else {
                setStatistic(pStatisticName, pStatisticValue);
            }
        //}
    }
}