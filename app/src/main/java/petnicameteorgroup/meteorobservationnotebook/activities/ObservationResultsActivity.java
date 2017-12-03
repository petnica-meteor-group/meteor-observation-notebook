package petnicameteorgroup.meteorobservationnotebook.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import petnicameteorgroup.meteorobservationnotebook.utils.Night;
import petnicameteorgroup.meteorobservationnotebook.utils.Nightkeeper;
import petnicameteorgroup.meteorobservationnotebook.utils.ObservationResultsAdapter;
import petnicameteorgroup.meteorobservationnotebook.R;

public class ObservationResultsActivity  extends ListActivity {

    private List<Night> nights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProgressBar progressBar = new ProgressBar(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params);
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        nights = new Nightkeeper(this).getNights();

        ObservationResultsAdapter adapter = new ObservationResultsAdapter(
                this, R.layout.observation_results_row, nights);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(this, NotesViewActivity.class);
        intent.putExtra(NotesViewActivity.NIGHT_ARG, (Serializable) nights.get(position));

        startActivity(intent);
    }

}
