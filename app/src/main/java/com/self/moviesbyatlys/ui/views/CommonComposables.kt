package com.self.moviesbyatlys.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
                color = Color.Blue,
            )
            if (!loadingText.isNullOrBlank()) {
                Text(text = loadingText, modifier = Modifier.padding(top = 16.dp))
            }

        }

    }
}