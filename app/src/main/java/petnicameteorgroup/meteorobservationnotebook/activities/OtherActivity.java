package petnicameteorgroup.meteorobservationnotebook.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import petnicameteorgroup.meteorobservationnotebook.R;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/PressStart2P-Regular.ttf");
        ((Button) findViewById(R.id.button_instructions)).setTypeface(font);
        ((Button) findViewById(R.id.button_test_notebook)).setTypeface(font);
    }

    public void showInstructions(View v) {
        startActivity(new Intent(this, InstructionsActivity.class));
    }

    public void testNotebook(View v) {
        startActivity(new Intent(this, ObservationTestActivity.class));
    }

}
