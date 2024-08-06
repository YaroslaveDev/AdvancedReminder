package com.pfv.advancedreminder.ext.date

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.getDayOfWeekShort(): String {
    val sdf = SimpleDateFormat("EE", Locale.getDefault())
    return sdf.format(this)
}

fun Date.getDayOfMonth(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.getNext7Dates(): List<Date> {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dates = mutableListOf<Date>()

    for (i in 1..7) {
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        dates.add(calendar.time)
    }

    return dates
}

fun Date.getPrevious7Dates(): List<Date> {
    val calendar = Calendar.getInstance()
    calendar.time = this
    val dates = mutableListOf<Date>()

    for (i in 1..7) {
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        dates.add(calendar.time)
    }

    return dates
}

fun Date.toFormattedString(): String {
    val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatDateToTimeString(): String {
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(this)
}

fun Long.longToDate(): Date {
    return Date(this)
}

fun Date.toHourMinutePair(): Pair<Int, Int> {
    val calendar = Calendar.getInstance().apply {
        time = this@toHourMinutePair
    }
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return Pair(hour, minute)
}

fun isDifferanceMoreThanWeek(start: Long, end: Long) : Boolean {

    val daySec = 86_400

    val diffMillis = end - start
    val diffSec = diffMillis / 1000

    return (diffSec / daySec) >= 7
}

fun Calendar.setHourAndMinute(hour: Int, minute: Int) {
    set(Calendar.HOUR_OF_DAY, hour)
    set(Calendar.MINUTE, minute)
}

fun Date.isLargerThan(endTime: Date) = this.time > endTime.time
fun Date.isEquals(endTime: Date) = this.toHourMinutePair() == endTime.toHourMinutePair()