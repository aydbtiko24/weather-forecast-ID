package com.highair.weatherforecastid.data.source.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.Constants.invalidId
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */


@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class PreferencesLocalDataSourceImplTest {

    private lateinit var preferencesLocalDataSource: PreferencesLocalDataSourceImpl

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        preferencesLocalDataSource = PreferencesLocalDataSourceImpl(
            PreferencesLocalDataSource.create(
                context,
                name = "test",
            )
        )
    }

    @Test
    fun `get selected region id, return region id`(): Unit = runBlocking {

        // given empty selected region id on store
        // when get selected region id
        val invalidResult = preferencesLocalDataSource.getSelectedRegionId().first()

        // then return invalid id
        assertThat(invalidResult).isEqualTo(invalidId)

        // given selected region id on store
        val regionId = 23L
        preferencesLocalDataSource.insertSelectedRegionId(regionId)
        // when get selected region id
        val result = preferencesLocalDataSource.getSelectedRegionId().first()
        // then return region id
        assertThat(result).isEqualTo(regionId)

    }
}