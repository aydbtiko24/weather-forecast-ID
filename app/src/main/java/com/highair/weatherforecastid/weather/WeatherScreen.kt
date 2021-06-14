package com.highair.weatherforecastid.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.ui.components.FullScreenLoading
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine3

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    openRegionPicker: () -> Unit
) {

    val viewState by viewModel.state.collectAsState(
        initial = WeatherViewState.EMPTY
    )

    WeatherScreenContent(
        dataLoading = viewState.dataLoading,
        selectedRegion = viewState.selectedRegion,
        currentWeather = viewState.currentWeather,
        weatherDates = viewState.weatherDates,
        weathers = viewState.weathers,
        openRegionPicker = openRegionPicker,
        onSelectedDateChange = viewModel::setSelectedDate
    )
}

@Composable
internal fun WeatherScreenContent(
    dataLoading: Boolean,
    selectedRegion: Region,
    currentWeather: Weather,
    weatherDates: List<WeatherDateOption>,
    weathers: List<Weather>,
    openRegionPicker: () -> Unit,
    onSelectedDateChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding),
            color = Color.LightGray.copy(alpha = 0.3f)
        ) {

            if (selectedRegion.id == Invalid.id) {
                openRegionPicker()
            }

            if (dataLoading) {
                FullScreenLoading()
            }

            if (!dataLoading && selectedRegion.id != 0L && selectedRegion.id != Invalid.id) {
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {

                    RegionItem(
                        region = selectedRegion,
                        modifier = Modifier.clickable { openRegionPicker() }
                    )

                    CurrentWeatherItem(
                        weather = currentWeather
                    )

                    Surface(
                        shape = RoundedCornerShape(
                            topStart = keyLine3,
                            topEnd = keyLine3
                        )
                    ) {
                        Column {
                            DateOptions(
                                dateOptions = weatherDates,
                                onSelectedChange = onSelectedDateChange
                            )

                            Weathers(
                                weathers = weathers
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun WeatherScreenPreview() {
    val viewState = WeatherViewState(
        false,
        selectedRegion = Region(
            id = 501162,
            province = "Bali",
            city = "Kab. Karangasem",
            district = "Amplapura"
        ),
        currentWeather = Weather(
            id = 10,
            name = "Cerah",
            dateTime = 1623452400000,
            code = 0,
            humidity = 65,
            tempC = 32,
            tempF = 90
        ),
        weatherDates = listOf(
            WeatherDateOption(
                date = "Thu, 10 jun",
                selected = false
            ),
            WeatherDateOption(
                date = "Thu, 11 jun",
                selected = true
            ),
            WeatherDateOption(
                date = "Thu, 12 jun",
                selected = false
            )
        ),
        weathers = listOf(
            Weather(
                id = 8,
                name = "Cerah",
                dateTime = 1623409200000,
                code = 0,
                humidity = 85,
                tempC = 23,
                tempF = 73
            ),
            Weather(
                id = 10,
                name = "Cerah",
                dateTime = 1623452400000,
                code = 97,
                humidity = 65,
                tempC = 32,
                tempF = 90
            ),
            Weather(
                id = 11,
                name = "Cerah Berawan",
                dateTime = 1623474000000,
                code = 1,
                humidity = 80,
                tempC = 26,
                tempF = 79
            ),
            Weather(
                id = 12,
                name = "Cerah Berawan",
                dateTime = 1623495600000,
                code = 1,
                humidity = 90,
                tempC = 23,
                tempF = 73
            ),
        )
    )

    WeatherForecastIDTheme {
        Surface {
            WeatherScreenContent(
                dataLoading = viewState.dataLoading,
                selectedRegion = viewState.selectedRegion,
                currentWeather = viewState.currentWeather,
                weatherDates = viewState.weatherDates,
                weathers = viewState.weathers,
                openRegionPicker = {},
                onSelectedDateChange = {}
            )
        }
    }
}
