package com.example.newsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.orangeColor
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.borutoapp.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsHomeTopBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
               Row {
                   Text(
                   modifier = Modifier.background(color = Color.Black).padding(2.dp),
                   text = "NEWS",
                   color = topAppBarContentColor,
                   style = MaterialTheme.typography.titleLarge,
                   fontWeight = FontWeight.Bold
               )
                   Text(
                       modifier = Modifier.background(color = orangeColor).padding(2.dp),
                       text = "PAPER",
                       color = topAppBarContentColor,
                       style = MaterialTheme.typography.titleLarge,
                       fontWeight = FontWeight.Bold
                   )
               }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topAppBarBackgroundColor
        ),
//        actions = {
//            IconButton(onClick = onSearchClicked) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = stringResource(R.string.search_icon),
//                    tint = topAppBarContentColor
//                )
//            }
//        }
    )
}

@Composable
@Preview
fun NewsHomeTopBarPreview() {
    NewsHomeTopBar {}
}
