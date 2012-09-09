package epiris.tower.defense.cstockton.org;

import epiris.tower.defense.cstockton.org.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ETDCredits extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_settings_layout);

		StringBuilder builder = new StringBuilder();
		builder.append("No credits!");

		TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
		settingsTextView.setText(builder.toString());

	}
}