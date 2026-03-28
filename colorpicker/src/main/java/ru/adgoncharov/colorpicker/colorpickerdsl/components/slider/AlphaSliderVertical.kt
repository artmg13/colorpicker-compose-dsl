package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.VerticalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun AlphaSliderVertical(
    modifier: Modifier = Modifier,
    block: SpecificSliderScope.() -> Unit,
) {
    val config = SpecificSliderScope().apply(block)

    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.alphaVerticalSlider(state.color, !config.reversed)

    VerticalSlider(modifier, brush) {
        value = state.alpha
        onValueChange = state::changeAlpha
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumb = config.thumb
    }
}