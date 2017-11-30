package petnicameteorgroup.meteorobservationnotebook.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Calendar;

import petnicameteorgroup.meteorobservationnotebook.utils.ObservationResults;
import petnicameteorgroup.meteorobservationnotebook.utils.ObservationResultsAdapter;
import petnicameteorgroup.meteorobservationnotebook.R;

public class ObservationResultsActivity  extends ListActivity {

    public static String RESULTS_DIR = "results";

    public static String getNightName() {
        Calendar now = Calendar.getInstance();

        Calendar other = (Calendar) now.clone(), first, second;
        if (now.get(Calendar.HOUR_OF_DAY) > 15) {
            other.add(Calendar.DAY_OF_MONTH, 1);
            first = now;
            second = other;
        } else {
            other.add(Calendar.DAY_OF_MONTH, -1);
            first = other;
            second = now;
        }

        int year1, year2, month1, month2, day1, day2;

        year1 = first.get(Calendar.YEAR);
        year2 = second.get(Calendar.YEAR);

        month1 = first.get(Calendar.MONTH) + 1;
        month2 = second.get(Calendar.MONTH) + 1;

        day1 = first.get(Calendar.DAY_OF_MONTH);
        day2 = second.get(Calendar.DAY_OF_MONTH);

        String nightName = "" + year1 + "_" + month1 + "_" + day1 + "-";
        nightName += "" + year2 + "_" + month2 + "_" + day2;

        return nightName;
    }

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

        ArrayList<ObservationResults> observationResults = getObservationResults();

        ObservationResultsAdapter adapter = new ObservationResultsAdapter(
                this, R.layout.observation_results_row, observationResults);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    private ArrayList<ObservationResults> getObservationResults() {
        return null;
    }

}
