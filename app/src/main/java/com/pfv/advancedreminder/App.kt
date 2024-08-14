package com.pfv.advancedreminder

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.pfv.advancedreminder.constants.NotificationConstants.CHANNEL_ID
import com.pfv.advancedreminder.constants.NotificationConstants.CHANNEL_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = this.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
    }
}