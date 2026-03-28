package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.VerticalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun ValueSliderVertical(
    modifier: Modifier = Modifier,
    block: SpecificSliderVerticalScope.() -> Unit,
) {
    val config = SpecificSliderVerticalScope().apply(block)

    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.valueVerticalSlider(
        Color.hsv(state.hue * 360f, state.saturation, 1f),
        !config.reversed
    )

    VerticalSlider(modifier, brush) {
        value = state.value
        onValueChange = state::changeValue
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumb = config.thumb
    }
}