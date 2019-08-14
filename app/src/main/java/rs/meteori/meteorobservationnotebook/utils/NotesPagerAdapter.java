package rs.meteori.meteorobservationnotebook.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by vladi on 11/2/2017.
 */

public class NotesPagerAdapter extends FragmentStatePagerAdapter {

    private Night night;

    public NotesPagerAdapter(FragmentManager fragmentManager, Night night) {
        super(fragmentManager);
        this.night = night;
    }

    @Override
    public Fragment getItem(int i) {
        int maxItems = night.getNoteCount();
        while (i < 0) {
            i += maxItems;
        }
        i %= maxItems;

        Fragment fragment = new NoteFragment();

        Bundle args = new Bundle();
        args.putSerializable(NoteFragment.NOTE_ARG, night.getNote(i));
        args.putInt(NoteFragment.COUNTER_CURRENT_ARG, i + 1);
        args.putInt(NoteFragment.COUNTER_MAX_ARG, getCount());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return night.getNoteCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Note " + (position + 1);
    }

}
