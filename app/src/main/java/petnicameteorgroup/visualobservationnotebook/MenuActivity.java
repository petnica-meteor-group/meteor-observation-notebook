package petnicameteorgroup.visualobservationnotebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private static int REQUIRED_PERMISSION_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.REORDER_TASKS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUIRED_PERMISSION_CODE);
            }
            if (checkSelfPermission(Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.VIBRATE}, REQUIRED_PERMISSION_CODE);
            }
        }

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        ((TextView) findViewById(R.id.title)).setTypeface(font);
        ((Button) findViewById(R.id.begin_observation_button)).setTypeface(font);
        ((Button) findViewById(R.id.test_observation_button)).setTypeface(font);
        ((Button) findViewById(R.id.instructions_button)).setTypeface(font);
        ((TextView) findViewById(R.id.pmg)).setTypeface(font);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {}
    }

    public void beginObservation(View v) {
        startActivity(new Intent(this, NotebookActivity.class));
    }

    public void showObservationResults(View v) {
        startActivity(new Intent(this, ObservationResultsActivity.class));
    }

    public void showOther(View v) {
        startActivity(new Intent(this, OtherActivity.class));
    }

}
