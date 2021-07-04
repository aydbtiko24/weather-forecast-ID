package com.highair.weatherforecastid

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.highair.weatherforecastid.data.FakePreferencesDataSource
import com.highair.weatherforecastid.data.FakeRegionRepository
import com.highair.weatherforecastid.data.FakeWeatherRepository
import com.highair.weatherforecastid.data.asFake
import com.highair.weatherforecastid.data.regionEntities
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.repository.WeatherRepository
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.data.source.local.asDomainModel
import com.highair.weatherforecastid.data.weatherEntities
import com.highair.weatherforecastid.di.DataModule
import com.highair.weatherforecastid.ui.NavGraph
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.utils.asDateString
import com.highair.weatherforecastid.weather.lottieAnimationRepeatCount
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by aydbtiko on 6/26/2021.
 *
 */
@UninstallModules(DataModule::class)
@HiltAndroidTest
class WeatherScreenTest {

    @BindValue
    @JvmField
    val weatherRepository: WeatherRepository = FakeWeatherRepository()

    @BindValue
    @JvmField
    val regionRepository: RegionRepository = FakeRegionRepository()

    @BindValue
    @JvmField
    val preferencesDataSource: PreferencesLocalDataSource = FakePreferencesDataSource()

    @get:Rule(order = 1)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val region = regionEntities[0].asDomainModel()

    @Before
    fun setUp() {
        lottieAnimationRepeatCount = 1
    }

    @Test
    fun displayCurrentRegion() {

        preferencesDataSource.asFake().state = MutableStateFlow(region.id)

        setContent()

        composeTestRule.onRoot(
            useUnmergedTree = true
        ).printToLog("NODE")

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = region.district,
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = "${region.city}, ${region.province}.",
        ).assertIsDisplayed()
    }

    @Test
    fun displaySelectedWeathersByDate() {

        val weather = weatherEntities[0].asDomainModel()

        preferencesDataSource.asFake().state = MutableStateFlow(region.id)

        setContent()

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = weather.dateTime.asDateString(),
        ).performClick()

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = weather.dateTime.asDateString(),
        ).performClick()

        composeTestRule.onRoot(
            useUnmergedTree = true
        ).printToLog("NODE")

        composeTestRule.onAllNodesWithText(
            text = weather.name,
        )[0].assertIsDisplayed()

        val tempcText = getInstrumentation().targetContext.getString(
            R.string.temp_c,
            weather.tempC
        )

        val detailTempText = "$tempcText ${weather.tempF} ℉ ${weather.humidity}% Humidity"

        composeTestRule.onAllNodesWithText(
            text = detailTempText,
        )[0].assertIsDisplayed()
    }

    @Test
    fun invalidRegionNavigateToPickRegion() {
        setContent()

        val pickRegionText = getInstrumentation().targetContext.getString(
            R.string.pick_region_label
        )

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = pickRegionText,
        ).assertIsDisplayed()
    }

    @Test
    fun touchRegionNavigateToPickRegion() {
        preferencesDataSource.asFake().state = MutableStateFlow(region.id)

        setContent()

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = region.district,
        ).performClick()

        val pickRegionText = getInstrumentation().targetContext.getString(
            R.string.pick_region_label
        )

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = pickRegionText,
        ).assertIsDisplayed()
    }

    private fun setContent() {
        composeTestRule.setContent {
            WeatherForecastIDTheme {
                NavGraph()
            }
        }
    }
}
