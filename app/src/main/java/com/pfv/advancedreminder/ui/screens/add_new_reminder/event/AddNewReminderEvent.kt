package com.pfv.advancedreminder.ui.screens.add_new_reminder.event

import com.pfv.advancedreminder.constants.DaysOfWeek
import com.pfv.advancedreminder.ui.screens.add_new_reminder.constants.ReminderType
import java.util.Date

sealed class AddNewReminderEvent {

    data class ChangeReminderType(val type: ReminderType) : AddNewReminderEvent()
    object IncrementTimesToRemind : AddNewReminderEvent()
    object DecrementTimesToRemind : AddNewReminderEvent()
    data class AddNewTime(val date: Date) : AddNewReminderEvent()
    data class UpdateTimeByIndex(val date: Date, val index: Int) : AddNewReminderEvent()
    object ShowTimePicker_AddTime : AddNewReminderEvent()
    data class ShowTimePicker_EditTime(val index: Int, val time: Date) : AddNewReminderEvent()
    data class RemoveTimeByIndex(val index: Int) : AddNewReminderEvent()
    object AddNewDate : AddNewReminderEvent()
    data class UpdateDate(val start: Date?, val end: Date?) : AddNewReminderEvent()
    data class UpdateSelectableDayOfWeekToRemind(val day: DaysOfWeek) : AddNewReminderEvent()
    data class UpdateTitle(val text: String) : AddNewReminderEvent()
    data class UpdateDescription(val text: String) : AddNewReminderEvent()
    object AddNewReminderClick : AddNewReminderEvent()
}