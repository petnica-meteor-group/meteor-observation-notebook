package petnicameteorgroup.visualobservationnotebook;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
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

    private static int CONFIRM_VIBRATE_DURATION = 100;
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

    private LockIntercepter lockIntercepter;
    private static PowerManager.WakeLock wakeLock;

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

        finishWakeLocker();
        wakeLock = null;
        lockIntercepter = new LockIntercepter();
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(lockIntercepter, screenStateFilter);
    }

    public boolean isLocked() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return activityManager.getLockTaskModeState() != ActivityManager.LOCK_TASK_MODE_NONE;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return activityManager.isInLockTaskMode();
        }

        return false;
    }

    public void finishWakeLocker(){
        if (wakeLock != null)
            wakeLock.release();
    }

    class LockIntercepter extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "TEST");
            wakeLock.acquire();

            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(context, NotebookActivity.class);
            PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
            alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,  10, pi);
        }

    }

    @Override
    protected void onResume() {
        if (!isLocked()) {
            startLockTask();
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        //vibrate(EXIT_VIBRATE_DURATION);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (isLocked()) {
            stopLockTask();
        }
        super.onDestroy();
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
