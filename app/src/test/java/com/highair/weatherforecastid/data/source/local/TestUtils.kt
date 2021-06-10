package com.highair.weatherforecastid.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */

fun buildTestDb(): AppDatabase =
    Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    ).allowMainThreadQueries()
        .build()

internal val weatherEntities = listOf(
    WeatherEntity(
        id = 1,
        name = "Cerah Berawan",
        dateTime = 1623258000000,
        code = 1,
        humidity = 70,
        tempC = 24,
        tempF = 75
    ),
    WeatherEntity(
        id = 2,
        name = "Cerah Berawan",
        dateTime = 1623279600000,
        code = 1,
        humidity = 70,
        tempC = 32,
        tempF = 90
    ),
    WeatherEntity(
        id = 3,
        name = "Berawan",
        dateTime = 1623301200000,
        code = 3,
        humidity = 95,
        tempC = 24,
        tempF = 75
    ),
    WeatherEntity(
        id = 4,
        name = "Berawan",
        dateTime = 1623322800000,
        code = 3,
        humidity = 95,
        tempC = 23,
        tempF = 73
    ),

    WeatherEntity(
        id = 5,
        name = "Cerah Berawan",
        dateTime = 1623344400000,
        code = 1,
        humidity = 70,
        tempC = 23,
        tempF = 73
    ),
    WeatherEntity(
        id = 6,
        name = "Hujan Ringan",
        dateTime = 1623366000000,
        code = 60,
        humidity = 70,
        tempC = 31,
        tempF = 88
    ),
    WeatherEntity(
        id = 7,
        name = "Berawan",
        dateTime = 1623387600000,
        code = 3,
        humidity = 95,
        tempC = 24,
        tempF = 75
    ),
    WeatherEntity(
        id = 8,
        name = "Cerah",
        dateTime = 1623409200000,
        code = 0,
        humidity = 85,
        tempC = 23,
        tempF = 73
    ),

    WeatherEntity(
        id = 9,
        name = "Cerah",
        dateTime = 1623430800000,
        code = 0,
        humidity = 70,
        tempC = 24,
        tempF = 75
    ),
    WeatherEntity(
        id = 10,
        name = "Cerah",
        dateTime = 1623452400000,
        code = 0,
        humidity = 65,
        tempC = 32,
        tempF = 90
    ),
    WeatherEntity(
        id = 11,
        name = "Cerah Berawan",
        dateTime = 1623474000000,
        code = 1,
        humidity = 80,
        tempC = 26,
        tempF = 79
    ),
    WeatherEntity(
        id = 12,
        name = "Cerah Berawan",
        dateTime = 1623495600000,
        code = 1,
        humidity = 90,
        tempC = 23,
        tempF = 73
    )
)
