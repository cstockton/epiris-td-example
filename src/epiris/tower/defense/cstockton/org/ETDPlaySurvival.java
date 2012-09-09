package epiris.tower.defense.cstockton.org;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.config.GameTypes;
import epiris.tower.defense.cstockton.org.config.MapTypes;
import epiris.tower.defense.cstockton.org.config.WaveStrategyTypes;
import epiris.tower.defense.cstockton.org.game.GameOptions;
import epiris.tower.defense.cstockton.org.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ETDPlaySurvival extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.play_survival);

		final String[] maps = new String[MapTypes.values().length];
		for(final MapTypes mt : MapTypes.values()) {
		    maps[mt.ordinal()] = mt.getTitle();
		}

		final String[] difficulties = new String[] {
            "1 - Really Easy",
            "2 - Easy",
            "3 - Medium",
            "4 - Medium Hard",
            "5 - Hard",
            "6 - Beyond Hard",
            "7 - Semi-Expert",
            "8 - Expert",
            "9 - Semi-Pro",
            "10 - Pro",
            "15 - Master",
            "20 - Legendary",
            "40 - Insane",
            "70 - Beyond Insane",
            "100 - Fully Upgraded",
            "200 - Fully Upgraded and Efficient",
            "350 - Not expecting to win",
            "700 - Nearly Impossible",
            "1000 - This. Is. Madness."
        };

		final Spinner mapSpinner = (Spinner) findViewById(R.id.play_survival_map_spinner);
		mapSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, maps));

		final Spinner difficultySpinner = (Spinner) findViewById(R.id.play_survival_difficulty_spinner);
		difficultySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficulties));

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDPlaySurvival.this.finish();
                }
            }
        );

        ((Button) findViewById(R.id.play_survival_play_now_button)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    final Intent intent = new Intent(ETDPlaySurvival.this.getApplicationContext(), ETDGameActivity.class);
                    final GameOptions gameOptions = new GameOptions();

                    // set our game options
                    gameOptions.setMode(GameTypes.SURVIVAL);
                    gameOptions.setMapType(MapTypes.getMapTypeByTitle((String) mapSpinner.getSelectedItem()));
                    gameOptions.setWaveStrategyType(WaveStrategyTypes.BASIC_WAVE_STRATEGY);
                    gameOptions.setDifficulty(Util.getIntFromString(((String) difficultySpinner.getSelectedItem()).split(" - ")[0], 1));

                    intent.putExtra(GameOptions.sItentKey, gameOptions);

                    startActivity(intent);
                    ETDPlaySurvival.this.finish();
                }
            }
        );
	}
}