package rs.meteori.meteorobservationnotebook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.UIFont;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        UIFont.apply(this, (TextView) findViewById(R.id.title));
    }

}
