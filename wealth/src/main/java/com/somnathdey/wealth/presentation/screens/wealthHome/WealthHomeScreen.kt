package com.somnathdey.wealth.presentation.screens.wealthHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.somnathdey.design.components.AppToolbar
import com.somnathdey.design.components.TextComponent
import com.somnathdey.design.ui.theme.whiteColor
import com.somnathdey.wealth.R
import com.somnathdey.wealth.presentation.screens.wealthHome.components.CoinListItem

@Composable
fun WealthHomeScreen(
    onClickPrimaryButton: () -> Unit = {},
    viewModel: WealthViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(topBar = {
        AppToolbar(
            title = stringResource(R.string.wealth),
            isBackButtonVisible = true,
            onClickPrimaryButton = {
                onClickPrimaryButton()
            }
        )
    }) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                    .background(whiteColor)
                    .padding(innerPadding)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.coins) { coin ->
                        CoinListItem(coin)
                    }
                }

            }

            if (!state.error.isNullOrEmpty()) {
                TextComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    textValue = state.error,
                    textColorValue = Color.Red,
                    fontSizeValue = 20.sp,

                    )
            }

            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun WealthHomeScreenPreview() {
    WealthHomeScreen()
}