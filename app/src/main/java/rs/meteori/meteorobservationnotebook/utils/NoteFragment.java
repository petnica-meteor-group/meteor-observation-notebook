package rs.meteori.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.activities.NotesViewActivity;

import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.cos;

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

        clocktime = rootView.findViewById(R.id.note_clocktime);
        counter = rootView.findViewById(R.id.note_counter);

        String UTCClockTime = note.getUTCClockTime();
        clocktime.setText(UTCClockTime);

        int counterCurrent = getArguments().getInt(COUNTER_CURRENT_ARG);
        int counterMax = getArguments().getInt(COUNTER_MAX_ARG);
        counter.setText(counterCurrent + "/" + counterMax);

        return rootView;
    }

    private TextView clocktime;
    private TextView counter;

}
