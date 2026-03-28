package ru.adgoncharov.colorpicker.colorpickerdsl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.BaseAreaScope
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState = rememberColorPickerState(),
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() },
    content: @Composable ColumnScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalColorPickerState provides state,
        LocalThumbSize provides thumbSize,
        LocalColorPickerThumb provides thumb,
    ) {
        Column(
            modifier = modifier,
        ) {
            content()
        }
    }
}

//@ColorPickerDsl
//class ColorPickerScope : BaseAreaScope() {
//    init {
//        modifier = Modifier.fillMaxSize()
//        thumbSize = DpSize(24.dp, 24.dp)
//        thumb = { DefaultThumb() }
//    }
//}

