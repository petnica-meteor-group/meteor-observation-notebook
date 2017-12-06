package petnicameteorgroup.meteorobservationnotebook.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import petnicameteorgroup.meteorobservationnotebook.R;
import petnicameteorgroup.meteorobservationnotebook.utils.UIFont;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        UIFont.apply(this, (TextView) findViewById(R.id.title));
    }

}
