package epiris.tower.defense.cstockton.org;

import java.util.ArrayList;
import java.util.Map;

import org.anddev.andengine.util.Debug;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.statistics.PlayerStatistics;
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
import android.widget.TextView;

public class ETDStatistics extends ListActivity {

    private ProgressDialog mProgressDialog = null;
    private ArrayList<Statistic> mStatistics = null;
    private StatisticAdapter mAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Registry.sPlayerStorageConnector.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        Registry.sPlayerStorageConnector = new PlayerStorageConnector(ETDStatistics.this.getApplicationContext());
        Registry.sPlayerStatistics = new PlayerStatistics();
        Registry.sPlayerStatistics.sync();

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDStatistics.this.finish();
                }
            }
        );

        loadStats();
    }

    public void loadStats() {
        mStatistics = new ArrayList<Statistic>();
        mAdapter = new StatisticAdapter(this, R.layout.statistics_row, mStatistics);
        setListAdapter(this.mAdapter);

        final Runnable statisticsLoader = new Runnable() {

            @Override
            public void run() {
                try {
                    mStatistics = new ArrayList<Statistic>();

                    for(final Map.Entry<String, Integer> s : Registry.sPlayerStatistics.getStatistics().entrySet()) {
                        mStatistics.add(new Statistic(Registry.sPlayerStatistics.getStatisticDescription(s.getKey()), s.getValue()));
                    }

                } catch (Exception e) {
                    Debug.e("ETDStatistics :: getStatistics :: exception: " + e);
                }

                runOnUiThread(returnRes);
            }
        };

        Thread thread = new Thread(null, statisticsLoader, "ETDStatisticsLoader");
        thread.start();

        mProgressDialog = ProgressDialog.show(this, "Please wait...", "Fetching your statistics ...", true);
    }

    private final Runnable returnRes = new Runnable() {

        @Override
        public void run() {
            if (mStatistics != null && mStatistics.size() > 0) {
                mAdapter.notifyDataSetChanged();
                for (int i = 0; i < mStatistics.size(); i++) {
                    mAdapter.add(mStatistics.get(i));
                }
            }
            mProgressDialog.dismiss();
            mAdapter.notifyDataSetChanged();
        }
    };

    public class Statistic {

        private String statisticName;
        private int statisticValue;

        public Statistic(final String pStatisticName, final int pStatisticValue) {
            setStatisticName(pStatisticName);
            setStatisticValue(pStatisticValue);
        }

        public String getStatisticName() {
            return statisticName;
        }

        public void setStatisticName(final String pStatisticName) {
            statisticName = pStatisticName;
        }

        public int getStatisticValue() {
            return statisticValue;
        }

        public void setStatisticValue(final int pStatisticValue) {
            statisticValue = pStatisticValue;
        }
    }

    private class StatisticAdapter extends ArrayAdapter<Statistic> {
        final private ArrayList<Statistic> mItems;

        public StatisticAdapter(Context context, int textViewResourceId, ArrayList<Statistic> pItems) {
            super(context, textViewResourceId, pItems);

            mItems = pItems;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.statistics_row, null);
            }

            final Statistic o = mItems.get(position);
            if (o != null) {
                final TextView tt = (TextView) v.findViewById(R.id.statistics_toptext);
                final TextView bt = (TextView) v.findViewById(R.id.statistics_bottomtext);

                if (tt != null) {
                    tt.setText(o.getStatisticName() + ": ");
                }
                if (bt != null) {
                    bt.setText(Integer.toString(o.getStatisticValue()));
                }
            }
            return v;
        }
    }
}