package com.somnathdey.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnathdey.design.ui.theme.blackColor

@Composable
fun TextComponent(
    modifier: Modifier,
    textValue: String? = null,
    textColorValue: Color = blackColor,
    fontSizeValue: TextUnit = 16.sp,
    paddingValue: Dp = 0.dp
) {
    textValue?.let {
        Text(
            modifier = modifier
                .padding(all = paddingValue),
            text = it,
            style = TextStyle(
                color = textColorValue,
                fontSize = fontSizeValue
            ),
            textAlign = TextAlign.Start
        )
    }
}