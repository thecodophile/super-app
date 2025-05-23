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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.somnathdey.superapp.R
import com.somnathdey.design.components.AppToolbar
import com.somnathdey.design.components.BannerComponent
import com.somnathdey.design.ui.theme.whiteColor

@Composable
fun HomeScreen(
    onClickPrimaryButton: () -> Unit = {},
    onClickWealthBanner: () -> Unit = {},
) {
    Scaffold(topBar = {
        AppToolbar(
            title = stringResource(R.string.add_address),
            isNotificationVisible = true,
            onClickPrimaryButton = {

            }
        )
    }) { innerPadding ->
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
                resourceValue = R.drawable.ic_wealth,
                onClickBanner = {
                    onClickWealthBanner()
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}