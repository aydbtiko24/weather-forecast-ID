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

