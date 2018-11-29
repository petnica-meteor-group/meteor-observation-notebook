package rs.meteori.meteorobservationnotebook.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.UIFont;

public class MenuActivity extends AppCompatActivity {

    private static int REQUIRED_PERMISSION_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getPermission(Manifest.permission.REORDER_TASKS);
        getPermission(Manifest.permission.VIBRATE);
        getPermission(Manifest.permission.WAKE_LOCK);
        getPermission(Manifest.permission.DISABLE_KEYGUARD);
        //getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        UIFont.apply(this, (TextView) findViewById(R.id.title));
        UIFont.apply(this, (Button) findViewById(R.id.begin_observation_button));
        UIFont.apply(this, (Button) findViewById(R.id.test_observation_button));
        UIFont.apply(this, (Button) findViewById(R.id.instructions_button));
        UIFont.apply(this, (TextView) findViewById(R.id.pmg));
    }

    private void getPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, REQUIRED_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {}
    }

    public void beginObservation(View v) {
        startActivity(new Intent(this, ObservationActivity.class));
        if (!InstructionsActivity.read(this)) {
            startActivity(new Intent(this, InstructionsActivity.class));
        }
    }

    public void showObservationResults(View v) {
        startActivity(new Intent(this, ObservationResultsActivity.class));
    }

    public void showOther(View v) {
        startActivity(new Intent(this, OtherActivity.class));
    }

}
