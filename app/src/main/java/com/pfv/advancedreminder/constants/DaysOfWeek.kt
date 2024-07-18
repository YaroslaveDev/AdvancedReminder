package com.pfv.advancedreminder.constants

import com.pfv.advancedreminder.R
import java.util.Calendar

enum class DaysOfWeek(val day: Int, val text: Int){

    SUNDAY(Calendar.SUNDAY, R.string.sunday),
    MONDAY(Calendar.MONDAY, R.string.monday),
    TUESDAY(Calendar.TUESDAY, R.string.tuesday),
    WEDNESDAY(Calendar.WEDNESDAY, R.string.wednesday),
    THURSDAY(Calendar.THURSDAY, R.string.thursday),
    FRIDAY(Calendar.FRIDAY, R.string.friday),
    SATURDAY(Calendar.SATURDAY, R.string.saturday),
}