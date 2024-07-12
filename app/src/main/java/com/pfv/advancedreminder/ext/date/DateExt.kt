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
