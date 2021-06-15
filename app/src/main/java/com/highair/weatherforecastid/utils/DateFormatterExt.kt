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
    startCalendar[Calendar.MILLISECOND] = 0
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
    endCalendar[Calendar.MILLISECOND] = 59
    return endCalendar.timeInMillis
}

private const val labelFormat = "EE, dd MMM"

fun Long.asDateString(): String {
    return SimpleDateFormat(labelFormat, Locale.getDefault()).format(this)
}

fun Long.asTimeString(): String {

    val currentTime = SimpleDateFormat(
        "HH:mm",
        Locale.getDefault()
    ).format(this)

    val nextTime = when (currentTime) {
        "00:00" -> "06:00"
        "06:00" -> "12:00"
        "12:00" -> "18:00"
        else -> "23:59"
    }

    return "$currentTime - $nextTime"
}

fun String.asLocalDateTime(): Long {
    return SimpleDateFormat(
        "yyyy-MM-dd HH:mm:ss", Locale.getDefault()
    ).parse(this)?.time ?: 0L
}

fun String.asSelectedLocalDateTime(): Long {

    val date = SimpleDateFormat(
        labelFormat, Locale.getDefault()
    ).parse(this)?.time ?: 0L

    val currentCalendar = Calendar.getInstance()

    return Calendar.getInstance().apply {
        timeInMillis = date
        set(Calendar.YEAR, currentCalendar[Calendar.YEAR])
    }.timeInMillis

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
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}

fun Long.isCurrentWeather(
    currentDateTime: Long = System.currentTimeMillis()
): Boolean {
    return this == findClosestWeatherTime(
        currentDateTime
    )
}
