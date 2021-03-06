package com.highair.weatherforecastid.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.dataOrEmpty
import com.highair.weatherforecastid.data.isLoading
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.models.Region
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class RegionViewModel @Inject constructor(
    regionRepository: RegionRepository,
    private val preferences: PreferencesLocalDataSource
) : ViewModel() {

    private val regionUpdated = MutableStateFlow(false)

    private val searchQuery = MutableStateFlow("")

    private val regions: Flow<Result<List<Region>>> = searchQuery
        .debounce(200)
        .map { it.lowercase() }
        .distinctUntilChanged()
        .flatMapLatest { query -> regionRepository.getRegions(query) }

    val state: Flow<RegionViewState> =
        combine(regionUpdated, regions, searchQuery) { updated, regionsResult, searchQuery ->
            RegionViewState(
                dataLoading = regionsResult.isLoading(),
                regions = regionsResult.dataOrEmpty(),
                regionUpdated = updated,
                searchQuery = searchQuery
            )
        }

    fun setSelectedRegion(regionId: Long) = viewModelScope.launch {
        preferences.insertSelectedRegionId(regionId)
        regionUpdated.value = true
    }

    fun searchRegion(query: String) {
        searchQuery.value = query
    }

}
