package petnicameteorgroup.meteorobservationnotebook.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import petnicameteorgroup.meteorobservationnotebook.R;
import petnicameteorgroup.meteorobservationnotebook.utils.UIFont;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        UIFont.apply(this, (Button) findViewById(R.id.button_instructions));
        UIFont.apply(this, (Button) findViewById(R.id.button_test_notebook));
    }

    public void showInstructions(View v) {
        startActivity(new Intent(this, InstructionsActivity.class));
    }

    public void testNotebook(View v) {
        startActivity(new Intent(this, ObservationTestActivity.class));
    }

}
