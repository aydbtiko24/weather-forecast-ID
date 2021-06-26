package com.highair.weatherforecastid.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine0
import com.highair.weatherforecastid.ui.theme.keyLine3
import com.highair.weatherforecastid.utils.asTimeString
import com.highair.weatherforecastid.utils.asWeatherIconId
import com.highair.weatherforecastid.utils.isCurrentWeather

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun WeatherItem(
    weather: Weather
) {
    Surface(
        color =
        if (weather.dateTime.isCurrentWeather()) MaterialTheme.colors.secondary.copy(alpha = 0.1f)
        else MaterialTheme.colors.surface
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(keyLine3)
        ) {

            val centerVertically = Modifier.align(Alignment.CenterVertically)

            Image(
                modifier = centerVertically.size(40.dp),
                painter = painterResource(id = weather.code.asWeatherIconId()),
                contentDescription = weather.name
            )
            WeatherSpacer()
            Column(
                modifier = centerVertically
            ) {
                Text(
                    text = weather.dateTime.asTimeString(),
                    style = MaterialTheme.typography.subtitle2,
                )
                WeatherSpacer(size = keyLine0)
                Text(
                    text = weather.name,
                    style = MaterialTheme.typography.subtitle1
                )
                WeatherSpacer(size = keyLine0)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = """
                        ${
                            stringResource(
                                id = R.string.temp_c,
                                weather.tempC
                            )
                        } ${weather.tempF} ℉ ${weather.humidity}% Humidity
                        """.trimIndent(),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun WeatherItemPreview() {
    WeatherForecastIDTheme {
        Surface {
            WeatherItem(
                Weather(
                    id = 8,
                    name = "Cerah",
                    dateTime = 1623409200000,
                    code = 0,
                    humidity = 85,
                    tempC = 23,
                    tempF = 73,
                    regionId = 501162
                )
            )
        }
    }
}
