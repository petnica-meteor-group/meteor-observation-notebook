package rs.meteori.meteorobservationnotebook.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.media.AudioManager;
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

import rs.meteori.meteorobservationnotebook.utils.LockInterceptor;
import rs.meteori.meteorobservationnotebook.utils.Night;
import rs.meteori.meteorobservationnotebook.utils.Nightkeeper;
import rs.meteori.meteorobservationnotebook.utils.Notebook;
import rs.meteori.meteorobservationnotebook.R;

public class ObservationActivity extends AppCompatActivity {

    protected static int SPECIAL_KEY_ONE = 0;
    protected static int SPECIAL_KEY_TWO = 1;

    protected static int CONFIRM_VIBRATE_DURATION = 100;
    protected static int EXIT_VIBRATE_DURATION = 800;

    protected static int PIN_CHECK_PERIOD_INITIAL = 12000;
    protected static int PIN_CHECK_PERIOD = 4000;

    private Vibrator vibrator;
    private ActivityManager activityManager;
    private Handler pinHandler = new Handler();

    protected Notebook notebook;
    protected Night night;
    protected long lastTimestamp = -1;

    private LockInterceptor lockInterceptor;

    private OrientationEventListener orientationChangeListener;
    private int orientation;

    private int originalVolumeRing = 0;
    private int originalVolumeMusic = 0;

    private int originalRingerMode = AudioManager.RINGER_MODE_SILENT;
    private boolean originalSystemMute = false;
    private boolean originalNotificationMute = false;
    private boolean originalRingMute = false;
    private boolean originalMusicMute = false;

    protected synchronized void onSpecialKey(int key) {
        if (key == SPECIAL_KEY_ONE) {
            if (!notebook.isBlank()) {
                night.addNote(notebook.getDrawing().getBitmap(), lastTimestamp);
            }
            lastTimestamp = System.currentTimeMillis();
            notebook.clear();
            notebook.enable();
            vibrate(CONFIRM_VIBRATE_DURATION);
        } else if (key == SPECIAL_KEY_TWO && notebook.isEnabled()) {
            if (!notebook.isBlank()) {
                night.addNote(notebook.getDrawing().getBitmap(), lastTimestamp);
            }
            lastTimestamp = -1;
            notebook.disable();
            notebook.clear();
            vibrate(CONFIRM_VIBRATE_DURATION);
        }
    }

    protected void vibrate(int duration) {
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
        setContentView(R.layout.activity_observation);
        notebook = (Notebook) findViewById(R.id.notebook);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        night = new Nightkeeper(this).getThisNight();

        orientationChangeListener = new OrientationEventListener(
                getApplicationContext(), SensorManager.SENSOR_DELAY_NORMAL) {

            @Override
            public void onOrientationChanged(int orientation) {
                synchronized (ObservationActivity.this) {
                    ObservationActivity.this.orientation = orientation;
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

        //AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //originalVolumeRing = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        //originalVolumeMusic = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

        //originalRingerMode = audioManager.getRingerMode();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //originalSystemMute = audioManager.isStreamMute(audioManager.STREAM_SYSTEM);
            //originalNotificationMute = audioManager.isStreamMute(audioManager.STREAM_NOTIFICATION);
            //originalRingMute = audioManager.isStreamMute(audioManager.STREAM_RING);
            //originalMusicMute = audioManager.isStreamMute(audioManager.STREAM_MUSIC);
        //}
        //audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        //audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        //audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        //audioManager.setStreamMute(AudioManager.STREAM_RING, true);
        //audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
    }

    @Override
    public void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        activityManager.moveTaskToFront(getTaskId(), 0);
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
        //AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        //audioManager.setStreamVolume(AudioManager.STREAM_RING, originalVolumeRing, 0);
        //audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolumeMusic, 0);

        //audioManager.setRingerMode(originalRingerMode);
        //audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, originalSystemMute);
        //audioManager.setStreamMute(AudioManager.STREAM_NOTIFICATION, originalNotificationMute);
        //audioManager.setStreamMute(AudioManager.STREAM_RING, originalRingMute);
        //audioManager.setStreamMute(AudioManager.STREAM_MUSIC, originalMusicMute);


        vibrate(EXIT_VIBRATE_DURATION);

        if (isPinned()) {
            stopLockTask();
        }

        lockInterceptor.disable();

        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                onSpecialKey(SPECIAL_KEY_ONE);
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                onSpecialKey(SPECIAL_KEY_TWO);
                return true;
            case KeyEvent.KEYCODE_HOME:
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onBackPressed() {}

}
