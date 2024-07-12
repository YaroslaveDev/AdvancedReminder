package com.pfv.advancedreminder.tools

import java.util.Calendar
import java.util.Date

fun getDatesFromCurrentDateRange(): List<Date> {
    val calendar = Calendar.getInstance()
    val dates = mutableListOf<Date>()

    // Get dates from -7 to +7 days relative to the current date
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    for (i in 0..14) {
        dates.add(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return dates
}

