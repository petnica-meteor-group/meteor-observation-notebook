package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import petnicameteorgroup.meteorobservationnotebook.activities.ObservationResultsActivity;

/**
 * Created by schutzekatze on 11/30/17.
 */

public class Nightkeeper {

    /*

    Dir structure:

    nights
        - night1
            notes
        - night2
            notes
        ...

     */

    private static String NIGHTS_DIR_NAME = "nights";
    private static String NOTES_DIR_NAME = "notes";

    private Context context;

    public Nightkeeper(Context context) {
        this.context = context;
        new File(context.getFilesDir(), NIGHTS_DIR_NAME).mkdirs();
    }

    public List<Night> getNights() {
        ArrayList<Night> nights = new ArrayList<>();

        File[] nightsDirs = new File(context.getFilesDir(), NIGHTS_DIR_NAME).listFiles();
        for (File nightDir : nightsDirs) {
            nights.add(new Night(nightDir.getName(), new File(nightDir, NOTES_DIR_NAME)));
        }

        return nights;
    }

    public Night getThisNight() {
        String name = getThisNightName();
        File nightsDir = new File(context.getFilesDir(), NIGHTS_DIR_NAME);
        File notesDir = new File(nightsDir, name + File.separator + NOTES_DIR_NAME);
        notesDir.mkdirs();
        return new Night(name, notesDir);
    }

    private static String getThisNightName() {
        Calendar now = Calendar.getInstance();

        Calendar other = (Calendar) now.clone(), first, second;
        if (now.get(Calendar.HOUR_OF_DAY) > 15) {
            other.add(Calendar.DAY_OF_MONTH, 1);
            first = now;
            second = other;
        } else {
            other.add(Calendar.DAY_OF_MONTH, -1);
            first = other;
            second = now;
        }

        int year1, year2, month1, month2, day1, day2;

        year1 = first.get(Calendar.YEAR);
        year2 = second.get(Calendar.YEAR);

        month1 = first.get(Calendar.MONTH) + 1;
        month2 = second.get(Calendar.MONTH) + 1;

        day1 = first.get(Calendar.DAY_OF_MONTH);
        day2 = second.get(Calendar.DAY_OF_MONTH);

        String nightName = "" + year1 + "_" + month1 + "_" + day1 + "-";
        nightName += "" + year2 + "_" + month2 + "_" + day2;

        return nightName;
    }

}
