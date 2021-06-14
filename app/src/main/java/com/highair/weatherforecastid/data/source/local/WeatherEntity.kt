package com.highair.weatherforecastid.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Weather

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Long? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date_time")
    val dateTime: Long,
    @ColumnInfo(name = "code")
    val code: Int,
    @ColumnInfo(name = "humidity")
    val humidity: Long,
    @ColumnInfo(name = "temp_c")
    val tempC: Long,
    @ColumnInfo(name = "temp_f")
    val tempF: Long,
    @ColumnInfo(name = "region_id")
    val regionId: Long
)

/**Mapper*/
fun WeatherEntity.asDomainModel() = Weather(
    id = this.id ?: Invalid.id,
    name = this.name,
    dateTime = this.dateTime,
    code = this.code,
    humidity = this.humidity,
    tempC = this.tempC,
    tempF = this.tempF,
    regionId = this.regionId,
)

fun List<WeatherEntity>.asDomainModels() = this.map {
    it.asDomainModel()
}

fun Weather.asEntity() = WeatherEntity(
    name = this.name,
    dateTime = this.dateTime,
    code = this.code,
    humidity = this.humidity,
    tempC = this.tempC,
    tempF = this.tempF,
    regionId = this.regionId
)

fun List<Weather>.asEntities() = this.map {
    it.asEntity()
}
