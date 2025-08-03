package com.somnathdey.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChipTextComponent(
    textValue: String,
    containerColorValue: Color = Color.Cyan,
    contentColorValue: Color = Color.Black
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(12.dp),
        colors = CardColors(
            containerColor = containerColorValue,
            contentColor = contentColorValue,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),

        ) {
        TextComponent(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp),
            textValue = textValue,
            fontSizeValue = 16.sp,
        )
    }
}