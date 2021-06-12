package com.highair.weatherforecastid.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class RegionViewModel @Inject constructor(
    regionRepository: RegionRepository,
    private val preferences: PreferencesLocalDataSource
) : ViewModel() {

    val state: Flow<RegionViewState> = regionRepository.getRegions().map { result ->
        RegionViewState(
            dataLoading = result is Result.Loading,
            regions = (result as? Result.Success)?.data ?: emptyList()
        )
    }

    fun setSelectedRegion(regionId: Long) = viewModelScope.launch {
        preferences.insertSelectedRegionId(regionId)
    }

}
