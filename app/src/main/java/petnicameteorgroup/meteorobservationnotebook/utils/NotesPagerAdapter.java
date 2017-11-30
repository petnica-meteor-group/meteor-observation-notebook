package petnicameteorgroup.meteorobservationnotebook.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import petnicameteorgroup.meteorobservationnotebook.utils.NoteFragment;

/**
 * Created by vladi on 11/2/2017.
 */

public class NotesPagerAdapter extends FragmentStatePagerAdapter {

    public NotesPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new NoteFragment();

        Bundle args = new Bundle();
        args.putParcelable(NoteFragment.NOTE_BITMAP_ARG, null);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return 100;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

}
