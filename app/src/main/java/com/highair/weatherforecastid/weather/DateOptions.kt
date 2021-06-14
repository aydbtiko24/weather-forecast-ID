package com.highair.weatherforecastid.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.highair.weatherforecastid.ui.theme.WeatherForecastIDTheme
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
        contentPadding = PaddingValues(
            top = keyLine3,
            start = keyLine3,
            end = keyLine3
        ),
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
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = if (dateOption.selected) MaterialTheme.colors.secondary
        else MaterialTheme.colors.secondary.copy(alpha = 0.1f)
    ) {
        Text(
            text = dateOption.date,
            color = if (dateOption.selected) MaterialTheme.colors.onSecondary
            else MaterialTheme.colors.onSurface,
            modifier = modifier.padding(keyLine3),
            style = MaterialTheme.typography.body2
        )
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