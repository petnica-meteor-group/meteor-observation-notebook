package petnicameteorgroup.meteorobservationnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    public void showInstructions(View v) {
        startActivity(new Intent(this, InstructionsActivity.class));
    }

    public void testNotebook(View v) {
        startActivity(new Intent(this, TestNotebookActivity.class));
    }

}
