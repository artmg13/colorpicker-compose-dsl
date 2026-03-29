package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.VerticalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun HueSliderVertical(
    modifier: Modifier = Modifier,
    block: SpecificSliderVerticalScope.() -> Unit,
) {
    val config = SpecificSliderVerticalScope().apply(block)

    val state = LocalColorPickerState.current

    val defaultColor = remember(state.hue) {
        Color.hsv(state.hue * 360f, 1f, 1f)
    }

    val brush = ColorPickerBrushes.hueVerticalSlider(!config.reversed)

    VerticalSlider(modifier, brush) {
        value = state.hue
        onValueChange = state::changeHue
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumbColor = config.thumbColor ?: defaultColor
        this.thumb = config.thumb
    }
}