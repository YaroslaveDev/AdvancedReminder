package com.pfv.advancedreminder.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pfv.advancedreminder.data.dbo.BaseReminderDbo

@Dao
interface BaseRemindersDao {

    @Query("SELECT * FROM BASE_REMINDERS_TABLE")
    fun getAllReminders() : List<BaseReminderDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReminder(reminder: BaseReminderDbo)
}