package rs.meteori.meteorobservationnotebook.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rs.meteori.meteorobservationnotebook.utils.Night;
import rs.meteori.meteorobservationnotebook.utils.NotesPagerAdapter;
import rs.meteori.meteorobservationnotebook.R;
import rs.meteori.meteorobservationnotebook.utils.VerticalViewPager;

public class NotesViewActivity extends FragmentActivity {

    public static String NIGHT_ARG = "rs.meteori.meteorobservationnotebook.activities.NotesViewActivity.NIGHT_ARG";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        Night night = (Night) getIntent().getSerializableExtra(NIGHT_ARG);

        NotesPagerAdapter resultsPagerAdapter = new NotesPagerAdapter(getSupportFragmentManager(), night);
        viewPager = findViewById(R.id.results_view_pager);
        viewPager.setAdapter(resultsPagerAdapter);
    }

    public void jumpToDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView title = new TextView(this);
        title.setText(getString(R.string.jump_to));
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        builder.setCustomTitle(title);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
        relativeLayout.setGravity(Gravity.CENTER);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        input.setMinEms(5);
        relativeLayout.addView(input);

        builder.setView(relativeLayout);

        builder.setPositiveButton("Jump", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    viewPager.setCurrentItem(Integer.parseInt(input.getText().toString()) - 1);
                } catch (Exception e) {}
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private VerticalViewPager viewPager;

}