package com.highair.weatherforecastid.data.source.remote

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
object ApiConstants {
    const val BASE_URL = "https://ibnux.github.io/BMKG-importer/"
    const val regionPath = "region"
    const val WEATHER_PATH = "cuaca/{$regionPath}.json"
    const val REGION_PATH = "cuaca/wilayah.json"
}
