package com.amvlabs.androidkotlinapps.warecover.utils

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import java.util.*

class CurrentDateAndTime {
    var date:String = ""
    var time:String = ""

     fun getInstance(currentDate:Date) {
        val dateFormat = SimpleDateFormat("MMM dd", Locale.ENGLISH)
        val timeFormat = SimpleDateFormat( "hh:mm a", Locale.ENGLISH)
        dateFormat.timeZone = TimeZone.getDefault()
        timeFormat.timeZone = TimeZone.getDefault()
         date = dateFormat.format(currentDate)
         time = timeFormat.format(currentDate)
    }
}