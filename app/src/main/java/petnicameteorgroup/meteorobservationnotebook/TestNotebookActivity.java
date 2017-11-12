package petnicameteorgroup.meteorobservationnotebook;

import android.os.Bundle;
import android.view.ViewGroup;

/**
 * Created by vladi on 11/3/2017.
 */

public class TestNotebookActivity extends NotebookActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_notebook);
        notebook = (Notebook) findViewById(R.id.notebook);
    }

    @Override
    protected void onSpecialKey(int key) {
        if (key == NotebookActivity.SPECIAL_KEY_ONE) {
            notebook.clear();
            notebook.enable();
            vibrate(NotebookActivity.CONFIRM_VIBRATE_DURATION);
        } else if (key == NotebookActivity.SPECIAL_KEY_TWO && notebook.isEnabled()) {
            notebook.disable();
            notebook.clear();
            vibrate(NotebookActivity.CONFIRM_VIBRATE_DURATION);
        }
    }

}
