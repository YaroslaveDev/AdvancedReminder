package com.pfv.advancedreminder.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pfv.advancedreminder.R
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_MESSAGE_PARAM
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_TITLE_PARAM
import kotlin.random.Random

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {

        val title = inputData.getString(NOTIFICATION_TITLE_PARAM)
        val message = inputData.getString(NOTIFICATION_MESSAGE_PARAM)

        showNotification(title, message)

        return Result.success()
    }

    private fun showNotification(title: String? = "", message: String? = "") {

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY_BASE_REMINDERS)
            .build()

        notificationManager.notify(Random.nextInt(), notification)

        // Create a summary notification for grouping
        val summaryNotification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Reminders")
            .setContentText("You have multiple reminders")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setGroup(GROUP_KEY_BASE_REMINDERS)
            .setGroupSummary(true)
            .build()

        notificationManager.notify(SUMMARY_NOTIFICATION_ID, summaryNotification)
    }

    companion object {
        private const val CHANNEL_ID = "notification_channel"
        private const val CHANNEL_NAME = "Notification Channel"
        private const val GROUP_KEY_BASE_REMINDERS = "base_reminders"
        private const val SUMMARY_NOTIFICATION_ID = 0
    }
}