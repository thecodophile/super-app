package com.somnathdey.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.somnathdey.design.R
import com.somnathdey.design.ui.theme.primaryColor
import com.somnathdey.design.ui.theme.whiteColor

@Composable
fun AppToolbar(
    title: String? = null,
    isBackButtonVisible: Boolean = false,
    isNotificationVisible: Boolean = false,
    onClickPrimaryButton: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(primaryColor)
            .systemBarsPadding()
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 18.dp, end = 18.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier.Companion
                .size(28.dp)
                .clickable {
                    onClickPrimaryButton()
                },
            painter = painterResource(id = if (isBackButtonVisible) R.drawable.ic_back else R.drawable.ic_user),
            contentDescription = if (isBackButtonVisible) "back button" else "User Image",
            tint = whiteColor
        )

        Spacer(modifier = Modifier.Companion.width(18.dp))

        TextComponent(
            modifier = Modifier.Companion.wrapContentSize(),
            textValue = title,
            textColorValue = whiteColor,
            fontSizeValue = 20.sp,
            paddingValue = 8.dp
        )

        Spacer(modifier = Modifier.Companion.weight(1f))

        if (isNotificationVisible) {
            Icon(
                modifier = Modifier.Companion.size(28.dp),
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = "Notification icon",
                tint = whiteColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppToolbarPreview() {
    AppToolbar(title = "App tool bar", isBackButtonVisible = true)
}