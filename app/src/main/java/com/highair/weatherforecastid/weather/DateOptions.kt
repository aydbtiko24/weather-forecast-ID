package com.highair.weatherforecastid.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
import com.highair.weatherforecastid.ui.theme.keyLine2
import com.highair.weatherforecastid.ui.theme.keyLine3

/**
 * Created by aydbtiko on 6/13/2021.
 *
 */

@Composable
fun DateOptions(
    dateOptions: List<WeatherDateOption>,
    onSelectedChange: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.background(color = MaterialTheme.colors.surface),
        contentPadding = PaddingValues(keyLine3),
        horizontalArrangement = Arrangement.spacedBy(keyLine3)
    ) {
        items(
            items = dateOptions,
            key = { item -> "${item.date}${item.selected}" }
        ) { dateOption ->
            DateOptionItem(
                dateOption = dateOption,
                modifier = Modifier.clickable {
                    onSelectedChange(dateOption.date)
                }
            )
        }
    }
}

@Composable
fun DateOptionItem(
    dateOption: WeatherDateOption,
    modifier: Modifier = Modifier
) {
    Surface {

        val contentAlpha =
            if (dateOption.selected) ContentAlpha.high
            else ContentAlpha.disabled

        CompositionLocalProvider(LocalContentAlpha provides contentAlpha) {

            Text(
                text = dateOption.date,
                modifier = modifier.padding(keyLine2),
                style = MaterialTheme.typography.body2.copy(
                    fontWeight =
                    if (dateOption.selected) FontWeight.Bold
                    else FontWeight.Normal
                )
            )
        }
    }
}

@Composable
@Preview
fun DateOptionItemSelectedPreview() {
    WeatherForecastIDTheme {
        Surface {
            DateOptionItem(
                dateOption = WeatherDateOption(
                    date = "Thu, 10 jun",
                    selected = true
                )
            )
        }
    }
}

@Composable
@Preview
fun DateOptionItemPreview() {
    WeatherForecastIDTheme {
        Surface {
            DateOptionItem(
                dateOption = WeatherDateOption(
                    date = "Thu, 10 jun",
                    selected = false
                )
            )
        }
    }
}

@Composable
@Preview
fun DateOptionsPreview() {
    WeatherForecastIDTheme {
        Surface {
            DateOptions(
                dateOptions = listOf(
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
                onSelectedChange = {}
            )
        }
    }
}