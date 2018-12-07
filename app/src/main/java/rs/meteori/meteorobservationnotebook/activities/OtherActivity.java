package rs.meteori.meteorobservationnotebook.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.LinearLayout;
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

        TextView title = new TextView(this);
        title.setText(getString(R.string.export_data));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        FrameLayout container = new FrameLayout(this);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint(getString(R.string.name_of_exported_file));
        layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(40, 5, 50, 0);
        input.setLayoutParams(layoutParams);

        linearLayout.addView(input);

        container.addView(linearLayout);

        alertDialog.setView(container);

        alertDialog.setPositiveButton(R.string.export, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String path = new Nightkeeper(OtherActivity.this).exportNights(input.getText().toString());
                Toast toast = Toast.makeText(OtherActivity.this, "Data exported to " + path, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.setMargin(0, 0.08F);
                toast.show();
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
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
