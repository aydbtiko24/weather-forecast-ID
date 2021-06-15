package com.highair.weatherforecastid.ui.components

import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by aydbtiko on 6/15/2021.
 *
 */

@Composable
fun WeatherDivider(
    modifier: Modifier = Modifier
) {
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
        modifier = modifier
    )
}