package petnicameteorgroup.meteorobservationnotebook;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by vladi on 11/1/2017.
 */

public class NoteSaver {

    private static String NOTES_DIR = "Notes";

    private File notesDir;
    private File currentNotesDir;

    public NoteSaver(Context context) {
         notesDir = new File(context.getFilesDir(), NOTES_DIR);
         if (!notesDir.exists())
             notesDir.mkdir();

         currentNotesDir = new File(notesDir, getNightName());
         if (!currentNotesDir.exists())
            currentNotesDir.mkdir();
    }

    public void save(Bitmap bitmap, long timestamp) {
        File bitmapFile = new File(currentNotesDir, Long.toString(timestamp));
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getNightName() {
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
