package rs.meteori.meteorobservationnotebook.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.UIFont;

public class InstructionsInitialActivity extends AppCompatActivity {

    private static String READ_FILE = "instructions_read";

    public static boolean read(Context context) {
        File file = new File(context.getFilesDir(), READ_FILE);
        return file.exists();
    }

    public void continueToObservation(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions_initial);

        File file = new File(getFilesDir(), READ_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        UIFont.apply(this, (TextView) findViewById(R.id.title));
        UIFont.apply(this, (TextView) findViewById(R.id.button_continue));
    }

}
