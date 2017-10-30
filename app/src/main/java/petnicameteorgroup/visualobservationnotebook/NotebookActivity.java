package petnicameteorgroup.visualobservationnotebook;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class NotebookActivity extends AppCompatActivity {

    private static int SPECIAL_KEY_ONE = 0;
    private static int SPECIAL_KEY_TWO = 1;

    private static int CONFIRM_VIBRATE_DURATION = 200;
    private static int EXIT_VIBRATE_DURATION = 1500;

    private Vibrator vibrator;

    private void onSpecialKey(int key) {
        vibrate(CONFIRM_VIBRATE_DURATION);
        if (key == SPECIAL_KEY_ONE) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);
        } else if (key == SPECIAL_KEY_TWO) {

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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_notebook);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    protected void onStop() {
        vibrate(EXIT_VIBRATE_DURATION);
        super.onStop();
    }

    @Override
    protected void onResume() {
        startLockTask();
        super.onResume();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                onSpecialKey(SPECIAL_KEY_ONE);
                return true;
            case KeyEvent.KEYCODE_HOME:
                return true;
            default:
                return super.dispatchKeyEvent(event);
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
