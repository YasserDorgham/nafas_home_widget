package com.usenafas.nafas_home_widget

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

            views.setTextViewText(
                R.id.widget_today,
                "Today: $todayCount"
            )

            views.setTextViewText(
                R.id.widget_target,
                "Target: $targetCount"
            )

            val logCigaretteIntent =
                HomeWidgetBackgroundIntent.getBroadcast(
                    context,
                    Uri.parse("nafaswidget://logcigarette")
                )

            views.setOnClickPendingIntent(
                R.id.widget_log_button,
                logCigaretteIntent
            )

            appWidgetManager.updateAppWidget(
                widgetId,
                views
            )
        }
    }
}
