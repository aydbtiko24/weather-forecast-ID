package com.highair.weatherforecastid.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */

fun Long.toStartDateTime(): Long {
    val startCalendar = Calendar.getInstance().apply {
        clear()
        timeInMillis = this@toStartDateTime
    }

    startCalendar[Calendar.HOUR_OF_DAY] = 0
    startCalendar[Calendar.MINUTE] = 0
    startCalendar[Calendar.SECOND] = 0
    return startCalendar.timeInMillis
}

fun Long.toEndDateTime(): Long {
    val endCalendar = Calendar.getInstance().apply {
        clear()
        timeInMillis = this@toEndDateTime
    }
    endCalendar[Calendar.HOUR_OF_DAY] = 23
    endCalendar[Calendar.MINUTE] = 59
    endCalendar[Calendar.SECOND] = 59
    return endCalendar.timeInMillis
}

fun Long.asDateString(): String {
    return SimpleDateFormat("EE, dd MMM", Locale.getDefault()).format(this)
}

fun Long.asTimeString(): String {
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this)
}

fun String.asLocalDateTime(): Long {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(this)?.time ?: 0L
}

fun findClosestWeatherTime(currentDateTime: Long): Long {

    val current = Calendar.getInstance().apply {
        timeInMillis = currentDateTime
    }

    val closestTime = when (current[Calendar.HOUR_OF_DAY]) {
        in 6..12 -> 6
        in 12..18 -> 12
        in 18..23 -> 18
        else -> 0
    }

    return Calendar.getInstance().apply {
        clear()
        timeInMillis = currentDateTime
        set(Calendar.HOUR_OF_DAY, closestTime)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
    }.timeInMillis
}
