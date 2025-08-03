package com.somnathdey.wealth.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.somnathdey.design.components.AppToolbar
import com.somnathdey.design.components.ChipTextComponent
import com.somnathdey.design.components.ImageComponent
import com.somnathdey.design.components.TextComponent
import com.somnathdey.design.ui.theme.greenColor
import com.somnathdey.design.ui.theme.lightGreenColor
import com.somnathdey.design.ui.theme.whiteColor
import com.somnathdey.wealth.R
import com.somnathdey.wealth.domain.model.CoinDetails
import java.text.NumberFormat

@Composable
fun CoinDetailsScreen(
    coinId: String,
    onClickPrimaryButton: () -> Unit = {},
    coinDetailsViewModel: CoinDetailsViewModel = hiltViewModel()
) {

    val coinPriceNumberFormat = coinDetailsViewModel.coinPriceNumberFormat()

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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
                    .background(whiteColor)
            ) {

                state.coinDetails?.also { coinDetails ->
                    CoinDetailsSuccessComponent(coinDetails)
                }

                CoinPriceInformationComponent(state, coinPriceNumberFormat)
//                state.coinTickerInformation?.also { coinTickerInformation ->
//                    CoinPriceInformationComponent(coinTickerInformation, coinPriceNumberFormat)
//                }

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
        CoinPrimaryComponent(coinDetails)

        CoinOrganisationDetailsComponent(coinDetails)

        TextComponent(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp),
            textValue = coinDetails.description,
            fontSizeValue = 16.sp,
            fontWeightValue = FontWeight.Light
        )
    }
}

@Composable
fun CoinPrimaryComponent(coinDetails: CoinDetails) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageComponent(
            modifier = Modifier.size(60.dp),
            url = coinDetails.logo
        )

        Column {
            TextComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 12.dp),
                textValue = coinDetails.name,
                fontSizeValue = 25.sp,
            )
            TextComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 12.dp),
                textValue = coinDetails.symbol,
                fontSizeValue = 18.sp,
            )
        }
    }

    Spacer(
        modifier = Modifier.height(8.dp)
    )

    coinDetails.author?.also { authorName ->
        TextComponent(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 12.dp),
            textValue = authorName,
            textColorValue = greenColor,
            fontSizeValue = 18.sp,
        )
    }

    Spacer(
        modifier = Modifier.height(8.dp)
    )
}

@Composable
fun CoinOrganisationDetailsComponent(coinDetails: CoinDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChipTextComponent(
            textValue = coinDetails.orgStructure,
            containerColorValue = lightGreenColor,
            contentColorValue = Color.Black
        )
        ChipTextComponent(
            textValue = coinDetails.hashAlgorithm,
            containerColorValue = lightGreenColor,
            contentColorValue = Color.Black
        )
    }
}

@Composable
fun CoinPriceInformationComponent(
    state: CoinDetailsScreenState,
    coinPriceNumberFormat: NumberFormat
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(24.dp)
    ) {

        if (state.isLoadingCoinTickerInformation) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp)
            )
        }

        if (!state.errorFetchingCoinTickerInformation.isNullOrEmpty()) {
            TextComponent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                textValue = state.errorFetchingCoinTickerInformation,
                textColorValue = Color.Red,
                fontSizeValue = 20.sp,
            )
        }

        state.coinTickerInformation?.also {
            TextComponent(
                modifier = Modifier.wrapContentSize(),
                textValue = "$ ${coinPriceNumberFormat.format(state.coinTickerInformation.priceInfo["USD"]?.price)}",
                fontSizeValue = 24.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoinDetailsScreenPreview() {
    CoinDetailsScreen("#0coinId")
}