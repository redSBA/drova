package com.drova.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class SpinWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context ctx, AppWidgetManager mgr, int[] ids) {
        ctx.startService(new Intent(ctx, SpinService.class));
    }

    @Override
    public void onDisabled(Context ctx) {
        ctx.stopService(new Intent(ctx, SpinService.class));
    }
}
