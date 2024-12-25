package com.salman.weathertracker.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.salman.weathertracker.domain.model.Weather

@Composable
fun SearchResultState(
    weathers: List<Weather>,
    onSaveCity: (String) -> Unit
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(weathers) { _, weather ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(WeatherAppGray)
                    .padding(24.dp)
                    .clickable {
                        onSaveCity(weather.cityName)
                    }
            ) {
                Column {
                    Text(
                        text = weather.cityName,
                        style = MaterialTheme.typography.headlineMedium,
                    )

                    Text(
                        text = "" + weather.temperature + " °",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                }
                Spacer(Modifier.weight(1f))
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(IconBaseUrl + weather.iconUrl.substringAfterLast("/"))
                        .build(),
                    contentDescription = "Weather Condition Icon",
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSearchResultState() {
    SearchResultState(
        weathers = listOf(
            Weather(
                cityName = "Test",
                temperature = 50,
                condition = "Light",
                humidity = 20,
                uvIndex = 5,
                feelsLike = 51,
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            ),
            Weather(
                cityName = "Test",
                temperature = 50,
                condition = "Light",
                humidity = 20,
                uvIndex = 5,
                feelsLike = 51,
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            ),
            Weather(
                cityName = "Test",
                temperature = 50,
                condition = "Light",
                humidity = 20,
                uvIndex = 5,
                feelsLike = 51,
                iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
            )
        )
    ) { }
}