package petnicameteorgroup.meteorobservationnotebook.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import petnicameteorgroup.meteorobservationnotebook.utils.NotesPagerAdapter;
import petnicameteorgroup.meteorobservationnotebook.R;

public class NotesViewActivity extends FragmentActivity {

    NotesPagerAdapter resultsPagerAdapter;
    ViewPager viewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        resultsPagerAdapter = new NotesPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.results_view_pager);
        viewPager.setAdapter(resultsPagerAdapter);
    }

}