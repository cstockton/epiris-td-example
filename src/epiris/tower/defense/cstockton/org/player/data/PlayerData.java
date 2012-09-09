package epiris.tower.defense.cstockton.org.player.data;

import java.util.HashMap;
import java.util.Map;

import epiris.tower.defense.cstockton.org.object.Registry;

public class PlayerData {
    final Map<String, String> mPlayerData = new HashMap<String, String>();

    public void sync() {
        final Map<String, String> r = Registry.sPlayerStorageConnector.selectAllPlayerData();
        for(final Map.Entry<String, String> s : r.entrySet()) {
            mPlayerData.put(s.getKey(), s.getValue());
            //Debug.v("PlayerData :: sync :: loading stat " + s.getKey() + "=" + s.getValue());
        }
    }

    public void reset() {
        Registry.sPlayerStorageConnector.resetPlayerData();

        //synchronized(mPlayerData) {
            mPlayerData.clear();
        //}
    }

    public void save() {
        //synchronized(mPlayerData) {
            for(final Map.Entry<String, String> s : mPlayerData.entrySet()) {
                Registry.sPlayerStorageConnector.updatePlayerData(s.getKey(), s.getValue());
                //Debug.v("PlayerData :: save :: update stat " + s.getKey() + "=" + s.getValue());
            }
        //}
    }

    public Map<String, String> getPlayerData() {
        return mPlayerData;
    }

    public String getPlayerData(final PlayerDataTypes pPlayerDataType) {
        return getPlayerData(pPlayerDataType.mName);
    }

    public String getPlayerData(final String pPlayerDataName) {
        if(mPlayerData.containsKey(pPlayerDataName)) {
            return mPlayerData.get(pPlayerDataName);
        }

        return "";
    }

    public void setPlayerData(final PlayerDataTypes pPlayerDataType, final String pPlayerDataValue) {
        setPlayerData(pPlayerDataType.mName, pPlayerDataValue);
    }

    public void addPlayerData(final PlayerDataTypes pPlayerDataType, final String pPlayerDataValue) {
        addPlayerData(pPlayerDataType.mName, pPlayerDataValue);
    }

    public void setPlayerData(final String pPlayerDataName, final String pPlayerDataValue) {
        mPlayerData.put(pPlayerDataName, pPlayerDataValue);
    }

    public void addPlayerData(final String pPlayerDataName, final String pPlayerDataValue) {
        if(mPlayerData.containsKey(pPlayerDataName)) {
            mPlayerData.put(pPlayerDataName, mPlayerData.get(pPlayerDataName) + pPlayerDataValue);
        } else {
            setPlayerData(pPlayerDataName, pPlayerDataValue);
        }
    }
}