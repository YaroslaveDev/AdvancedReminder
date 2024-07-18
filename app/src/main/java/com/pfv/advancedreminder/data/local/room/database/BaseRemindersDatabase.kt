package com.pfv.advancedreminder.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pfv.advancedreminder.data.dbo.BaseReminderDbo
import com.pfv.advancedreminder.data.local.room.dao.BaseRemindersDao

@Database(entities = [BaseReminderDbo::class], version = 1)
abstract class BaseRemindersDatabase : RoomDatabase() {

    abstract fun baseRemindersDao(): BaseRemindersDao
}