package com.pfv.advancedreminder.ui.screens.add_new_reminder.form

import androidx.annotation.StringRes
import com.pfv.advancedreminder.constants.DaysOfWeek
import com.pfv.advancedreminder.ext.date.formatDateToTimeString
import com.pfv.advancedreminder.ext.date.isDifferanceMoreThanWeek
import com.pfv.advancedreminder.ext.date.setHourAndMinute
import java.util.Calendar
import java.util.Date

data class AddNewReminderScreenForm(

    val timesToRemind: Int = 1,
    val time: List<Date> = listOf(Calendar.getInstance().time),
    val startDate: Date? = null,
    val endDate: Date? = null,
    val selectableDaysOfWeekToRemind: List<DaysOfWeek> = DaysOfWeek.entries,
    val title: String = "",
    val description: String = "",
    val startTimeToRemind: Date = Calendar.getInstance().apply {
        setHourAndMinute(9, 0)
    }.time,
    val endTimeToRemind: Date = Calendar.getInstance().apply {
        setHourAndMinute(20, 0)
    }.time,
    @StringRes
    val titleError: Int? = null,
    @StringRes
    val selectTimeToRemindError: Int? = null,
    @StringRes
    val startEndTimeError: Int? = null

){
    fun countOfAdditionalTimesToRemind() = timesToRemind - time.size

    fun isAllFieldsValid() =
        (titleError == null) &&
                (selectTimeToRemindError == null) &&
                (startEndTimeError == null)
    fun hasTitleError() = titleError != null

    fun hasSelectTimeToRemindError() = selectTimeToRemindError != null

    fun hastStartEndTimeError() = startEndTimeError != null

    fun formattedTimeList() = time.map {
        it.formatDateToTimeString()
    }

    fun getTimeByIndex(index: Int) = time[index]

    fun needDisplaySelectSpecifiedDaysSection() = isDifferanceMoreThanWeek(
        start = startDate?.time ?: Calendar.getInstance().time.time,
        end = endDate?.time ?: Calendar.getInstance().time.time
    )
}
