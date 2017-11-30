package petnicameteorgroup.meteorobservationnotebook.utils;

import java.io.File;
import java.util.Calendar;

/**
 * Created by schutzekatze on 11/30/17.
 */

public class ResultsKeeper {

    private static String RESULTS_DIR_NAME = "results";
    private static String NOTES_DIR_NAME = "notes";

    public static File getResultsDir() {

    }

    public static File getTonightsNotesDir() {

    }

    private static String getNightName() {
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
