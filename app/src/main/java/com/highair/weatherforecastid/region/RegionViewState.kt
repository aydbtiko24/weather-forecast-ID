package com.highair.weatherforecastid.region

import androidx.compose.runtime.Immutable
import com.highair.weatherforecastid.models.Region

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
@Immutable
data class RegionViewState(
    val dataLoading: Boolean,
    val regions: List<Region>
)
