package com.highair.weatherforecastid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.highair.weatherforecastid.ui.NavGraph
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastIDTheme {
                NavGraph()
            }
        }
    }
}
