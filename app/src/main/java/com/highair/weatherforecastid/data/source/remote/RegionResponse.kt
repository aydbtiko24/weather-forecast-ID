package com.highair.weatherforecastid.data.source.remote

import com.highair.weatherforecastid.models.Region
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@JsonClass(generateAdapter = true)
data class RegionDto(
    val id: Long,
    @Json(name = "propinsi")
    val province: String,
    @Json(name = "kota")
    val city: String,
    @Json(name = "kecamatan")
    val district: String
)

/**Mapper*/
fun RegionDto.asDomainModel() = Region(
        id = this.id,
        province = this.province,
        city = this.city,
        district = this.district
)
