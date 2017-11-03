package petnicameteorgroup.meteorobservationnotebook;

/**
 * Created by vladi on 11/3/2017.
 */

public class TestNotebookActivity extends NotebookActivity {

    @Override
    protected void onSpecialKey(int key) {
        if (key == NotebookActivity.SPECIAL_KEY_ONE) {
            notebook.enable();
            vibrate(NotebookActivity.CONFIRM_VIBRATE_DURATION);
        } else if (key == NotebookActivity.SPECIAL_KEY_TWO && notebook.isEnabled()) {
            notebook.disable();
            notebook.clear();
            vibrate(NotebookActivity.CONFIRM_VIBRATE_DURATION);
        }
    }

}
