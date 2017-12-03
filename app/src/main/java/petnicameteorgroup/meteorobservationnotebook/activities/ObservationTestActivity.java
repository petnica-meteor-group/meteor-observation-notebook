package petnicameteorgroup.meteorobservationnotebook.activities;

import android.os.Bundle;

import petnicameteorgroup.meteorobservationnotebook.utils.Notebook;
import petnicameteorgroup.meteorobservationnotebook.R;

/**
 * Created by vladi on 11/3/2017.
 */

public class ObservationTestActivity extends ObservationActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_test);
        notebook = (Notebook) findViewById(R.id.notebook);
    }

    @Override
    protected void onSpecialKey(int key) {
        if (key == ObservationActivity.SPECIAL_KEY_ONE) {
            notebook.clear();
            notebook.enable();
            vibrate(ObservationActivity.CONFIRM_VIBRATE_DURATION);
        } else if (key == ObservationActivity.SPECIAL_KEY_TWO && notebook.isEnabled()) {
            notebook.disable();
            notebook.clear();
            vibrate(ObservationActivity.CONFIRM_VIBRATE_DURATION);
        }
    }

}
