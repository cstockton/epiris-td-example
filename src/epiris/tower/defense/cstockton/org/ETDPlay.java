package epiris.tower.defense.cstockton.org;

import epiris.tower.defense.cstockton.org.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ETDPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.play);

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDPlay.this.finish();
                }
            }
        );

        /*
        ((Button) findViewById(R.id.play_mode_conquer_new_game)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    startActivity(new Intent(ETDPlay.this.getApplicationContext(), ETDPlayConquer.class));
                    ETDPlay.this.finish();
                }
            }
        );
        */

        ((Button) findViewById(R.id.play_mode_survival_new_game)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    startActivity(new Intent(ETDPlay.this.getApplicationContext(), ETDPlaySurvival.class));
                    ETDPlay.this.finish();
                }
            }
        );

        /*
        ((Button) findViewById(R.id.play_mode_penny_pincher_new_game)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    startActivity(new Intent(ETDPlay.this.getApplicationContext(), ETDPlaySurvival.class));
                    ETDPlay.this.finish();
                }
            }
        );
        */

        /* @TODO continue this */
        /*
        final Button conquerResumeButton = (Button) findViewById(R.id.play_mode_conquer_resume);
        conquerResumeButton.setVisibility(Button.INVISIBLE);

        final Button survivalResumeButton = (Button) findViewById(R.id.play_mode_survival_resume);
        survivalResumeButton.setVisibility(Button.INVISIBLE);

        final Button pennypencherResumeButton = (Button) findViewById(R.id.play_mode_penny_pincher_resume);
        pennypencherResumeButton.setVisibility(Button.INVISIBLE);
        */

	}
}