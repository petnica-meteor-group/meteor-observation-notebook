package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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

        Collections.sort(nights, new Comparator<Night>(){
            public int compare(Night n1, Night n2)
            {
                return n1.getName().compareTo(n2.getName());
            }
        });

        return nights;
    }

    public Night getThisNight() {
        String name = getThisNightName();
        File nightsDir = new File(context.getFilesDir(), NIGHTS_DIR_NAME);
        File notesDir = new File(nightsDir, name + File.separator + NOTES_DIR_NAME);
        return new Night(name, notesDir);
    }

    public void exportNights(String dirname) {
        try {
            FileUtils.copyDirectory(
                    new File(context.getFilesDir(), NIGHTS_DIR_NAME),
                    new File(Environment.getExternalStorageDirectory(), dirname)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getThisNightName() {
        Calendar now = Calendar.getInstance();

        Calendar other = (Calendar) now.clone(), first, second;
        if (now.get(Calendar.HOUR_OF_DAY) >= 15) {
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

        String year = (year1 == year2 ? ("" + year1) : ("" + year1 + "~" + year2));
        String month = (month1 == month2 ? String.format("%02d", month1) :
                (String.format("%02d", month1) + "~" + String.format("%02d", month2)));
        String day = (day1 == day2 ? String.format("%02d", day1) :
                (String.format("%02d", day1) + "~" + String.format("%02d", day2)));

        String nightName = year + "-" + month + "-" + day;

        return nightName;
    }

    public void deleteNight(String name) {
        File nightsDir = new File(context.getFilesDir(), NIGHTS_DIR_NAME);
        File nightDir = new File(nightsDir, name);
        deleteRecursive(nightDir);
    }

    private void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

}
