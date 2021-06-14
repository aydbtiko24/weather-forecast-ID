package com.highair.weatherforecastid.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
@Dao
interface WeatherDao {

    @Transaction
    suspend fun addWeathers(weatherEntities: List<WeatherEntity>) {
        clear()
        insertWeathers(weatherEntities)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeathers(weatherEntities: List<WeatherEntity>)

    @Query("SELECT * FROM weather")
    fun getWeathers(): Flow<List<WeatherEntity>>

    @Query(
        """
        SELECT * FROM weather 
        WHERE date_time =:currentDateTime AND region_id =:currentRegionId
        """
    )
    fun getCurrentWeather(currentDateTime: Long, currentRegionId: Long): Flow<WeatherEntity?>

    @Query("SELECT * FROM weather WHERE date_time BETWEEN :startDateTime AND :endDateTime")
    fun getWeathersByDateTime(startDateTime: Long, endDateTime: Long): Flow<List<WeatherEntity>>

    @Query("DELETE FROM weather")
    suspend fun clear()

}