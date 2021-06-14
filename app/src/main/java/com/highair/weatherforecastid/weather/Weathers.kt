package com.highair.weatherforecastid.weather

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine3
import com.highair.weatherforecastid.utils.asTimeString
import com.highair.weatherforecastid.utils.asWeatherIconId

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun Weathers(
    weathers: List<Weather>
) {
    LazyRow(
        contentPadding = PaddingValues(keyLine3),
        horizontalArrangement = Arrangement.spacedBy(keyLine3)
    ) {
        items(
            items = weathers,
            key = { weather -> weather.id }
        ) { weather ->
            WeatherItem(weather)
        }
    }
}

@Composable
fun WeatherItem(
    weather: Weather
) {
    Card(
        border = BorderStroke(
            1.dp, color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
        ),
        elevation = 0.dp
    ) {
        Column(
            Modifier
                .width(160.dp)
                .padding(keyLine3)
        ) {
            Text(
                text = weather.dateTime.asTimeString(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            WeatherSpacer()
            Image(
                modifier = Modifier
                    .size(65.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = weather.code.asWeatherIconId()),
                contentDescription = weather.name
            )
            WeatherSpacer()
            Text(
                text = stringResource(id = R.string.temp_c, weather.tempC),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            WeatherSpacer()
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

@Composable
@Preview
fun WeatherItemsPreview() {
    WeatherForecastIDTheme {
        Surface {
            Weathers(
                listOf(
                    Weather(
                        id = 8,
                        name = "Cerah",
                        dateTime = 1623409200000,
                        code = 0,
                        humidity = 85,
                        tempC = 23,
                        tempF = 73,
                        regionId = 501162
                    ),
                    Weather(
                        id = 10,
                        name = "Cerah",
                        dateTime = 1623452400000,
                        code = 0,
                        humidity = 65,
                        tempC = 32,
                        tempF = 90,
                        regionId = 501162
                    ),
                    Weather(
                        id = 11,
                        name = "Cerah Berawan",
                        dateTime = 1623474000000,
                        code = 1,
                        humidity = 80,
                        tempC = 26,
                        tempF = 79,
                        regionId = 501162
                    ),
                    Weather(
                        id = 12,
                        name = "Cerah Berawan",
                        dateTime = 1623495600000,
                        code = 1,
                        humidity = 90,
                        tempC = 23,
                        tempF = 73,
                        regionId = 501162
                    ),
                )
            )
        }
    }
}

