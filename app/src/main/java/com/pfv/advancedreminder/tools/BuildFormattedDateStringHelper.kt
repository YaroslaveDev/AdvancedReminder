package com.pfv.advancedreminder.tools

import com.pfv.advancedreminder.ext.date.toFormattedString
import java.util.Date

fun buildFormattedDateStringHelper(startDate: Date, endDate: Date) : String {

    return if (startDate == endDate){
        startDate.toFormattedString()
    }else{
        startDate.toFormattedString() + " - " + endDate.toFormattedString()
    }
}