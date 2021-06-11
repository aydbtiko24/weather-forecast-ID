package com.highair.weatherforecastid.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
@Database(
    entities = [
        WeatherEntity::class,
        RegionEntity::class
    ],
    version = AppDatabase.version
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
    abstract fun regionDao(): RegionDao

    companion object {

        private const val name = "weather.db"
        internal const val version = 1

        fun create(
            @ApplicationContext context: Context
        ): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                name
            ).build()
        }
    }
}
