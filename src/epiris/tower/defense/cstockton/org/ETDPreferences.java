package epiris.tower.defense.cstockton.org;

import epiris.tower.defense.cstockton.org.R;
import epiris.tower.defense.cstockton.org.object.Registry;
import epiris.tower.defense.cstockton.org.player.storage.PlayerStorageConnector;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ETDPreferences extends PreferenceActivity {
    final static public int MENU_SHOW_CURRENT_PREFERENCES = 1;
    final static public int MENU_RESET_EVERYTHING = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        setContentView(R.layout.preferences);

        ((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {
                    ETDPreferences.this.finish();
                }
            }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        menu.add(Menu.NONE, MENU_SHOW_CURRENT_PREFERENCES, 0, "Show current preferences");
        menu.add(Menu.NONE, MENU_RESET_EVERYTHING, 0, "Reset Everything");

    	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
    	switch (item.getItemId()) {
            case MENU_SHOW_CURRENT_PREFERENCES:
                startActivity(new Intent(this, ETDSettings.class));
                return true;
            case MENU_RESET_EVERYTHING:
                if(Registry.sPlayerStorageConnector == null) {
                    Registry.sPlayerStorageConnector = new PlayerStorageConnector(ETDPreferences.this.getApplicationContext());
                }
                Registry.sPlayerStorageConnector.reset();
                return true;
    	}
    	return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(Registry.sPlayerStorageConnector != null) {
            Registry.sPlayerStorageConnector.close();
        }
    }
}