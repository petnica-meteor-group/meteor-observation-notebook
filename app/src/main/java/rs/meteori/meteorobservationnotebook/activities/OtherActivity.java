package rs.meteori.meteorobservationnotebook.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.Nightkeeper;
import rs.meteori.meteorobservationnotebook.utils.UIFont;

public class OtherActivity extends AppCompatActivity {

    private static int REQUIRED_PERMISSION_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        UIFont.apply(this, (TextView) findViewById(R.id.title));
        UIFont.apply(this, (Button) findViewById(R.id.button_instructions));
        UIFont.apply(this, (Button) findViewById(R.id.button_details));
        UIFont.apply(this, (Button) findViewById(R.id.button_test_notebook));
        UIFont.apply(this, (Button) findViewById(R.id.button_export_data));
    }

    public void showInstructions(View v) {
        startActivity(new Intent(this, InstructionsActivity.class));
    }

    public void showDetails(View v) {
        startActivity(new Intent(this, DetailsActivity.class));
    }

    public void testNotebook(View v) {
        startActivity(new Intent(this, ObservationTestActivity.class));
    }

    private void showExportDataDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Name of the exported file?");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(40, 10, 50, 10);
        input.setLayoutParams(layoutParams);
        container.addView(input);
        alertDialog.setView(container);

        alertDialog.setPositiveButton("Export", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String path = new Nightkeeper(OtherActivity.this).exportNights(input.getText().toString());
                Toast toast = Toast.makeText(OtherActivity.this, "Data exported to " + path, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setMargin(0, 0.08F);
                toast.show();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    public void exportData(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUIRED_PERMISSION_CODE);
            } else {
                showExportDataDialog();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showExportDataDialog();
        }
    }

}
