package com.highair.weatherforecastid.region

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.highair.weatherforecastid.R
import com.highair.weatherforecastid.models.Region
import com.highair.weatherforecastid.ui.components.FullScreenLoading
import com.highair.weatherforecastid.ui.components.WeatherDivider
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine2

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
        searchQuery = viewState.searchQuery,
        onRegionSelected = viewModel::setSelectedRegion,
        navigateUp = navigateUp,
        searchRegion = viewModel::searchRegion
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun RegionScreenContent(
    regionUpdated: Boolean,
    dataLoading: Boolean,
    regions: List<Region>,
    searchQuery: String,
    onRegionSelected: (Long) -> Unit,
    navigateUp: () -> Unit,
    searchRegion: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    if (regionUpdated) {
        navigateUp()
        return
    }

    val keyboardController = LocalSoftwareKeyboardController.current

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
                Column {
                    Surface(
                        color = MaterialTheme.colors.secondary.copy(alpha = 0.1f)
                    ) {

                        val focusedColor = MaterialTheme.colors.onSurface.copy(
                            ContentAlpha.medium
                        )

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = keyLine2),
                            value = searchQuery,
                            onValueChange = searchRegion,
                            label = {
                                Text(
                                    text = stringResource(R.string.search_region_label),
                                    style = MaterialTheme.typography.caption
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.search_region_placeholder),
                                    style = MaterialTheme.typography.caption
                                )
                            },
                            maxLines = 1,
                            textStyle = MaterialTheme.typography.body1,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = focusedColor.copy(
                                    alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity
                                ),
                                focusedLabelColor = focusedColor,
                                cursorColor = focusedColor,
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() }
                            )
                        )
                    }
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
                                WeatherDivider()
                            }
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
        ),
        regionUpdated = false,
        searchQuery = ""
    )

    WeatherForecastIDTheme {
        Surface {
            RegionScreenContent(
                regionUpdated = viewState.regionUpdated,
                dataLoading = viewState.dataLoading,
                regions = viewState.regions,
                searchQuery = "Bali",
                onRegionSelected = {},
                navigateUp = {},
                searchRegion = {}
            )
        }
    }
}
