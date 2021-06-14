package com.highair.weatherforecastid.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine1
import com.highair.weatherforecastid.ui.theme.keyLine3

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */

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
                text = region.city,
                style = MaterialTheme.typography.h6,
            )

            Text(
                text = "${region.province}, ${region.district}.",
                style = MaterialTheme.typography.overline,
            )
        }
    }
}

@Composable
@Preview
fun RegionItemPreview() {
    WeatherForecastIDTheme {
        Surface {
            RegionItem(
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
