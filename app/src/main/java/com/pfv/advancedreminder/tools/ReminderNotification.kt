package com.pfv.advancedreminder.tools

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.NotificationConstants.CHANNEL_ID
import com.pfv.advancedreminder.constants.NotificationConstants.GROUP_KEY_BASE_REMINDERS
import com.pfv.advancedreminder.constants.NotificationConstants.SUMMARY_NOTIFICATION_ID
import kotlin.random.Random

class ReminderNotification(private val context: Context) {

    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun sendReminderNotification(title: String? = "", message: String? = "") {

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY_BASE_REMINDERS)
            .build()

        notificationManager.notify(Random.nextInt(), notification)

        val summaryNotification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Reminders")
            .setContentText("You have multiple reminders")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(GROUP_KEY_BASE_REMINDERS)
            .setGroupSummary(true)
            .build()

        notificationManager.notify(SUMMARY_NOTIFICATION_ID, summaryNotification)
    }
}