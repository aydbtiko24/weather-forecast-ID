package com.highair.weatherforecastid.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@Dao
interface RegionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegions(regionEntities: List<RegionEntity>)

    @Query("SELECT * FROM region")
    fun getRegions(): Flow<List<RegionEntity>>

    @Query("SELECT * FROM region WHERE _id =:regionId")
    fun getRegionById(regionId: Long): Flow<RegionEntity?>

}