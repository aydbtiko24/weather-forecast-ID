package com.highair.weatherforecastid.models

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
object Invalid {

    const val id = -1L

    val weather = Weather(
        id = id,
        name = "",
        dateTime = 0,
        code = 0,
        humidity = 0,
        tempC = 0,
        tempF = 0
    )

    val region = Region(
        id = id,
        province = "",
        city = "",
        district = ""
    )

}