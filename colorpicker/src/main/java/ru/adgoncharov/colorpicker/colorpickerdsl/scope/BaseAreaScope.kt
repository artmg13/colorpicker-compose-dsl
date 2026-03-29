package ru.adgoncharov.colorpicker.colorpickerdsl.scope

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl

@ColorPickerDsl
abstract class BaseAreaScope {
    var thumbInside: Boolean = true
    var contentPadding: PaddingValues = PaddingValues(0.dp)

    var thumbSize: DpSize? = null
    var thumbColor: Color? = null
    var thumb: (@Composable () -> Unit)? = null
}