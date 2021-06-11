package com.highair.weatherforecastid.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
interface PreferencesLocalDataSource {

    suspend fun insertSelectedRegionId(regionId: Long)

    fun getSelectedRegionId(): Flow<Long>

    companion object {

        val SELECTED_REGION = longPreferencesKey("selected_region")

        const val name = "user_preference"

        fun create(
            @ApplicationContext context: Context,
            name: String = this.name
        ): DataStore<Preferences> = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(name)
        }
    }
}
