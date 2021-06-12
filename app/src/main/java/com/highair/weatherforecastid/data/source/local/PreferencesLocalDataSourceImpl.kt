package com.highair.weatherforecastid.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource.Companion.SELECTED_REGION
import com.highair.weatherforecastid.models.Invalid
import kotlinx.coroutines.flow.map

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
class PreferencesLocalDataSourceImpl(
    private val store: DataStore<Preferences>
) : PreferencesLocalDataSource {

    override suspend fun insertSelectedRegionId(regionId: Long) {
        store.edit { preferences ->
            preferences[SELECTED_REGION] = regionId
        }
    }

    override fun getSelectedRegionId() = store.data.map { preferences ->
        preferences[SELECTED_REGION] ?: Invalid.id
    }
}