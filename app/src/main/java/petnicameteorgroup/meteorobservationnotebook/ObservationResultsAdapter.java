package petnicameteorgroup.meteorobservationnotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vladi on 11/3/2017.
 */

public class ObservationResultsAdapter extends ArrayAdapter<ObservationResults> {

    public ObservationResultsAdapter(Context context, int resource, List<ObservationResults> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.observation_results_row, null);
        }

        ObservationResults observationResults = getItem(position);

        if (observationResults != null) {
            TextView name = view.findViewById(R.id.name);
            name.setText(observationResults.getName());
        }

        return view;
    }

}
