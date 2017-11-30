package petnicameteorgroup.meteorobservationnotebook.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by vladi on 11/1/2017.
 */

public class LockInterceptor extends BroadcastReceiver {

    private IntentFilter screenStateFilter;
    private Context context;
    private Class activity;

    public LockInterceptor(Context context) {
        screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);

        this.context = context;
        this.activity = context.getClass();
    }

    public void enable() {
        context.registerReceiver(this, screenStateFilter);
    }

    public void disable() {
        context.unregisterReceiver(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "TEST");
        wakeLock.acquire(20);

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, activity);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,  10, pi);
    }

}
