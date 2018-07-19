package petnicameteorgroup.meteorobservationnotebook.utils;

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

import petnicameteorgroup.meteorobservationnotebook.R;

/**
 * Created by vladi on 11/2/2017.
 */

public class NoteFragment extends Fragment {

    public static final String NOTE_ARG =
            "petnicameteorgroup.meteorobservationnotebook.utils.NoteFragment.NOTE_ARG";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        Note note = (Note) getArguments().getSerializable(NOTE_ARG);

        ImageView imageView = rootView.findViewById(R.id.note_image_view);
        imageView.setBackground(new BitmapDrawable(getResources(), note.getBitmap()));

        TextView textView = rootView.findViewById(R.id.note_clocktime);
        textView.setText(note.getClockTime());

        return rootView;
    }
}
