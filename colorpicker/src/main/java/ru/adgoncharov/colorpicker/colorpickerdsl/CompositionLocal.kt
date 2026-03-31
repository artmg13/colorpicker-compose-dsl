package ru.adgoncharov.colorpicker.colorpickerdsl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

val LocalColorPickerState = compositionLocalOf<ColorPickerState> {
    error("No ColorPickerState provided")
}

val LocalThumbSize = compositionLocalOf { DpSize(24.dp, 24.dp) }

val LocalThumbColor = compositionLocalOf { Color.White }

val LocalColorPickerThumb = compositionLocalOf<@Composable (Color) -> Unit> {
    { color -> DefaultThumb(color) }
}