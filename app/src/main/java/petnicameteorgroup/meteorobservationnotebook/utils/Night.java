package petnicameteorgroup.meteorobservationnotebook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by schutzekatze on 12/3/17.
 */

public class Night implements Serializable {

    private String name;
    private File notesDir;

    private String[] notesFilenames;
    private int counter;

    public Night(String name, File notesDir) {
        this.name = name;
        this.notesDir = notesDir;
        reset();
    }

    public String getName() {
        return name;
    }

    public void reset() {
        notesFilenames = notesDir.list();
        counter = 0;
    }

    public Bitmap getNextNote() {
        if (counter == notesFilenames.length)
            return null;
        else
            return BitmapFactory.decodeFile(notesFilenames[counter++]);
    }

    public int getNoteCount() {
        return notesFilenames.length;
    }

    public void addNote(Bitmap note, long timestamp) {
        File bitmapFile = new File(notesDir, Long.toString(timestamp));
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(bitmapFile);
            note.compress(Bitmap.CompressFormat.PNG, 100, out);
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
