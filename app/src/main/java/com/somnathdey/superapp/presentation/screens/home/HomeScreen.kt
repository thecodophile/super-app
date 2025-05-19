package com.somnathdey.superapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.somnathdey.superapp.R
import com.somnathdey.superapp.presentation.components.AppToolbar
import com.somnathdey.superapp.presentation.components.BannerComponent
import com.somnathdey.superapp.presentation.ui.theme.whiteColor

@Composable
fun HomeScreen() {
    Scaffold(topBar = { AppToolbar() }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
                .background(whiteColor)
                .padding(innerPadding)
        ) {
            BannerComponent(
                title = "Wealth",
                description = "Investment ideas for you",
                imageUrl = null,
                resourceValue = R.drawable.ic_wealth
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}