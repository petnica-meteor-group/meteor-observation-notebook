package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import petnicameteorgroup.meteorobservationnotebook.activities.ObservationResultsActivity;

/**
 * Created by vladi on 11/1/2017.
 */

public class NoteSaver {

    public static String NOTES_DIR = "notes";

    private File notesDir;

    public NoteSaver(Context context) {
         notesDir = new File(context.getFilesDir(),
                 ObservationResultsActivity.RESULTS_DIR + File.separator +
                       ObservationResultsActivity.getNightName() + File.separator +
                       NOTES_DIR
         );
         notesDir.mkdirs();
    }

    public void save(Bitmap bitmap, long timestamp) {
        File bitmapFile = new File(notesDir, Long.toString(timestamp));
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

}
