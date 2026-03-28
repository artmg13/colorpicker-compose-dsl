package ru.adgoncharov.colorpicker.thumb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp

@Composable
fun ColoredThumb(
    color: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(color)
            .border(width = 1.dp, brush = SolidColor(Color.White), CircleShape)
            .border(width = 2.dp, brush = SolidColor(Color.Black), CircleShape)
    )
}