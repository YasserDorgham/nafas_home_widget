package com.usenafas.nafas_home_widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.net.Uri
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetBackgroundIntent
import es.antonborri.home_widget.HomeWidgetProvider

class NafasWidgetProvider : HomeWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        widgetData: android.content.SharedPreferences
    ) {
        appWidgetIds.forEach { widgetId ->

            val todayCount = widgetData.getInt("todayCount", 0)
            val targetCount = widgetData.getInt("targetCount", 0)

            val views = RemoteViews(
                context.packageName,
                R.layout.nafas_widget
            )
val isLoading = widgetData.getBoolean("isLoading", false)

views.setViewVisibility(
    R.id.widget_loading,
    if (isLoading) android.view.View.VISIBLE else android.view.View.GONE
)

views.setTextViewText(
    R.id.widget_log_button,
    if (isLoading) "" else "+"
)

views.setBoolean(
    R.id.widget_log_button,
    "setEnabled",
    !isLoading
)

views.setContentDescription(
    R.id.widget_log_button,
    if (isLoading) "Saving cigarette" else "Log one cigarette"
)
            views.setTextViewText(
                R.id.widget_today,
                "Today: $todayCount"
            )

            views.setTextViewText(
                R.id.widget_target,
                "Target: $targetCount"
            )

            // + button: log a cigarette.
            val logCigaretteIntent =
                HomeWidgetBackgroundIntent.getBroadcast(
                    context,
                    Uri.parse("nafaswidget://logcigarette")
                )

            views.setOnClickPendingIntent(
                R.id.widget_log_button,
                logCigaretteIntent
            )

            // Everywhere else: open Nafas.
            val launchIntent =
                context.packageManager.getLaunchIntentForPackage(
                    context.packageName
                )

            if (launchIntent != null) {
                val openAppIntent = PendingIntent.getActivity(
                    context,
                    widgetId,
                    launchIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.widget_root,
                    openAppIntent
                )
            }

            appWidgetManager.updateAppWidget(
                widgetId,
                views
            )
        }
    }
}
