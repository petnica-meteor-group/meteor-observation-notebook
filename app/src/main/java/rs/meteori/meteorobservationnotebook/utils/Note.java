package rs.meteori.meteorobservationnotebook.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by schutzekatze on 12/4/17.
 */

public class Note implements Serializable {

    private String bitmapPath;
    private long timestamp;

    public Note(String bitmapPath, long timestamp) {
        this.bitmapPath = bitmapPath;
        this.timestamp = timestamp;
    }

    public Bitmap getBitmap() {
        return BitmapFactory.decodeFile(bitmapPath);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Date getDate() {
        return new Date(timestamp);
    }

    public String getUTCClockTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(new Date(timestamp)) + " UTC";
    }

}
