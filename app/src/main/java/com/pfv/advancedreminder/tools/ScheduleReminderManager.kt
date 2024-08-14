package com.pfv.advancedreminder.tools

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_MESSAGE_PARAM
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_TITLE_PARAM
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class ScheduleReminderManager {

    fun scheduleNotification(
        context: Context,
        date: Date,
        title: String,
        description: String
    ) {
        val intent = Intent(context.applicationContext, ReminderReceiver::class.java)
        intent.putExtra(NOTIFICATION_TITLE_PARAM, title)
        intent.putExtra(NOTIFICATION_MESSAGE_PARAM, description)
        val pendingIntent = PendingIntent.getBroadcast(
            context.applicationContext,
            Random.nextInt(),
            intent,
            PendingIntent.FLAG_MUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val selectedDate = Calendar.getInstance().apply {
            timeInMillis = date.time
        }

        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH)
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)
        val hour = selectedDate.get(Calendar.HOUR_OF_DAY)
        val minute = selectedDate.get(Calendar.MINUTE)
        val second = selectedDate.get(Calendar.SECOND)

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute, second)

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        Log.i("scheduled_task_info ==============>", "${title} - ${description}, ${calendar.time}")
    }
}