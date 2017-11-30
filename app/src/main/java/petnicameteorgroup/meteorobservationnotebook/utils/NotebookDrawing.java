package petnicameteorgroup.meteorobservationnotebook.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;

/**
 * Created by vladi on 11/10/2017.
 */

public class NotebookDrawing {

    private Bitmap bitmap;
    private Path path;
    private Canvas canvas;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}
