package rs.meteori.meteorobservationnotebook.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
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

    public int dpToPx(int dp, Resources r) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

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

/*
        Activity activity = getActivity();
        Resources r = activity.getResources();

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        LinearLayout l1 = new LinearLayout(activity);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, marginBottom);
        l1.setLayoutParams(params);*/
/*
            <LinearLayout
        android:id="@+id/note_layout_left"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="20dp"
        android:layout_marginTop="44dp"
        android:orientation="vertical">
    </LinearLayout>
*/
/*
        for (int i = 0; i < 5; i++) {
            Button button = new Button(activity);
            Drawable d = getResources().getDrawable(R.drawable.note_button);
            DrawableCompat.setTint(d, getResources().getColor(R.color.PER));
            button.setBackground(d);
            button.setText("PER");
            button.setTextColor(Color.WHITE);

            int marginBottom = dpToPx(height / 36, r);
            int buttonSize = dpToPx(height / 18, r);

            params = new RelativeLayout.LayoutParams(buttonSize, buttonSize);
            params.setMargins(0, 0, 0, marginBottom);
            button.setLayoutParams(params);

            layout.addView(button);
        }*/

        return rootView;
    }

    private TextView clocktime;
    private TextView counter;

}
