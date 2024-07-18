package com.pfv.advancedreminder.data.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pfv.advancedreminder.data.constants.RoomConstants.BaseRemindersTable
import java.util.Date

@Entity(tableName = BaseRemindersTable)
data class BaseReminderDbo(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val timesToRemindPerDay: Int,
    val startDate: Date,
    val endDate: Date,
    val timeListToRemind: List<Date>,
    val title: String,
    val description: String
)
