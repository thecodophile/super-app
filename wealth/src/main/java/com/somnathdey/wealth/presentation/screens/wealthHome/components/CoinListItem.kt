package com.somnathdey.wealth.presentation.screens.wealthHome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnathdey.datasource.local.entity.Coin
import com.somnathdey.design.components.TextComponent
import com.somnathdey.design.ui.theme.primaryColor
import com.somnathdey.design.ui.theme.whiteColor

@Composable
fun CoinListItem(
    coin: Coin,
    coinItemClicked: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .clickable {
                coinItemClicked(coin.id)
            }
            .padding(top = 18.dp, start = 18.dp, end = 18.dp)
            .border(1.dp, primaryColor, RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(whiteColor)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextComponent(
                modifier = Modifier.weight(8f),
                textValue = "${coin.rank} ${coin.name} ${coin.symbol}",
                textColorValue = Color.Blue,
                fontSizeValue = 18.sp
            )

            TextComponent(
                modifier = Modifier.wrapContentWidth(),
                textValue = if (coin.isActive) "Active" else "Inactive",
                textColorValue = if (coin.isActive) Color.Green else Color.Gray,
            )

        }
    }
}
