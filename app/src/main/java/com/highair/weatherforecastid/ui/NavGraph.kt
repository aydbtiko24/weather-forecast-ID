package com.highair.weatherforecastid.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.highair.weatherforecastid.region.RegionScreen
import com.highair.weatherforecastid.weather.WeatherScreen

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

object AppDestinations {
    const val WEATHER = "weather"
    const val REGION_PICKER = "region_picker"
}

@Suppress("unused")
object DestinationsResult {
    const val REGION_PICKER = "region_picker_result"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestinations: String = AppDestinations.WEATHER
) {
    val actions = remember(navController) {
        AppActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestinations
    ) {
        composable(AppDestinations.WEATHER) {
            WeatherScreen(
                openRegionPicker = actions.navigateToRegionPicker
            )
        }
        composable(AppDestinations.REGION_PICKER) {
            RegionScreen(
                navigateUp = actions.navigateUp
            )
        }
    }
}

class AppActions(
    navController: NavHostController
) {
    val navigateToRegionPicker: () -> Unit = {
        navController.navigate(AppDestinations.REGION_PICKER)
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}

/**
 * used to retrieving and storing result from savedState
 */
@Suppress("unused")
class DestinationsResultHandler(
    private val navController: NavHostController
) {
    fun <T> set(key: String, value: T) {
        navController.previousBackStackEntry?.savedStateHandle?.set(
            key, value
        )
    }

    fun <T> get(key: String, default: T): T {
        val result = navController.currentBackStackEntry?.savedStateHandle?.get<T>(
            key
        ) ?: default

        set(key, default)

        return result
    }
}
