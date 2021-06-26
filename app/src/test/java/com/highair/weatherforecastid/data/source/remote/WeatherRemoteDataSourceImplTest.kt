package com.highair.weatherforecastid.data.source.remote

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.weatherEntities
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by aydbtiko on 6/10/2021.
 *
 */
class WeatherRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var dataSource: WeatherRemoteDataSourceImpl

    @Before
    fun setUp() {
        val okHttp = buildOkHttpClient()
        val apiService = buildApiService(okHttp)
        mockWebServer = buildMockServer(SuccessDispatcher())
        dataSource = WeatherRemoteDataSourceImpl(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get weathers, returns expected values`(): Unit = runBlocking {
        // given return data from mockServer
        // when get weathers from remote server
        val weathers = dataSource.getWeathers(0)

        // then contains expected value
        assertThat(weathers).hasSize(weatherEntities.size)
    }

}