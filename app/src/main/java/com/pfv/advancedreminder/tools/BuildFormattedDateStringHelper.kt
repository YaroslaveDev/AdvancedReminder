package com.pfv.advancedreminder.tools

import com.pfv.advancedreminder.ext.date.toFormattedString
import java.util.Calendar
import java.util.Date

fun buildFormattedDateStringHelper(startDate: Date?, endDate: Date?) : String {

    return if (startDate == endDate){
        ((startDate ?: endDate) ?: Calendar.getInstance().time).toFormattedString()
    }else{
        ((startDate ?: endDate) ?: Calendar.getInstance().time).toFormattedString() + " - " +
                ((endDate ?: startDate) ?: Calendar.getInstance().time).toFormattedString()
    }
}