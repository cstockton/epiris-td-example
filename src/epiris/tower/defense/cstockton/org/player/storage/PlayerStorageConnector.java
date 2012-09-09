package epiris.tower.defense.cstockton.org.player.storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.anddev.andengine.util.Debug;

import epiris.tower.defense.cstockton.org.config.PlayerAchievementTypes;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievement;
import epiris.tower.defense.cstockton.org.player.rewards.PlayerReward;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerStorageConnector extends SQLiteOpenHelper {
    final private static String DB_NAME = "EpicaryTD";
    final private static int DB_VERSION = 1;

    final private static String ACHIEVEMENTS_TABLE = "ETD_Achievements";
    final private static String ACHIEVEMENTS_COLUMN_ID = "achievement_id";
    final private static String ACHIEVEMENTS_COLUMN_NAME = "achievement_name";
    final private static String ACHIEVEMENTS_COLUMN_DATE = "achievement_date";
    final private static String ACHIEVEMENTS_DROP = "DROP TABLE IF EXISTS " + ACHIEVEMENTS_TABLE;
    final private static String ACHIEVEMENTS_UPGRADE = ACHIEVEMENTS_DROP;
    final private static String ACHIEVEMENTS_CREATE =
        "CREATE TABLE " + ACHIEVEMENTS_TABLE + " (`"
            + ACHIEVEMENTS_COLUMN_ID + "` INTEGER PRIMARY KEY, `"
            + ACHIEVEMENTS_COLUMN_NAME + "` varchar(255) NOT NULL UNIQUE, `"
            + ACHIEVEMENTS_COLUMN_DATE + "` date)";

    final private static String STATISTICS_TABLE = "ETD_Statistics";
    final private static String STATISTICS_COLUMN_ID = "statistic_id";
    final private static String STATISTICS_COLUMN_NAME = "statistic_name";
    final private static String STATISTICS_COLUMN_VALUE = "statistic_value";
    final private static String STATISTICS_DROP = "DROP TABLE IF EXISTS " + STATISTICS_TABLE;
    final private static String STATISTICS_UPGRADE = STATISTICS_DROP;
    final private static String STATISTICS_CREATE =
        "CREATE TABLE " + STATISTICS_TABLE + " (`"
            + STATISTICS_COLUMN_ID + "` INTEGER PRIMARY KEY, `"
            + STATISTICS_COLUMN_NAME + "` varchar(255) NOT NULL UNIQUE, `"
            + STATISTICS_COLUMN_VALUE + "` INTEGER)";

    final private static String PLAYER_DATA_TABLE = "ETD_PlayerData";
    final private static String PLAYER_DATA_COLUMN_ID = "player_data_id";
    final private static String PLAYER_DATA_COLUMN_NAME = "player_data_name";
    final private static String PLAYER_DATA_COLUMN_VALUE = "player_data_value";
    final private static String PLAYER_DATA_DROP = "DROP TABLE IF EXISTS " + PLAYER_DATA_TABLE;
    final private static String PLAYER_DATA_UPGRADE = PLAYER_DATA_DROP;
    final private static String PLAYER_DATA_CREATE =
            "CREATE TABLE " + PLAYER_DATA_TABLE + " (`"
                + PLAYER_DATA_COLUMN_ID + "` INTEGER PRIMARY KEY, `"
                + PLAYER_DATA_COLUMN_NAME + "` varchar(255) NOT NULL UNIQUE, `"
                + PLAYER_DATA_COLUMN_VALUE + "` TEXT NOT NULL)";

    final private static String PLAYER_REWARDS_TABLE = "ETD_PlayerRewards";
    final private static String PLAYER_REWARDS_COLUMN_ID = "player_rewards_id";
    final private static String PLAYER_REWARDS_COLUMN_NAME = "player_rewards_name";
    final private static String PLAYER_REWARDS_COLUMN_LEVEL = "player_rewards_level";
    final private static String PLAYER_REWARDS_DROP = "DROP TABLE IF EXISTS " + PLAYER_REWARDS_TABLE;
    final private static String PLAYER_REWARDS_UPGRADE = PLAYER_REWARDS_DROP;
    final private static String PLAYER_REWARDS_CREATE =
            "CREATE TABLE " + PLAYER_REWARDS_TABLE + " (`"
                + PLAYER_REWARDS_COLUMN_ID + "` INTEGER PRIMARY KEY, `"
                + PLAYER_REWARDS_COLUMN_NAME + "` varchar(255) NOT NULL UNIQUE, `"
                + PLAYER_REWARDS_COLUMN_LEVEL + "` INTEGER)";

    private SQLiteDatabase mDatabase;

    public PlayerStorageConnector(final Context pContext) {
        super(pContext, DB_NAME, null, DB_VERSION);
    }

    public ArrayList<PlayerAchievement> selectAllAchievements() {
        openDataBase();

        final ArrayList<PlayerAchievement> achievements = new ArrayList<PlayerAchievement>();

        try {
            final Cursor cursor = mDatabase.rawQuery("select * from " + ACHIEVEMENTS_TABLE, null);

            if(cursor.moveToFirst()) {
                do {
                    achievements.add(new PlayerAchievement(cursor.getString(1), PlayerAchievementTypes.getAchievementDescriptionByTitle(cursor.getString(1)), cursor.getString(2)));
                } while(cursor.moveToNext());
            }
            if(cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch(Exception e) {
            Debug.w("PlayerAchievementsConnector :: selectAllAchievements :: error reading from the db - " + e);
        }

        return achievements;
    }

    public Map<String, Integer> selectAllStatistics() {
        openDataBase();

        final Map<String, Integer> map = new HashMap<String, Integer>();
        final Cursor cursor = mDatabase.rawQuery("select * from " + STATISTICS_TABLE, null);

        if(cursor.moveToFirst()) {
            do {
                map.put(cursor.getString(1), cursor.getInt(2));
            } while(cursor.moveToNext());
        }
        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return map;
    }

    public Map<String, String> selectAllPlayerData() {
        openDataBase();

        final Map<String, String> map = new HashMap<String, String>();
        final Cursor cursor = mDatabase.rawQuery("select * from " + PLAYER_DATA_TABLE, null);

        if(cursor.moveToFirst()) {
            do {
                map.put(cursor.getString(1), cursor.getString(2));
            } while(cursor.moveToNext());
        }
        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return map;
    }

    public ArrayList<PlayerReward> selectAllPlayerRewards() {
        openDataBase();

        final ArrayList<PlayerReward> rewards = new ArrayList<PlayerReward>();

        try {
            final Cursor cursor = mDatabase.rawQuery("select * from " + PLAYER_REWARDS_TABLE, null);

            if(cursor.moveToFirst()) {
                do {
                    rewards.add(new PlayerReward(cursor.getString(1), cursor.getInt(2)));
                } while(cursor.moveToNext());
            }
            if(cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        } catch(Exception e) {
            Debug.w("PlayerAchievementsConnector :: selectAllPlayerRewards :: error reading from the db - " + e);
        }

        return rewards;
    }

    public void insertAchievement(final String pName) {
        openDataBase();

        try {

            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final Date date = new Date();
            final ContentValues initialValues = new ContentValues();

            initialValues.put(ACHIEVEMENTS_COLUMN_NAME, pName);
            initialValues.put(ACHIEVEMENTS_COLUMN_DATE, dateFormat.format(date));
            mDatabase.insert(ACHIEVEMENTS_TABLE, null, initialValues);

        } catch(Exception e) {
            Debug.w("PlayerAchievementsConnector :: updateAchievement :: error inserting into the db - " + e);
        }
    }

    public void updateStatistic(final String pName, final int pValue) {
        //Debug.i("PlayerStatisticsConnector :: updateStatistic :: pValue=" + pValue + " Integer.toString(pValue)=" + Integer.toString(pValue));
        openDataBase();

        ContentValues cv = new ContentValues();
        cv.put(STATISTICS_COLUMN_NAME, pName);
        cv.put(STATISTICS_COLUMN_VALUE, pValue);

        try {
            final int result = mDatabase.update(STATISTICS_TABLE, cv, STATISTICS_COLUMN_NAME + " = ?", new String[] { pName });
            if(0 == result) {
                mDatabase.insert(STATISTICS_TABLE, null, cv);
            }
        } catch(Exception e) {
            Debug.i("PlayerStatisticsConnector :: updateStatistic :: error inserting into the db - " + e);
        }
    }

    public void updatePlayerData(final String pName, final String pValue) {
        //Debug.i("PlayerStatisticsConnector :: updateStatistic :: pValue=" + pValue + " Integer.toString(pValue)=" + Integer.toString(pValue));
        openDataBase();

        ContentValues cv = new ContentValues();
        cv.put(PLAYER_DATA_COLUMN_NAME, pName);
        cv.put(PLAYER_DATA_COLUMN_VALUE, pValue);

        try {
            final int result = mDatabase.update(PLAYER_DATA_TABLE, cv, PLAYER_DATA_COLUMN_NAME + " = ?", new String[] { pName });
            if(0 == result) {
                mDatabase.insert(PLAYER_DATA_TABLE, null, cv);
            }
        } catch(Exception e) {
            Debug.i("PlayerStatisticsConnector :: updatePlayerData :: error inserting into the db - " + e);
        }
    }

    public void updatePlayerReward(final String pName, final int pLevel) {
        //Debug.i("PlayerStatisticsConnector :: updateStatistic :: pValue=" + pValue + " Integer.toString(pValue)=" + Integer.toString(pValue));
        openDataBase();

        final ContentValues cv = new ContentValues();

        cv.put(PLAYER_REWARDS_COLUMN_NAME, pName);
        cv.put(PLAYER_REWARDS_COLUMN_LEVEL, pLevel);

        try {
            final int result = mDatabase.update(PLAYER_REWARDS_TABLE, cv, PLAYER_REWARDS_COLUMN_NAME + " = ?", new String[] { pName });
            if(0 == result) {
                mDatabase.insert(PLAYER_REWARDS_TABLE, null, cv);
            }
        } catch(Exception e) {
            Debug.i("PlayerStatisticsConnector :: updatePlayerReward :: error inserting into the db - " + e);
        }
    }

    public void openDataBase() throws SQLException {
        if(null == mDatabase) {
            mDatabase = getWritableDatabase();
        }
    }

    public void resetAchievements() {
        Debug.i("PlayerStatisticsConnector :: resetAchievements");
        openDataBase();

        mDatabase.execSQL(ACHIEVEMENTS_DROP);
        mDatabase.execSQL(ACHIEVEMENTS_CREATE);
    }
    public void resetStatistics() {
        Debug.i("PlayerStatisticsConnector :: resetStatistics");
        openDataBase();

        mDatabase.execSQL(STATISTICS_DROP);
        mDatabase.execSQL(STATISTICS_CREATE);
    }

    public void resetPlayerData() {
        Debug.i("PlayerStatisticsConnector :: resetPlayerData");
        openDataBase();

        mDatabase.execSQL(PLAYER_DATA_DROP);
        mDatabase.execSQL(PLAYER_DATA_CREATE);
    }

    public void resetPlayerRewards() {
        Debug.i("PlayerStatisticsConnector :: resetPlayerRewards");
        openDataBase();

        mDatabase.execSQL(PLAYER_REWARDS_DROP);
        mDatabase.execSQL(PLAYER_REWARDS_CREATE);
    }

    public void reset() {
        Debug.i("PlayerStatisticsConnector :: reset");
        openDataBase();

        resetAchievements();
        resetStatistics();
        resetPlayerData();
        resetPlayerRewards();
    }

    @Override
    public void close() {
        if(mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }

        super.close();
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Debug.i("PlayerStorageConnector :: onCreate");

        db.execSQL(ACHIEVEMENTS_CREATE);
        db.execSQL(STATISTICS_CREATE);
        db.execSQL(PLAYER_DATA_CREATE);
        db.execSQL(PLAYER_REWARDS_CREATE);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int pOldVersion, final int pNewVersion) {
        Debug.i("PlayerStorageConnector :: onUpgrade :: pOldVersion=" + pOldVersion + " pNewVersion=" + pNewVersion);

        db.execSQL(ACHIEVEMENTS_UPGRADE);
        db.execSQL(STATISTICS_UPGRADE);
        db.execSQL(PLAYER_DATA_UPGRADE);
        db.execSQL(PLAYER_REWARDS_UPGRADE);

        onCreate(db);
    }
}