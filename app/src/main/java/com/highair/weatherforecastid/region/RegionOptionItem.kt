package com.highair.weatherforecastid.region

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.keyLine0
import com.highair.weatherforecastid.ui.theme.keyLine3

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

@Composable
fun RegionItem(
    region: Region,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(keyLine3)
    ) {
        Icon(
            imageVector = Icons.Rounded.Place,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        WeatherSpacer()

        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(
                text = region.district,
                style = MaterialTheme.typography.subtitle1,
            )
            WeatherSpacer(size = keyLine0)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${region.city}, ${region.province}.",
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}