package com.self.moviesbyatlys.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.self.moviesbyatlys.R
import com.self.moviesbyatlys.ui.theme.IconColour

@Composable
fun FullScreenLoader(
    loadingText: String? = null
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray.copy(alpha = 0.3F))
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {}
        )) {
        Column(
            modifier = Modifier.align(
                alignment = Alignment.Center,
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = IconColour,
            )
            if (!loadingText.isNullOrBlank()) {
                Text(text = loadingText, modifier = Modifier.padding(top = 16.dp), fontSize = 16.sp)
            }

        }

    }
}

@Composable
fun FullScreenEmptyView() {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {}
        )) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null
            )
            Text(
                text = "Oops! No movie found.",
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 16.sp
            )
        }

    }
}

@Preview
@Composable
fun PreviewLoader() {
    FullScreenEmptyView()
}