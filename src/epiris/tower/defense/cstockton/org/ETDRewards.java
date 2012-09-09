package epiris.tower.defense.cstockton.org;

import java.text.MessageFormat;
import java.util.ArrayList;

import org.anddev.andengine.util.Debug;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.config.PlayerRewardTypes;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.achievements.PlayerAchievements;
import epiris.tower.defense.cstockton.org.player.data.PlayerData;
import epiris.tower.defense.cstockton.org.player.data.PlayerDataTypes;
import epiris.tower.defense.cstockton.org.player.rewards.IPlayerRewardPrerequisite;
import epiris.tower.defense.cstockton.org.player.rewards.PlayerReward;
import epiris.tower.defense.cstockton.org.player.rewards.PlayerRewards;
import epiris.tower.defense.cstockton.org.player.statistics.PlayerStatistics;
import epiris.tower.defense.cstockton.org.player.storage.PlayerStorageConnector;
import epiris.tower.defense.cstockton.org.util.Util;
import android.content.Context;
import android.graphics.Color;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ETDRewards extends ListActivity {

    private ProgressDialog mProgressDialog = null;
    private ArrayList<PlayerReward> mRewards = null;
    private RewardAdapter mAdapter;
    private TextView mGpTextView;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Registry.sPlayerStorageConnector.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);

        Registry.sPlayerStorageConnector = new PlayerStorageConnector(ETDRewards.this.getApplicationContext());
        Registry.sPlayerData = new PlayerData();
        Registry.sPlayerRewards = new PlayerRewards();
        Registry.sPlayerStatistics = new PlayerStatistics();
        Registry.sPlayerAchievements = new PlayerAchievements();

        Registry.sPlayerData.sync();
        Registry.sPlayerRewards.sync();
        Registry.sPlayerStatistics.sync();
        Registry.sPlayerAchievements.sync();

        mGpTextView = (TextView) findViewById(R.id.rewards_gp);
        mGpTextView.setText("GP " + Util.getIntFromString(Registry.sPlayerData.getPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS), 0));

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDRewards.this.finish();
                }
            }
        );

        loadStats();

    }

    public void loadStats() {

        mRewards = new ArrayList<PlayerReward>();
        mAdapter = new RewardAdapter(this, R.layout.rewards_row);
        setListAdapter(mAdapter);

        final Runnable rewardsLoader = new Runnable() {

            @Override
            public void run() {
                try {
                    mRewards = new ArrayList<PlayerReward>();
                    final ArrayList<PlayerReward> rewardsEarned = Registry.sPlayerRewards.getRewardsEarned();

                    for(PlayerRewardTypes t : PlayerRewardTypes.values()) {
                        boolean earned = false;
                        PlayerReward rewardEarned = null;

                        for(final PlayerReward reward : rewardsEarned) {
                            if(reward.getName().equals(t.mName)) {
                                earned = true;
                                rewardEarned = reward;
                                break;
                            }
                        }

                        if(earned) {
                            mRewards.add(new PlayerReward(t.mName, rewardEarned.getLevel()));
                        } else {
                            mRewards.add(new PlayerReward(t.mName, 0));
                        }
                    }
                } catch (Exception e) {
                    Debug.e("ETDRewards :: getRewards :: exception: " + e);
                }

                runOnUiThread(returnRes);
            }
        };

        Thread thread = new Thread(null, rewardsLoader, "ETDRewardsLoader");
        thread.start();

        mProgressDialog = ProgressDialog.show(this, "Please wait...", "Fetching your rewards ...", true);
    }

    private final Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if (mRewards != null && mRewards.size() > 0) {
                mAdapter.notifyDataSetChanged();

                for (int i = 0; i < mRewards.size(); i++) {
                    mAdapter.add(mRewards.get(i));
                }
            }

            mProgressDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    };

    private class RewardAdapter extends ArrayAdapter<PlayerReward> {
        public RewardAdapter(final Context context, final int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public String getTopText(final PlayerReward pPlayerReward) {
            return pPlayerReward.getName();
        }

        public String getMidText(final PlayerReward pPlayerReward) {

            if(pPlayerReward.getLevel() > 0) {
                return format(pPlayerReward.getType().mDescriptionUpgrade, pPlayerReward);

            } else {
                return format(pPlayerReward.getType().mDescription, pPlayerReward);

            }
        }

        public String getBotText(final PlayerReward pPlayerReward) {

            // first set the name and reset everything else
            if(pPlayerReward.getLevel() > 0) {
                if(pPlayerReward.getMaxLevel() > 1) {
                    return "[Earned, Level " + pPlayerReward.getLevel() + "]";
                } else {
                    return "[Earned]";
                }
            }

            return "";
        }

        public String format(final String pMsg, final PlayerReward pPlayerReward) {

            return MessageFormat.format(
                pMsg,

                pPlayerReward.getLevel(),

                pPlayerReward.getCost(),
                pPlayerReward.getCostForNextLevel()
            );
        }

        @Override
        public PlayerReward getItem(int pPosition) {
            if(pPosition < mRewards.size())
                return  mRewards.get(pPosition);
            else
                return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getCount() {
            return mRewards.size();
        }

        @Override
        public boolean isEnabled(int position) {
            return super.isEnabled(position);
        }

        @Override
        public View getView(final int pPosition, final View pConvertView, final ViewGroup parent) {
            final PlayerReward playerReward = mRewards.get(pPosition);

            View view;
            if(pConvertView == null) {
                final LayoutInflater inflater = LayoutInflater.from(getContext()); //(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.rewards_row, null);
            } else {
                view = pConvertView;
            }

            if(playerReward == null) {
                return view;
            }

            final TextView topText = (TextView) view.findViewById(R.id.rewards_toptext);
            final TextView midText = (TextView) view.findViewById(R.id.rewards_midtext);
            final TextView botText = (TextView) view.findViewById(R.id.rewards_bottext);
            final TextView button = (TextView) view.findViewById(R.id.rewards_button);

            if(null != topText && null != midText && null != botText && button != null) {
                topText.setText(getTopText(playerReward));
                midText.setText(getMidText(playerReward));
                botText.setText(getBotText(playerReward));

                button.setClickable(false);
                button.setVisibility(View.INVISIBLE);
                button.setOnClickListener(null);

                if(playerReward.getCost() > 0 && playerReward.hasSatisfiedAllPrerequisites()) {
                    if(1 < playerReward.getMaxLevel() && playerReward.getLevel() < playerReward.getMaxLevel()) {
                        button.setText("Upgrade");
                        button.setVisibility(View.VISIBLE);

                    } else {

                        if(playerReward.getLevel() >= playerReward.getMaxLevel()) {
                            button.setText("");
                            button.setVisibility(View.INVISIBLE);

                        } else {
                            button.setText("Unlock");
                            button.setVisibility(View.VISIBLE);

                        }
                    }

                    if(button.getVisibility() == View.VISIBLE) {
                        button.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(final View v) {
                                    final int gp = Util.getIntFromString(Registry.sPlayerData.getPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS), 0);

                                    if(gp > playerReward.getCost()) {
                                        final int newGp = gp - playerReward.getCost();

                                        playerReward.setLevel(playerReward.getLevel() + 1);

                                        Registry.sPlayerRewards.earn(playerReward.getName(), playerReward.getLevel(), false);
                                        Registry.sPlayerData.setPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS, Integer.toString(newGp));
                                        Registry.sPlayerData.save();

                                        mGpTextView.setText("GP " + Util.getIntFromString(Registry.sPlayerData.getPlayerData(PlayerDataTypes.TOTAL_GAME_POINTS), 0));

                                        if(playerReward.getLevel() >= playerReward.getMaxLevel()) {
                                            button.setVisibility(Button.INVISIBLE);
                                        }

                                        topText.setText(getTopText(playerReward));
                                        midText.setText(getMidText(playerReward));
                                        botText.setText(getBotText(playerReward));
                                    }
                                }
                            }
                        );
                    }
                }

                // if we have any prereqs lets display them in the third row
                if(playerReward != null && playerReward.hasPrerequisites() && !playerReward.hasSatisfiedAllPrerequisites()) {

                    final StringBuilder sb = new StringBuilder();
                    final int[] sbLengths = new int[playerReward.getPrerequisites().length];
                    final boolean[] sbSatisfied = new boolean[playerReward.getPrerequisites().length];
                    int i = 0;

                    // the start of the bottom text
                    sb.append("Current outstanding prerequisites are listed below.\n    ");
                    final int initialLength = sb.length();

                    // iterate the rewards building the prereqs
                    for(final IPlayerRewardPrerequisite prp : playerReward.getPrerequisites()) {
                        final String name = prp.getName();

                        if(!prp.isSatisfied()) {

                            // append the achievement name
                            sb.append(name).append("\n    ");

                            // set if its satisfied as well as the length
                            sbSatisfied[i] = prp.isSatisfied();
                            sbLengths[i++] = name.length();
                        }
                    }

                    // create a spannable string builder to make text gold, as well as bold satisfied prereqs
                    final SpannableStringBuilder ssb = new SpannableStringBuilder(sb);

                    // this is the end of the first part of the string builder, also keep track of our loop
                    int spanIndex = initialLength;
                    int y = 0;

                    // iterate the lengths, which is identical in length to our satisfied array
                    for(final int length : sbLengths) {
                        final ForegroundColorSpan fcs = new ForegroundColorSpan(Color.rgb(255, 215, 0));

                        if(y >= i) {
                            break;
                        }

                        // make the text gold regardless
                        ssb.setSpan(fcs, spanIndex, spanIndex + length + 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                        // only set it to bold if its finished
                        if(sbSatisfied[y++]) {
                            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

                            ssb.setSpan(bss, spanIndex, spanIndex + length + 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                        }

                        // set our current span index
                        spanIndex += (length + 4);

                    }

                    botText.setText(ssb);

                }
            }

            return view;
        }
    }
}