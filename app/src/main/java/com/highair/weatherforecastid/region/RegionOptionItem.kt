package com.highair.weatherforecastid.region

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.weather.RegionItem

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun RegionOptionItem(
    region: Region,
    onRegionSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    RegionItem(region = region,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onRegionSelected(region.id) }
    )
}