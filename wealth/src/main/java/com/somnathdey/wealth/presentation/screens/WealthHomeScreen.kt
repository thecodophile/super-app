package com.somnathdey.wealth.presentation.screens

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
import com.somnathdey.design.components.AppToolbar
import com.somnathdey.design.ui.theme.whiteColor
import com.somnathdey.wealth.R

@Composable
fun WealthHomeScreen(
    onClickPrimaryButton: () -> Unit = {}
) {
    Scaffold(topBar = {
        AppToolbar(
            title = stringResource(R.string.wealth),
            isBackButtonVisible = true,
            onClickPrimaryButton = {
                onClickPrimaryButton()
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
                .background(whiteColor)
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WealthHomeScreenPreview() {
    WealthHomeScreen()
}