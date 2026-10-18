package com.drova.widget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import com.drova.widget.R;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.RemoteViews;

public class SpinService extends Service {
    private static final int[] FRAMES = {
        R.drawable.spin_00,
        R.drawable.spin_01,
        R.drawable.spin_02,
        R.drawable.spin_03,
        R.drawable.spin_04,
        R.drawable.spin_05,
        R.drawable.spin_06,
        R.drawable.spin_07,
        R.drawable.spin_08,
        R.drawable.spin_09,
        R.drawable.spin_10,
        R.drawable.spin_11,
        R.drawable.spin_12,
        R.drawable.spin_13,
        R.drawable.spin_14,
        R.drawable.spin_15,
        R.drawable.spin_16,
        R.drawable.spin_17,
        R.drawable.spin_18,
        R.drawable.spin_19,
        R.drawable.spin_20,
        R.drawable.spin_21,
        R.drawable.spin_22,
        R.drawable.spin_23,
    };
    private static final long FRAME_MS = 250;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private int index = 0;

    private final Runnable tick = new Runnable() {
        @Override public void run() {
            AppWidgetManager mgr = AppWidgetManager.getInstance(SpinService.this);
            int[] ids = mgr.getAppWidgetIds(new ComponentName(SpinService.this, SpinWidget.class));
            if (ids.length == 0) { stopSelf(); return; }
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
            views.setImageViewResource(R.id.spin_image, FRAMES[index]);
            mgr.partiallyUpdateAppWidget(ids, views);
            index = (index + 1) % FRAMES.length;
            handler.postDelayed(this, FRAME_MS);
        }
    };

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(tick);
        handler.post(tick);
        return START_STICKY;
    }

    @Override public void onDestroy() { handler.removeCallbacks(tick); super.onDestroy(); }
    @Override public IBinder onBind(Intent intent) { return null; }
}
