package com.somnathdey.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage

@Composable
fun ImageComponent(
    modifier: Modifier,
    resourceValue: Int? = null,
    url: String? = null
) {
    resourceValue?.also {
        Image(
            modifier = modifier,
            painter = painterResource(id = it),
            contentDescription = "Icon"
        )
    }
    url?.also {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = it,
            contentDescription = "Image",
            contentScale = ContentScale.Fit
        )
    }
}