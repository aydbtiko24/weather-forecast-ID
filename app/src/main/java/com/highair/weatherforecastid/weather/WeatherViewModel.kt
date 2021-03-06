package com.highair.weatherforecastid.weather

import androidx.lifecycle.ViewModel
import com.highair.weatherforecastid.data.Result
import com.highair.weatherforecastid.data.dataOr
import com.highair.weatherforecastid.data.isLoading
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.repository.WeatherRepository
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.models.Invalid
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.models.Weather
import com.highair.weatherforecastid.utils.asDateString
import com.highair.weatherforecastid.utils.asSelectedLocalDateTime
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.System.currentTimeMillis
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

/**
 * Created by aydbtiko on 6/11/2021.
 *
 */
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val regionRepository: RegionRepository,
    preferences: PreferencesLocalDataSource
) : ViewModel() {

    private val selectedDate = MutableStateFlow(currentTimeMillis())

    private val selectedRegionId: Flow<Long> = preferences.getSelectedRegionId()

    private val selectedRegion: Flow<Region> = selectedRegionId.flatMapLatest { regionId ->
        regionRepository.getRegionBy(regionId)
    }

    private val currentWeather: Flow<Result<Weather>> = selectedRegionId.flatMapLatest { regionId ->
        weatherRepository.getCurrentWeather(
            regionId = regionId,
            update = false
        )
    }

    private val weatherDates: Flow<List<WeatherDateOption>> = selectedDate.flatMapLatest { date ->
        weatherRepository.getWeatherDates().map { dates ->
            dates.map { datesWeather ->
                WeatherDateOption(
                    selected = datesWeather == date.asDateString(),
                    date = datesWeather
                )
            }
        }
    }

    private val weathers: Flow<List<Weather>> = selectedDate.flatMapLatest {
        weatherRepository.getWeathersByDate(it)
    }

    val state: Flow<WeatherViewState> = combine(
        selectedRegion,
        currentWeather,
        weatherDates,
        weathers
    ) { region, weatherResult, dates, weathers ->

        WeatherViewState(
            dataLoading = weatherResult.isLoading(),
            selectedRegion = region,
            currentWeather = weatherResult dataOr Invalid.weather,
            weatherDates = dates,
            weathers = weathers
        )
    }

    fun setSelectedDate(date: String) {
        selectedDate.value = date.asSelectedLocalDateTime()
    }
}
