package com.highair.weatherforecastid.data

import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.models.Invalid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
class FakePreferencesDataSource : PreferencesLocalDataSource {

    private val selectedRegionKey = PreferencesLocalDataSource.SELECTED_REGION.name

    private val data = HashMap<String, Any>().apply {
        this[selectedRegionKey] = Invalid.id
    }

    val state = MutableStateFlow((data[selectedRegionKey] as? Long) ?: Invalid.id)

    override suspend fun insertSelectedRegionId(regionId: Long) {
        data[selectedRegionKey] = regionId
    }

    override fun getSelectedRegionId(): Flow<Long> {
        return state
    }

    fun addedRegionId(): Long {
      return  (data[selectedRegionKey] as? Long) ?: Invalid.id
    }

}