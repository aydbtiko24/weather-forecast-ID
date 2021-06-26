package com.highair.weatherforecastid.data.source.remote

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.data.regionEntities
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class RegionRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: RegionRemoteDataSourceImpl

    @Before
    fun setUp() {
        val okHttp = buildOkHttpClient()
        val apiService = buildApiService(okHttp)
        mockWebServer = buildMockServer(SuccessDispatcher())
        dataSource = RegionRemoteDataSourceImpl(apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get regions, return expected value`(): Unit = runBlocking {
        // given return data from mockServer
        val regions = dataSource.getRegions()

        // then contains expected value
        assertThat(regions).isNotEmpty()
        val expected = regionEntities.asDomainModels()
        assertThat(regions).contains(
            expected[0]
        )
    }
}