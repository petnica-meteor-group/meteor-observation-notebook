package rs.meteori.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by schutzekatze on 12/6/17.
 */

public class UIFont {

    private static Typeface font = null;

    public static void apply(Context context, TextView v) {
        if (font == null) {
            font = Typeface.createFromAsset(context.getAssets(), "fonts/PressStart2P-Regular.ttf");
        }
        v.setTypeface(font);
    }

}
