package com.highair.weatherforecastid.models

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
data class Weather(
    val id: Long,
    val name: String,
    val dateTime: Long,
    val code: Int,
    val humidity: Long,
    val tempC: Long,
    val tempF: Long,
    val regionId: Long
)
