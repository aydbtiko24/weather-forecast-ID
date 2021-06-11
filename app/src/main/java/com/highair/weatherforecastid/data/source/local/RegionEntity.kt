package com.highair.weatherforecastid.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.highair.weatherforecastid.models.Region

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@Entity(tableName = "region")
data class RegionEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: Long,
    @ColumnInfo(name = "province")
    val province: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "district")
    val district: String
)

/**Mapper*/
fun RegionEntity.asDomainModel() = Region(
    id = this.id,
    province = this.province,
    city = this.city,
    district = this.district
)

fun List<RegionEntity>.asDomainModels() = this.map {
    it.asDomainModel()
}

fun Region.asEntity() = RegionEntity(
    id = this.id,
    province = this.province,
    city = this.city,
    district = this.district
)

fun List<Region>.asEntities() = this.map {
    it.asEntity()
}

