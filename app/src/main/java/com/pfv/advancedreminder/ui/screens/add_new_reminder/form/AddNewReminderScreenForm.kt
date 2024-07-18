package com.pfv.advancedreminder.ui.screens.add_new_reminder.form

import androidx.annotation.StringRes
import com.pfv.advancedreminder.constants.DaysOfWeek
import com.pfv.advancedreminder.ext.date.formatDateToTimeString
import com.pfv.advancedreminder.ext.date.isDifferanceMoreThanWeek
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
    @StringRes
    val titleError: Int? = null,
    @StringRes
    val selectTimeToRemindError: Int? = null

){

    fun countOfAdditionalTimesToRemind() = timesToRemind - time.size

    fun isAllFieldsValid() =
        (titleError == null) &&
                (selectTimeToRemindError == null)
    fun hasTitleError() = titleError != null

    fun hasSelectTimeToRemindError() = selectTimeToRemindError != null

    fun formattedTimeList() = time.map {
        it.formatDateToTimeString()
    }

    fun getTimeByIndex(index: Int) = time[index]

    fun needDisplaySelectSpecifiedDaysSection() = isDifferanceMoreThanWeek(
        start = startDate?.time ?: Calendar.getInstance().time.time,
        end = endDate?.time ?: Calendar.getInstance().time.time
    )
}
