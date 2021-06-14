package com.highair.weatherforecastid.region

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.ui.components.FullScreenLoading
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun RegionScreen(
    viewModel: RegionViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.state.collectAsState(
        initial = RegionViewState.EMPTY
    )
    RegionScreenContent(
        regionUpdated = viewState.regionUpdated,
        dataLoading = viewState.dataLoading,
        regions = viewState.regions,
        onRegionSelected = viewModel::setSelectedRegion,
        navigateUp = navigateUp
    )
}

@Composable
internal fun RegionScreenContent(
    regionUpdated: Boolean,
    dataLoading: Boolean,
    regions: List<Region>,
    onRegionSelected: (Long) -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    if (regionUpdated) {
        navigateUp()
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.pick_region_label))
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding)
        ) {
            if (dataLoading) {
                FullScreenLoading()
            } else {
                LazyColumn {
                    items(
                        items = regions,
                        key = { item -> item.id }
                    ) { region ->
                        Column {
                            RegionOptionItem(
                                region = region,
                                onRegionSelected = onRegionSelected
                            )
                            Divider(
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
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
fun RegionScreenPreview() {

    val viewState = RegionViewState(
        dataLoading = false,
        regions = listOf(
            Region(
                id = 501397,
                province = "Aceh",
                city = "Kota Banda Aceh",
                district = "Banda Aceh"
            ),
            Region(
                id = 501162,
                province = "Bali",
                city = "Kab. Karangasem",
                district = "Amplapura"
            ),
            Region(
                id = 5002216,
                province = "SumateraUtara",
                city = "Kab. Nias Barat",
                district = "Lahomi"
            ),
        )
    )

    WeatherForecastIDTheme {
        Surface {
            RegionScreenContent(
                regionUpdated = viewState.regionUpdated,
                dataLoading = viewState.dataLoading,
                regions = viewState.regions,
                onRegionSelected = {},
                navigateUp = {}
            )
        }
    }
}
