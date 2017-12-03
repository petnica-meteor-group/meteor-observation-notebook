package petnicameteorgroup.meteorobservationnotebook.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import petnicameteorgroup.meteorobservationnotebook.utils.Notebook;

/**
 * Created by vladi on 10/31/2017.
 */

public class NotebookTest extends Notebook {

    public NotebookTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.WHITE);
    }

    @Override
    public void clear() {
        super.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(drawing.getBitmap(), 0, 0, paint);
        canvas.drawPath(drawing.getPath(),  paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean returnValue = super.onTouchEvent(event);
        invalidate();
        return returnValue;
    }

}
