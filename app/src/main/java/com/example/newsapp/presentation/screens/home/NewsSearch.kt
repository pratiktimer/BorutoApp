package com.example.newsapp.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor

@Composable
fun NewsSearch(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        border = BorderStroke(2.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(TOP_APP_BAR_HEIGHT),
        color = topAppBarBackgroundColor // Vintage beige background
    ) {
        TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    "Search news...",
                    color = Color(0xFF6D4C41)
                )
            },
            textStyle = TextStyle(
                color = Color(0xFF3E2723),
                fontFamily = FontFamily.Serif
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF6D4C41)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color(0xFF3E2723)
            )
        )
    }
}
