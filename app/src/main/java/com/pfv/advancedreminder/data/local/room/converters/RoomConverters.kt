package com.pfv.advancedreminder.data.local.room.converters

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

class ListConverters {
    @TypeConverter
    fun fromTimestampList(value: String?): List<Date>? {
        return value?.split(",")?.map { Date(it.toLong()) }
    }

    @TypeConverter
    fun dateListToTimestamp(dates: List<Date>?): String? {
        return dates?.joinToString(",") { it.time.toString() }
    }

    @TypeConverter
    fun fromIntList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }

    @TypeConverter
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }
}
