package petnicameteorgroup.meteorobservationnotebook.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import petnicameteorgroup.meteorobservationnotebook.utils.Night;
import petnicameteorgroup.meteorobservationnotebook.utils.NotesPagerAdapter;
import petnicameteorgroup.meteorobservationnotebook.R;

public class NotesViewActivity extends FragmentActivity {

    public static String NIGHT_ARG = "petnicameteorgroup.meteorobservationnotebook.activities.NotesViewActivity.NIGHT_ARG";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        Night night = (Night) getIntent().getSerializableExtra(NIGHT_ARG);

        NotesPagerAdapter resultsPagerAdapter = new NotesPagerAdapter(getSupportFragmentManager(), night);
        ViewPager viewPager = (ViewPager) findViewById(R.id.results_view_pager);
        viewPager.setAdapter(resultsPagerAdapter);
    }

}