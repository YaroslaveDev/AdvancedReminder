package com.pfv.advancedreminder.tools

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_MESSAGE_PARAM
import com.pfv.advancedreminder.constants.GlobalAppConstants.NOTIFICATION_TITLE_PARAM

class ReminderReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val scheduleNotificationService = context?.let { ReminderNotification(it) }
        val title: String? = intent?.getStringExtra(NOTIFICATION_TITLE_PARAM)
        val description: String? = intent?.getStringExtra(NOTIFICATION_MESSAGE_PARAM)
        scheduleNotificationService?.sendReminderNotification(title, description)
    }
}