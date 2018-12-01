package rs.meteori.meteorobservationnotebook.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by schutzekatze on 12/3/17.
 */

public class Night implements Serializable {

    private String name;
    private File notesDir;
    private String[] notesFilenames;

    public Night(String name, File notesDir) {
        this.name = name;
        this.notesDir = notesDir;
        if (notesDir.exists()) {
            this.notesFilenames = notesDir.list();
            Arrays.sort(this.notesFilenames);
        } else
            this.notesFilenames = null;
    }

    public String getName() {
        return name;
    }
    public String getUIName() { return name.replace('~', '/'); }

    public Note getNote(int i) {
        if (i >= 0 && i < getNoteCount()) {
            return new Note(
                    notesDir.getAbsolutePath() + File.separator + notesFilenames[i],
                    Long.parseLong(notesFilenames[i].replace(".png", ""))
            );
        }
        return null;
    }

    public int getNoteCount() {
        if (notesFilenames != null) {
            return notesFilenames.length;
        }
        return 0;
    }

    public void addNote(Bitmap bitmap, long timestamp) {
        if (notesFilenames == null) {
            notesDir.mkdirs();
        }

        File bitmapFile = new File(notesDir, Long.toString(timestamp) + ".png");
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

        notesFilenames = notesDir.list();
        Arrays.sort(this.notesFilenames);
    }

}
