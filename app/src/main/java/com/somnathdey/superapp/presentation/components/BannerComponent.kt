package com.somnathdey.superapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.somnathdey.superapp.R
import com.somnathdey.superapp.presentation.ui.theme.blackColor
import com.somnathdey.superapp.presentation.ui.theme.primaryColor
import com.somnathdey.superapp.presentation.ui.theme.whiteColor

@Composable
fun BannerComponent(
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(primaryColor, blackColor)
                )
            )
    ) {
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.wrapContentSize(),
                model = it,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop
            )
        }

        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .wrapContentSize()
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.BottomEnd),
                resourceValue = resourceValue
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = 18.dp),
        ) {
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    textValue = it,
                    fontSizeValue = 24.sp,
                    textColorValue = whiteColor,
                )
            }
            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp),
                    textValue = it,
                    textColorValue = whiteColor,
                )
            }
        }
    }
}

@Preview(showSystemUi = true, name = "With network image")
@Composable
fun BannerComponentPreviewFirstType() {
    BannerComponent(
        title = "This is title",
        description = "This is long description",
        imageUrl = null,
        resourceValue = R.drawable.ic_wealth
    )
}

@Preview(showSystemUi = true, name = "With drawable image")
@Composable
fun BannerComponentPreviewSecondType() {
    BannerComponent(
        title = "This is title",
        description = "This is long description",
        imageUrl = "https://cdn.pixabay.com/photo/2025/05/04/11/36/asia-9578106_1280.jpg",
        resourceValue = null
    )
}