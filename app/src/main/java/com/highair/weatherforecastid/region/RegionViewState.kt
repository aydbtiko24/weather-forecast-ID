package com.highair.weatherforecastid.region

import com.highair.weatherforecastid.models.Region

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
data class RegionViewState(
    val dataLoading: Boolean,
    val regions: List<Region>,
    val regionUpdated: Boolean,
    val searchQuery: String
) {
    companion object {
        val EMPTY = RegionViewState(
            dataLoading = false,
            regions = emptyList(),
            regionUpdated = false,
            searchQuery = ""
        )
    }
}
