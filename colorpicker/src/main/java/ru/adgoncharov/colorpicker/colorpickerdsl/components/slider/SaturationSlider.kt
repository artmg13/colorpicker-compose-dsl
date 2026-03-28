package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.HorizontalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun SaturationSlider(
    modifier: Modifier = Modifier,
    block: SpecificSliderScope.() -> Unit,
) {
    val config = SpecificSliderScope().apply(block)

    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.saturationHorizontalSlider(
        Color.hsv(state.hue * 360f, 1f, 1f),
        config.reversed
    )

    HorizontalSlider(modifier, brush) {
        value = state.saturation
        onValueChange = state::changeSaturation
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumb = config.thumb
    }
}