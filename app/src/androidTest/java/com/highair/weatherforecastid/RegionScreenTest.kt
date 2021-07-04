package com.highair.weatherforecastid

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.highair.weatherforecastid.data.FakePreferencesDataSource
import com.highair.weatherforecastid.data.FakeRegionRepository
import com.highair.weatherforecastid.data.FakeWeatherRepository
import com.highair.weatherforecastid.data.asFake
import com.highair.weatherforecastid.data.regionEntities
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.repository.WeatherRepository
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.data.source.local.asDomainModels
import com.highair.weatherforecastid.di.DataModule
import com.highair.weatherforecastid.ui.NavGraph
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
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
 * Created by aydbtiko on 7/2/2021.
 *
 */
@UninstallModules(DataModule::class)
@HiltAndroidTest
class RegionScreenTest {

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

    private val regions = regionEntities.asDomainModels()
    private val region = regions[0]

    @Before
    fun setUp() {
        lottieAnimationRepeatCount = 1
        preferencesDataSource.asFake().state = MutableStateFlow(region.id)
        setContent()
    }

    @Test
    fun displayRegions() {
        composeTestRule.onNodeWithText(
            regions[0].district
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            regions[1].district
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            regions[2].district
        ).assertIsDisplayed()
    }

    @Test
    fun pickRegionNavigateUp() {

        composeTestRule.onNodeWithText(
            regions[1].district
        ).performClick()

        val pickRegionText = getInstrumentation().targetContext.getString(
            R.string.pick_region_label
        )

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = pickRegionText
        ).assertDoesNotExist()
    }


    @Test
    fun searchRegion() {

        val searchTag = getInstrumentation().targetContext.getString(
            R.string.search_region_label
        )

        val selectedRegion = regions[2].district

        composeTestRule.onNodeWithTag(searchTag).performTextInput(selectedRegion)

        composeTestRule.onNodeWithTag(searchTag).assert(hasText(selectedRegion))
    }

    private fun setContent() {
        composeTestRule.setContent {
            WeatherForecastIDTheme {
                NavGraph()
            }
        }

        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = region.district
        ).performClick()

        val searchRegionText = getInstrumentation().targetContext.getString(
            R.string.search_region_label
        )
        composeTestRule.onNodeWithText(
            useUnmergedTree = true,
            text = searchRegionText
        ).performClick()
    }
}