package com.salman.weathertracker.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            textState = it
        },
        shape = RoundedCornerShape(16.dp),
        placeholder = { Text("Search Location", color = Color.Gray) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 44.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            disabledContainerColor = WeatherAppGray,
            unfocusedContainerColor = WeatherAppGray,
            unfocusedBorderColor = WeatherAppGray,
            focusedBorderColor = WeatherAppGray,
        ),
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
        },
    )

    LaunchedEffect(key1 = textState) {
        if (textState.text.isNotEmpty()) {
            delay(300)
            onValueChange(textState.text)
        }
    }
}