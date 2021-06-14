package com.highair.weatherforecastid.data.source.remote

import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.utils.asLocalDateTime
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
@JsonClass(generateAdapter = true)
data class WeatherDto(
    @Json(name = "cuaca")
    val name: String,
    @Json(name = "jamCuaca")
    val dateTime: String,
    @Json(name = "kodeCuaca")
    val code: Int,
    val humidity: Long,
    val tempC: Long,
    val tempF: Long
)

/**Mapper*/
fun WeatherDto.asDomainModel(regionId: Long) = Weather(
    id = 0,
    name = this.name,
    dateTime = this.dateTime.asLocalDateTime(),
    code = this.code,
    humidity = this.humidity,
    tempC = this.tempC,
    tempF = this.tempF,
    regionId = regionId,
)
