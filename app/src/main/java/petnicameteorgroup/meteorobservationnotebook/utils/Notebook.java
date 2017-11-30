package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import petnicameteorgroup.meteorobservationnotebook.R;

/**
 * Created by vladi on 10/24/2017.
 */

public class Notebook extends View {

    private static final float TOUCH_TOLERANCE = 1;

    public void enable() { enabled = true; }
    public void disable() { enabled = false; }
    public boolean isEnabled() { return enabled; }

    public void setDrawing(NotebookDrawing drawing) { this.drawing = drawing; }
    public NotebookDrawing getDrawing() { return drawing; }

    private float lastX, lastY;
    private boolean enabled = false;

    protected NotebookDrawing drawing = null;
    protected Paint paint;

    public void clear() {
        drawing.setBitmap(Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888));
        drawing.setCanvas(new Canvas(drawing.getBitmap()));

        drawing.getPath().reset();
    }

    public Notebook(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(getResources().getColor(R.color.vantablack));
        setKeepScreenOn(true);

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

        if (drawing == null) {
            drawing = new NotebookDrawing();

            drawing.setPath(new Path());
            clear();
        }
    }

    private void touchStart(float x, float y) {
        drawing.getPath().reset();
        drawing.getPath().moveTo(x, y);

        lastX = x;
        lastY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - lastX);
        float dy = Math.abs(y - lastY);

        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            drawing.getPath().lineTo(x, y);
            lastX = x;
            lastY = y;
        }
    }

    private void touchUp() {
        drawing.getPath().lineTo(lastX, lastY);
        drawing.getCanvas().drawPath(drawing.getPath(),  paint);
        drawing.getPath().reset();
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
