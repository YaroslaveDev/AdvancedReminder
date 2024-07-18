package com.pfv.advancedreminder.data.repository

import com.pfv.advancedreminder.data.dbo.BaseReminderDbo
import com.pfv.advancedreminder.data.local.room.dao.BaseRemindersDao
import javax.inject.Inject

class BaseRemindersRepository @Inject constructor(
    private val baseRemindersDao: BaseRemindersDao
) {

    fun getAllReminders() : List<BaseReminderDbo> = baseRemindersDao.getAllReminders()

    fun insertReminder(reminder: BaseReminderDbo) {
        baseRemindersDao.insertReminder(reminder = reminder)
    }
}