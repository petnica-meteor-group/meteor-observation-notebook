package petnicameteorgroup.meteorobservationnotebook;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by vladi on 10/24/2017.
 */

public class Notebook extends View {

    private static final float TOUCH_TOLERANCE = 1;

    public Bitmap getBitmap() { return bitmap; }
    public void enable() { enabled = true; }
    public void disable() { enabled = false; }

    private float lastX, lastY;
    private boolean enabled = false;

    protected Bitmap bitmap = null;
    protected Canvas canvas;

    protected Path path;
    protected Paint paint;

    public Notebook(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(14f);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    private void touchStart(float x, float y) {
        path.reset();
        path.moveTo(x, y);

        lastX = x;
        lastY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - lastX);
        float dy = Math.abs(y - lastY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.lineTo(x, y);
            lastX = x;
            lastY = y;
        }
    }

    private void touchUp() {
        path.lineTo(lastX, lastY);
        canvas.drawPath(path,  paint);
        path.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!enabled) return true;

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }

        return true;
    }

}
