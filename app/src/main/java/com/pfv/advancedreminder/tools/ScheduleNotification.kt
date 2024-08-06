package com.pfv.advancedreminder.tools

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_MESSAGE_PARAM
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_TITLE_PARAM
import com.pfv.advancedreminder.worker.NotificationWorker
import java.util.concurrent.TimeUnit

fun scheduleNotification(context: Context, delayMinutes: Long, title: String, message: String) {

    Log.i("ScheduleReminder", "Scheduled notification with title: $title, description: $message, delay: $delayMinutes minutes")

    val inputData = Data.Builder()
        .putString(NOTIFICATION_TITLE_PARAM, title)
        .putString(NOTIFICATION_MESSAGE_PARAM, message)
        .build()


    val workRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
        .setInitialDelay(delayMinutes, TimeUnit.MINUTES)
        .setInputData(inputData)
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}