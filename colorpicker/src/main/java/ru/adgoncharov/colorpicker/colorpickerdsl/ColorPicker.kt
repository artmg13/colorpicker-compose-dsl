package ru.adgoncharov.colorpicker.colorpickerdsl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState = rememberColorPickerState(),
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumbColor: Color = Color.White,
    thumb: @Composable (Color) -> Unit = remember { { color -> DefaultThumb(color) } },
    content: @Composable ColumnScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalColorPickerState provides state,
        LocalThumbSize provides thumbSize,
        LocalThumbColor provides thumbColor,
        LocalColorPickerThumb provides thumb,
    ) {
        Column(
            modifier = modifier,
        ) {
            content()
        }
    }
}

