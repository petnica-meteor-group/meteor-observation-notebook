package petnicameteorgroup.visualobservationnotebook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by vladi on 10/31/2017.
 */

public class TestNotebook extends Notebook {

    public TestNotebook(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawPath(path,  paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean returnValue = super.onTouchEvent(event);
        invalidate();
        return returnValue;
    }
}
