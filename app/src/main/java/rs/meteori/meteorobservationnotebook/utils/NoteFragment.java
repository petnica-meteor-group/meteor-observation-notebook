package rs.meteori.meteorobservationnotebook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import rs.meteori.meteorobservationnotebook.R;

/**
 * Created by vladi on 11/2/2017.
 */

public class NoteFragment extends Fragment {

    public static final String NOTE_ARG =
            "rs.meteori.meteorobservationnotebook.utils.NoteFragment.NOTE_ARG";
    public static final String COUNTER_CURRENT_ARG =
            "rs.meteori.meteorobservationnotebook.utils.NoteFragment.COUNTER_CURRENT_ARG";
    public static final String COUNTER_MAX_ARG =
            "rs.meteori.meteorobservationnotebook.utils.NoteFragment.COUNTER_MAX_ARG";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        Note note = (Note) getArguments().getSerializable(NOTE_ARG);

        ImageView imageView = rootView.findViewById(R.id.note_image_view);
        imageView.setBackground(new BitmapDrawable(getResources(), note.getBitmap()));

        TextView clocktimeView = rootView.findViewById(R.id.note_clocktime);
        clocktimeView.setText(note.getClockTime());

        TextView counterView = rootView.findViewById(R.id.note_counter);
        int counterCurrent = getArguments().getInt(COUNTER_CURRENT_ARG);
        int counterMax = getArguments().getInt(COUNTER_MAX_ARG);
        counterView.setText(counterCurrent + "/" + counterMax);

        return rootView;
    }
}
