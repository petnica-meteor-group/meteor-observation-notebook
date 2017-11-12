package petnicameteorgroup.meteorobservationnotebook;

import android.graphics.Bitmap;
import android.graphics.Path;

/**
 * Created by vladi on 11/10/2017.
 */

public class NotebookData {

    private Bitmap bitmap;
    private Path path;

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
}
