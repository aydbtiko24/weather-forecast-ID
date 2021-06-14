package com.highair.weatherforecastid.utils

import com.highair.weatherforecastid.R

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */

fun Int.asWeatherIconId() = when (this) {
    0 -> R.drawable.clear
    1 -> R.drawable.partly_cloudy
    2 -> R.drawable.partly_cloudy
    3 -> R.drawable.mostly_cloudy
    4 -> R.drawable.overcast
    5 -> R.drawable.haze
    6 -> R.drawable.smoke
    45 -> R.drawable.fog
    60 -> R.drawable.light_rain
    61 -> R.drawable.rain
    63 -> R.drawable.heavy_rain
    80 -> R.drawable.isolated_shower
    95 -> R.drawable.thunderstorms
    97 -> R.drawable.thunderstorms1
    else -> R.drawable.na
}

fun Int.asWeatherAnimId() = when (this) {
    0 -> R.raw.anim_clear
    1 -> R.raw.anim_partly_cloudy
    2 -> R.raw.anim_partly_cloudy
    3 -> R.raw.anim_mostly_cloudy
    4 -> R.raw.anim_overcast
    5 -> R.raw.anim_haze_smoke
    6 -> R.raw.anim_haze_smoke
    45 -> R.raw.anim_fog
    60 -> R.raw.anim_rainy
    61 -> R.raw.anim_rainy
    63 -> R.raw.anim_rainy
    80 -> R.raw.anim_isolated_shower
    95 -> R.raw.anim_thunderstorms
    97 -> R.raw.anim_thunderstorms
    else -> R.raw.anim_clear
}
