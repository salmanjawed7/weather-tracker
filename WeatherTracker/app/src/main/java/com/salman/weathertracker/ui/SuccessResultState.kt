package com.salman.weathertracker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.salman.weathertracker.R
import com.salman.weathertracker.domain.model.Weather

@Composable
fun SuccessResultState(
    weather: Weather,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(124.dp)
                .align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalContext.current)
                .data(IconBaseUrl + weather.iconUrl.substringAfterLast("/"))
                .build(),
            contentDescription = "Weather Condition Icon",
        )
        Spacer(Modifier.height(16.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = weather.cityName,
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_location),
                contentDescription = "location icon",
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "" + weather.temperature + " °",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp))
                .background(WeatherAppGray)
        ) {
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Humidity",
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayHeading
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "" + weather.humidity + " %",
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayText
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "UV",
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayHeading
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "" + weather.uvIndex,
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayText
                )
            }
            Spacer(Modifier.width(16.dp))
            Column(
                Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Feels like",
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayHeading
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "" + weather.feelsLike + "°",
                    style = MaterialTheme.typography.bodySmall,
                    color = WeatherAppGrayText
                )
            }
            Spacer(Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewSuccessResultState() {
    SuccessResultState(
        weather = Weather(
            cityName = "Test",
            temperature = 50,
            condition = "Light",
            humidity = 20,
            uvIndex = 5,
            feelsLike = 51,
            iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png"
        )
    )
}