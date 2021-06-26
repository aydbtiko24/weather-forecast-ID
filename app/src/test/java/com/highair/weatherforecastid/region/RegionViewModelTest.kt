package com.highair.weatherforecastid.region

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.FakePreferencesDataSource
import com.highair.weatherforecastid.data.FakeRegionRepository
import com.highair.weatherforecastid.data.source.local.asDomainModel
import com.highair.weatherforecastid.data.regionEntities
import com.highair.weatherforecastid.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by aydbtiko on 6/12/2021.
 *
 */
@ExperimentalCoroutinesApi
class RegionViewModelTest {

    private lateinit var regionRepository: FakeRegionRepository
    private lateinit var preferences: FakePreferencesDataSource

    private lateinit var viewModel: RegionViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        regionRepository = FakeRegionRepository()
        preferences = FakePreferencesDataSource()
    }

    private fun initViewModel() {
        viewModel = RegionViewModel(
            regionRepository, preferences
        )
    }

    @Test
    fun `loading state, state are loading`(): Unit = runBlockingTest {
        regionRepository.shouldLoading = true
        initViewModel()

        val state = viewModel.state.first()

        assertThat(state).isEqualTo(
            RegionViewState(
                dataLoading = true,
                regions = emptyList(),
                regionUpdated = false,
                searchQuery = ""
            )
        )
    }

    @Test
    fun `update region event, added to source`(): Unit = runBlockingTest {
        initViewModel()

        val region = regionEntities[0].asDomainModel()

        viewModel.setSelectedRegion(region.id)

        val latestRegionId = preferences.addedRegionId()

        assertThat(latestRegionId).isEqualTo(region.id)
    }

    @Test
    fun `update region event, state updated`(): Unit = runBlockingTest {
        initViewModel()

        val region = regionEntities[0].asDomainModel()

        val states = arrayListOf<RegionViewState>()

        val job = launch {
            viewModel.state.toList(states)
        }

        viewModel.setSelectedRegion(region.id)

        advanceUntilIdle()

        val updatedRegionState = states.map { it.regionUpdated }

        assertThat(updatedRegionState).containsExactlyElementsIn(
            listOf(true)
        )

        job.cancel()
    }

    @Test
    fun `update search event, state updated`(): Unit = runBlockingTest {
        initViewModel()

        val region = regionEntities[0].asDomainModel()

        val states = arrayListOf<RegionViewState>()

        val job = launch {
            viewModel.state.toList(states)
        }

        viewModel.searchRegion(region.district)

        viewModel.searchRegion(region.district)

        advanceUntilIdle()

        val updatedRegionsState = states.flatMap { it.regions }

        println("${updatedRegionsState.map { it.district }}}")

        assertThat(updatedRegionsState).containsExactlyElementsIn(
            listOf(region)
        )

        job.cancel()
    }

    @Test
    fun `update search event, search query state updated`(): Unit = runBlockingTest {
        initViewModel()

        val region = regionEntities[0].asDomainModel()

        val states = arrayListOf<RegionViewState>()

        val job = launch {
            viewModel.state.toList(states)
        }

        viewModel.searchRegion(region.district)

        advanceUntilIdle()

        val searchQueryState = states.map { it.searchQuery }

        println("$searchQueryState")

        assertThat(searchQueryState).containsExactlyElementsIn(
            listOf(region.district)
        )

        job.cancel()
    }
}
