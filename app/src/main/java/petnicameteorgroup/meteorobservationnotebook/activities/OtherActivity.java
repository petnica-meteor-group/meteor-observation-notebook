package petnicameteorgroup.meteorobservationnotebook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import petnicameteorgroup.meteorobservationnotebook.R;
import petnicameteorgroup.meteorobservationnotebook.utils.Nightkeeper;
import petnicameteorgroup.meteorobservationnotebook.utils.ObservationResultsAdapter;
import petnicameteorgroup.meteorobservationnotebook.utils.UIFont;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        UIFont.apply(this, (Button) findViewById(R.id.button_instructions));
        UIFont.apply(this, (Button) findViewById(R.id.button_test_notebook));
        UIFont.apply(this, (Button) findViewById(R.id.button_export_data));
    }

    public void showInstructions(View v) {
        startActivity(new Intent(this, InstructionsActivity.class));
    }

    public void testNotebook(View v) {
        startActivity(new Intent(this, ObservationTestActivity.class));
    }

    public void exportData(View v) {
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
                new Nightkeeper(OtherActivity.this).exportNights(input.getText().toString());
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

}
