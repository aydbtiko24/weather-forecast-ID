package com.highair.weatherforecastid.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.ui.components.WeatherSpacer
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine2
import com.highair.weatherforecastid.utils.asWeatherAnimId

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun CurrentWeatherItem(
    weather: Weather,
    modifier: Modifier = Modifier
) {

    val animationSpec = remember { LottieAnimationSpec.RawRes(weather.code.asWeatherAnimId()) }
    val animationState = rememberLottieAnimationState(
        autoPlay = true,
        repeatCount = Integer.MAX_VALUE
    )

    Column(
        modifier = modifier
    ) {
        LottieAnimation(
            spec = animationSpec,
            animationState = animationState,
            modifier = Modifier
                .size(190.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        )
        WeatherSpacer()
        Text(
            text = weather.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.temp_c, weather.tempC),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${weather.tempF} \u2109 ${weather.humidity}",
                style = MaterialTheme.typography.body2
            )
            WeatherSpacer(size = 1.dp)
            Image(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "",
                modifier = Modifier
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        WeatherSpacer()
    }
}

@Composable
@Preview
fun CurrentWeatherItemPreview() {
    WeatherForecastIDTheme {
        Surface {
            CurrentWeatherItem(
                Weather(
                    id = 12,
                    name = "Cerah Berawan",
                    dateTime = 1623495600000,
                    code = 1,
                    humidity = 90,
                    tempC = 23,
                    tempF = 73
                )
            )
        }
    }
}
