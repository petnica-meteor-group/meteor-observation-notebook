package petnicameteorgroup.meteorobservationnotebook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

import petnicameteorgroup.meteorobservationnotebook.R;
import petnicameteorgroup.meteorobservationnotebook.utils.Night;
import petnicameteorgroup.meteorobservationnotebook.utils.Nightkeeper;
import petnicameteorgroup.meteorobservationnotebook.utils.ObservationResultsAdapter;
import petnicameteorgroup.meteorobservationnotebook.utils.UIFont;

public class ObservationResultsActivity  extends AppCompatActivity {

    private static final int RESULTS_SPACING = 15;

    private List<Night> nights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_results);

        UIFont.apply(this, (TextView) findViewById(R.id.title));
        UIFont.apply(this, (TextView) findViewById(R.id.empty_results_text));

        final ListView listView = (ListView) findViewById(R.id.results_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ObservationResultsActivity.this, NotesViewActivity.class);
                intent.putExtra(NotesViewActivity.NIGHT_ARG, (Serializable) nights.get(i));

                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Night night = nights.get(i);

                AlertDialog.Builder alertDialog = new  AlertDialog.Builder(ObservationResultsActivity.this);
                alertDialog.setTitle("Delete?");
                alertDialog.setMessage("Do you want to delete " + night.getUIName() + "?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Nightkeeper nightkeeper = new Nightkeeper(ObservationResultsActivity.this);

                        nightkeeper.deleteNight(night.getName());
                        nights = nightkeeper.getNights();
                        ObservationResultsAdapter adapter = new ObservationResultsAdapter(
                                ObservationResultsActivity.this, R.layout.observation_results_row, nights);
                        listView.setAdapter(adapter);
                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //alertDialog.dismiss();
                    }
                });

                alertDialog.show();

                return true;
            }
        });

        listView.setEmptyView(findViewById(R.id.empty_results_text));

        nights = new Nightkeeper(this).getNights();

        ObservationResultsAdapter adapter = new ObservationResultsAdapter(
                this, R.layout.observation_results_row, nights);
        listView.setAdapter(adapter);
    }

}
