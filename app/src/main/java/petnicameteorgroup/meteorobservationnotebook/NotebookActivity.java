package petnicameteorgroup.meteorobservationnotebook;

import android.app.ActivityManager;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.Window;
import android.view.WindowManager;

public class NotebookActivity extends AppCompatActivity {

    private static int SPECIAL_KEY_ONE = 0;
    private static int SPECIAL_KEY_TWO = 1;

    private static int CONFIRM_VIBRATE_DURATION = 100;
    private static int EXIT_VIBRATE_DURATION = 800;

    private static int PIN_CHECK_PERIOD_INITIAL = 10000;
    private static int PIN_CHECK_PERIOD = 4000;

    private Vibrator vibrator;
    private ActivityManager activityManager;
    private Handler pinHandler = new Handler();

    private Notebook notebook;
    private long lastTimestamp = -1;
    private boolean writing = false;

    private NoteSaver noteSaver;
    private LockInterceptor lockInterceptor;

    private OrientationEventListener orientationChangeListener;
    private int orientation;

    private void onSpecialKey(int key) {
        if (key == SPECIAL_KEY_ONE) {
            lastTimestamp = System.currentTimeMillis();
            notebook.enable();
            writing = true;
            vibrate(CONFIRM_VIBRATE_DURATION);
        } else if (key == SPECIAL_KEY_TWO && writing) {
            writing = false;
            notebook.disable();
            noteSaver.save(notebook.getBitmap(), lastTimestamp);
            vibrate(CONFIRM_VIBRATE_DURATION);
        }
    }

    private void vibrate(int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(duration);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int flags =
                WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        getWindow().setFlags(flags, flags);
        setContentView(R.layout.activity_notebook);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        notebook = (Notebook) findViewById(R.id.notebook);
        noteSaver = new NoteSaver(this);

        orientationChangeListener = new OrientationEventListener(
                getApplicationContext(), SensorManager.SENSOR_DELAY_NORMAL) {

            @Override
            public void onOrientationChanged(int orientation) {
                synchronized (NotebookActivity.this) {
                    NotebookActivity.this.orientation = orientation;
                }
            }

        };

        lockInterceptor = new LockInterceptor(this);
        lockInterceptor.enable();

        if (!isPinned()) {
            startLockTask();
        }

        pinHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isPinned()) {
                    pinHandler.postDelayed(this, PIN_CHECK_PERIOD);
                } else {
                    finish();
                }
            }
        }, PIN_CHECK_PERIOD_INITIAL);
    }

    @Override
    protected void onStart() {
        super.onStart();
        orientationChangeListener.enable();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orientationChangeListener.disable();
    }

    private boolean isPinned() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return activityManager.getLockTaskModeState() != ActivityManager.LOCK_TASK_MODE_NONE;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return activityManager.isInLockTaskMode();
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        vibrate(EXIT_VIBRATE_DURATION);

        lockInterceptor.disable();

        if (isPinned()) {
            stopLockTask();
        }

        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                onSpecialKey(SPECIAL_KEY_ONE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                onSpecialKey(SPECIAL_KEY_TWO);
                return true;
            case KeyEvent.KEYCODE_HOME:
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
    }

    @Override
    public void onBackPressed() {}

}
