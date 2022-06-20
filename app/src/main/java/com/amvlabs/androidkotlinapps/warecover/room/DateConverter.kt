package com.amvlabs.androidkotlinapps.warecover.room

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun dateToLong(date:Date):Long = date.time
    @TypeConverter
    fun longToDate(long: Long):Date = Date(long)
}