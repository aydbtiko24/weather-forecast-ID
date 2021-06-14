package com.highair.weatherforecastid.region

import com.google.common.truth.Truth.assertThat
import com.highair.weatherforecastid.data.fake.PreferencesDataSource
import com.highair.weatherforecastid.data.fake.RegionRepository
import com.highair.weatherforecastid.data.source.local.asDomainModel
import com.highair.weatherforecastid.data.source.local.regionEntities
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

    private lateinit var regionRepository: RegionRepository
    private lateinit var preferences: PreferencesDataSource

    private lateinit var viewModel: RegionViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        regionRepository = RegionRepository()
        preferences = PreferencesDataSource()
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
                true,
                emptyList()
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
            listOf(false, true)
        )

        job.cancel()
    }
}
