package com.pfv.advancedreminder.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pfv.advancedreminder.data.dbo.BaseReminderDbo
import com.pfv.advancedreminder.data.local.room.converters.Converters
import com.pfv.advancedreminder.data.local.room.converters.ListConverters
import com.pfv.advancedreminder.data.local.room.dao.BaseRemindersDao

@Database(entities = [BaseReminderDbo::class], version = 1)
@TypeConverters(Converters::class, ListConverters::class)
abstract class BaseRemindersDatabase : RoomDatabase() {

    abstract fun baseRemindersDao(): BaseRemindersDao
}