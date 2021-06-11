package com.highair.weatherforecastid.data.source.remote

import com.highair.weatherforecastid.data.source.remote.ApiConstants.BASE_URL
import com.highair.weatherforecastid.data.source.remote.ApiConstants.REGION_PATH
import com.highair.weatherforecastid.data.source.remote.ApiConstants.WEATHER_PATH
import com.highair.weatherforecastid.data.source.remote.ApiConstants.regionPath
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import timber.log.Timber

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */

interface ApiService {

    @GET(WEATHER_PATH)
    suspend fun getWeathers(
        @Path(regionPath) region: Long
    ): List<WeatherDto>

    @GET(REGION_PATH)
    suspend fun getRegions(): List<RegionDto>

    companion object {

        fun create(): ApiService {
            val logger = HttpLoggingInterceptor {
                Timber.tag("OkHttp").d(it)
            }.apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiService::class.java)
        }
    }
}
