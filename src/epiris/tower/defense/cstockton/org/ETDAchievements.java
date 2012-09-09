package epiris.tower.defense.cstockton.org;

import java.util.ArrayList;

import org.anddev.andengine.util.Debug;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.config.PlayerAchievementTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievement;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievements;
import epiris.tower.defense.cstockton.org.player.storage.PlayerStorageConnector;
import android.content.Context;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ETDAchievements extends ListActivity {

    private ProgressDialog mProgressDialog = null;
    private ArrayList<PlayerAchievement> mAchievements = null;
    private AchievementAdapter mAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Registry.sPlayerStorageConnector.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievements);

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDAchievements.this.finish();
                }
            }
        );

        loadStats();

    }

    public void loadStats() {
        mAchievements = new ArrayList<PlayerAchievement>();
        mAdapter = new AchievementAdapter(this, R.layout.achievements_row);
        setListAdapter(mAdapter);

        final Runnable achievementsLoader = new Runnable() {

            @Override
            public void run() {
                try {
                    mAchievements = new ArrayList<PlayerAchievement>();

                    if(null == Registry.sPlayerAchievements) {
                        Registry.sPlayerStorageConnector = new PlayerStorageConnector(ETDAchievements.this.getApplicationContext());
                        Registry.sPlayerAchievements = new PlayerAchievements();
                    }
                    Registry.sPlayerAchievements.sync();

                    final ArrayList<PlayerAchievement> achievementsEarned = Registry.sPlayerAchievements.getAchievementsEarned();

                    for(PlayerAchievementTypes t : PlayerAchievementTypes.values()) {
                        boolean earned = false;
                        PlayerAchievement achievementEarned = null;

                        for(final PlayerAchievement achievement : achievementsEarned) {
                            if(achievement.getAchievementTitle().equals(t.mTitle)) {
                                earned = true;
                                achievementEarned = achievement;
                                break;
                            }
                        }

                        if(earned) {
                            mAchievements.add(new PlayerAchievement(t.mTitle, t.mDescription, achievementEarned.getAchievementDate()));
                        } else {
                            mAchievements.add(new PlayerAchievement(t.mTitle, t.mDescription, "In Progress"));
                        }
                    }
                } catch (Exception e) {
                    Debug.e("ETDAchievements :: getAchievements :: exception: " + e);
                }

                runOnUiThread(returnRes);
            }
        };

        Thread thread = new Thread(null, achievementsLoader, "ETDAchievementsLoader");
        thread.start();

        mProgressDialog = ProgressDialog.show(this, "Please wait...", "Fetching your achievements ...", true);
    }

    private final Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if (mAchievements != null && mAchievements.size() > 0) {
                mAdapter.notifyDataSetChanged();

                for (int i = 0; i < mAchievements.size(); i++) {
                    mAdapter.add(mAchievements.get(i));
                }
            }

            mProgressDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    };

    private class AchievementAdapter extends ArrayAdapter<PlayerAchievement> {
        public AchievementAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return mAchievements.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.achievements_row, null);
            }

            final PlayerAchievement o = mAchievements.get(position);
            if (o != null) {
                final TextView tt = (TextView) v.findViewById(R.id.achievements_toptext);
                final TextView bt = (TextView) v.findViewById(R.id.achievements_bottomtext);
                final ImageView iv = (ImageView) v.findViewById(R.id.icon);

                if (tt != null) {
                    tt.setText(o.getAchievementTitle() + ": ");
                }
                if (bt != null) {
                    if(o.getAchievementDate().equals("In Progress")) {
                        iv.setImageResource(R.drawable.trophy_transparent);
                    } else {
                        iv.setImageResource(R.drawable.trophy);
                    }

                    bt.setText(o.getAchievementDescription() + " (" + o.getAchievementDate() + ")");
                }
            }
            return v;
        }
    }
}