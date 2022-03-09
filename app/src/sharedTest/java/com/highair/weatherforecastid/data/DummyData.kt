package com.highair.weatherforecastid.data

import com.highair.weatherforecastid.data.source.local.RegionEntity
import com.highair.weatherforecastid.data.source.local.WeatherEntity

internal val weatherEntities = listOf(
    WeatherEntity(
        id = 1,
        name = "Cerah Berawan",
        dateTime = 1654794000000,
        code = 1,
        humidity = 70,
        tempC = 24,
        tempF = 75,
        regionId = 501397
    ),
    WeatherEntity(
        id = 2,
        name = "Cerah Berawan",
        dateTime = 1654815600000,
        code = 1,
        humidity = 70,
        tempC = 32,
        tempF = 90,
        regionId = 501397
    ),
    WeatherEntity(
        id = 3,
        name = "Berawan",
        dateTime = 1654837200000,
        code = 3,
        humidity = 95,
        tempC = 24,
        tempF = 75,
        regionId = 501397
    ),
    WeatherEntity(
        id = 4,
        name = "Berawan",
        dateTime = 1654858800000,
        code = 3,
        humidity = 95,
        tempC = 23,
        tempF = 73,
        regionId = 501397
    ),

    WeatherEntity(
        id = 5,
        name = "Cerah Berawan",
        dateTime = 1654880400000,
        code = 1,
        humidity = 70,
        tempC = 23,
        tempF = 73,
        regionId = 501397
    ),
    WeatherEntity(
        id = 6,
        name = "Hujan Ringan",
        dateTime = 1654902000000,
        code = 60,
        humidity = 70,
        tempC = 31,
        tempF = 88,
        regionId = 501397
    ),
    WeatherEntity(
        id = 7,
        name = "Berawan",
        dateTime = 1654923600000,
        code = 3,
        humidity = 95,
        tempC = 24,
        tempF = 75,
        regionId = 501397
    ),
    WeatherEntity(
        id = 8,
        name = "Cerah",
        dateTime = 1654945200000,
        code = 0,
        humidity = 85,
        tempC = 23,
        tempF = 73,
        regionId = 501397
    ),

    WeatherEntity(
        id = 9,
        name = "Cerah",
        dateTime = 1654966800000,
        code = 0,
        humidity = 70,
        tempC = 24,
        tempF = 75,
        regionId = 501397
    ),
    WeatherEntity(
        id = 10,
        name = "Cerah",
        dateTime = 1654988400000,
        code = 0,
        humidity = 65,
        tempC = 32,
        tempF = 90,
        regionId = 501397
    ),
    WeatherEntity(
        id = 11,
        name = "Cerah Berawan",
        dateTime = 1655010000000,
        code = 1,
        humidity = 80,
        tempC = 26,
        tempF = 79,
        regionId = 501397
    ),
    WeatherEntity(
        id = 12,
        name = "Cerah Berawan",
        dateTime = 1655031600000,
        code = 1,
        humidity = 90,
        tempC = 23,
        tempF = 73,
        regionId = 501397
    )
)
internal val regionEntities = listOf(
    RegionEntity(
        id = 501397,
        province = "Aceh",
        city = "Kota Banda Aceh",
        district = "Banda Aceh"
    ),
    RegionEntity(
        id = 501162,
        province = "Bali",
        city = "Kab. Karangasem",
        district = "Amplapura"
    ),
    RegionEntity(
        id = 5002216,
        province = "SumateraUtara",
        city = "Kab. Nias Barat",
        district = "Lahomi"
    ),
)