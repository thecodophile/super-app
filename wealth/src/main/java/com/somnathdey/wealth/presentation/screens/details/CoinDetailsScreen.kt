package com.somnathdey.wealth.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.somnathdey.design.components.AppToolbar
import com.somnathdey.design.components.ImageComponent
import com.somnathdey.design.components.TextComponent
import com.somnathdey.design.ui.theme.whiteColor
import com.somnathdey.wealth.R
import com.somnathdey.wealth.domain.model.CoinDetails
import com.somnathdey.wealth.presentation.screens.wealthHome.components.CoinListItem

@Composable
fun CoinDetailsScreen(
    coinId: String,
    onClickPrimaryButton: () -> Unit = {},
    coinDetailsViewModel: CoinDetailsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = coinId) {
        coinDetailsViewModel.fetchDetailsForCoin(coinId)
    }

    val state = coinDetailsViewModel.state.value

    Scaffold(topBar = {
        AppToolbar(
            title = state.coinDetails?.name ?: stringResource(R.string.coin_details),
            isBackButtonVisible = true,
            onClickPrimaryButton = {
                onClickPrimaryButton()
            })
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

                state.coinDetails?.also { coinDetails ->
                    CoinDetailsSuccessComponent(coinDetails)
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

@Composable
fun CoinDetailsSuccessComponent(coinDetails: CoinDetails) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ) {
        coinDetails.description.let { description ->
            TextComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp),
                textValue = description,
                fontSizeValue = 18.sp,
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        coinDetails.logo.let { logo ->
            ImageComponent(
                modifier = Modifier.wrapContentSize(),
                url = logo
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoinDetailsScreenPreview() {
    CoinDetailsScreen("#0coinId")
}