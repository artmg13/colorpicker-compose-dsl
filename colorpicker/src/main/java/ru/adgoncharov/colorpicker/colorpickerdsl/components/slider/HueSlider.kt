package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbColor
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.HorizontalSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.HorizontalSliderScope
import ru.adgoncharov.colorpicker.component.HorizontalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun HueSlider(
    modifier: Modifier = Modifier,
    block: SpecificSliderScope.() -> Unit,
) {
    val state = LocalColorPickerState.current

    val config = SpecificSliderScope().apply(block)

    val defaultColor = remember(state.hue) {
        Color.hsv(state.hue * 360f, 1f, 1f)
    }

    val brush = ColorPickerBrushes.hueHorizontalSlider(config.reversed)

    HorizontalSlider(modifier, brush) {
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