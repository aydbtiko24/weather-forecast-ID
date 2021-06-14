package com.highair.weatherforecastid.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.highair.weatherforecastid.ui.theme.keyLine2

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun WeatherSpacer(
    modifier: Modifier = Modifier,
    size: Dp = keyLine2
) {
    Spacer(modifier = modifier.padding(size))
}
