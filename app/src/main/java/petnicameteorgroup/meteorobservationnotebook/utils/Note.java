package petnicameteorgroup.meteorobservationnotebook.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by schutzekatze on 12/4/17.
 */

public class Note {

    private Bitmap bitmap;
    private long timestamp;

    public Note(Bitmap bitmap, long timestamp) {
        this.bitmap = bitmap;
        this.timestamp = timestamp;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Date getDate() {
        return new Date(timestamp);
    }

    public String getClockTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date(timestamp));
    }

}
