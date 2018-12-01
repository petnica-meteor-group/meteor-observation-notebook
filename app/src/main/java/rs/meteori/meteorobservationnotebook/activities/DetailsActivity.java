package rs.meteori.meteorobservationnotebook.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.UIFont;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        UIFont.apply(this, (TextView) findViewById(R.id.title));
    }

}
