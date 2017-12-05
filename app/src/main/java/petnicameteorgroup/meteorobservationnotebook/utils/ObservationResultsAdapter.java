package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import petnicameteorgroup.meteorobservationnotebook.R;

/**
 * Created by vladi on 11/3/2017.
 */

public class ObservationResultsAdapter extends ArrayAdapter<Night> {

    public ObservationResultsAdapter(Context context, int resource, List<Night> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.observation_results_row, null);
        }

        Night night = getItem(position);

        if (night != null) {
            TextView name = view.findViewById(R.id.name);
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/PressStart2P-Regular.ttf");
            name.setTypeface(font);
            name.setText(night.getUIName());
        }

        return view;
    }

}
