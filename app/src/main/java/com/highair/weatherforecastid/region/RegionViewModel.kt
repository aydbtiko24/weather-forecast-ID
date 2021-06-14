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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
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

    private val regionUpdated = MutableStateFlow(false)

    val state: Flow<RegionViewState> =
        combine(regionUpdated, regionRepository.getRegions()) { updated, regionResult ->
            RegionViewState(
                dataLoading = regionResult is Result.Loading,
                regions = (regionResult as? Result.Success)?.data ?: emptyList(),
                regionUpdated = updated
            )
        }

    fun setSelectedRegion(regionId: Long) = viewModelScope.launch {
        preferences.insertSelectedRegionId(regionId)
        regionUpdated.value = true
    }
}
