package com.pfv.advancedreminder.tools

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import com.pfv.advancedreminder.constants.NotificationConstants.CHANNEL_ID
import com.pfv.advancedreminder.constants.NotificationConstants.CHANNEL_NAME

class NotificationChannelManager(val context: Context) {

    val notificationChannel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_HIGH
    )

    val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    init {
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

