package ru.adgoncharov.colorpicker.colorpickerdsl.components.rectangle

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.RectangleArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun RectangleSV(
    modifier: Modifier = Modifier,
    block: SpecificRectangleScope.() -> Unit,
) {
    val config = SpecificRectangleScope().apply(block)

    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.svRectangle(
        Color.hsv(state.hue * 360f, 1f, 1f),
        config.reversedX,
        !config.reversedY
    )

    RectangleArea(modifier, brush) {
        valueX = state.saturation
        valueY = state.value
        onValueChangeX = state::changeSaturation
        onValueChangeY = state::changeValue
        this.reversedX = config.reversedX
        this.reversedY = config.reversedY
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumb = config.thumb
    }
}

