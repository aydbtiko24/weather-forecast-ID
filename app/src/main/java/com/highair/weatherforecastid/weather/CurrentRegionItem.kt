package com.highair.weatherforecastid.weather

/**
 * Created by aydbtiko on 6/15/2021.
 *
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine3

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */

@Composable
fun CurrentRegionItem(
    region: Region,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(keyLine3)
    ) {

        val centerVertically = Modifier.align(Alignment.CenterVertically)

        Icon(
            imageVector = Icons.Rounded.Place,
            contentDescription = null,
            modifier = centerVertically
        )

        WeatherSpacer()

        Column(
            modifier = centerVertically.fillMaxWidth()
        ) {
            Text(
                text = region.district,
                style = MaterialTheme.typography.h6,
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "${region.city}, ${region.province}.",
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
@Preview
fun CurrentRegionItemPreview() {
    WeatherForecastIDTheme {
        Surface {
            CurrentRegionItem(
                region = Region(
                    id = 501397,
                    province = "Aceh",
                    city = "Kota Banda Aceh",
                    district = "Banda Aceh"
                )
            )
        }
    }
}
