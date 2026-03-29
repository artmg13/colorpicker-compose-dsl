package ru.adgoncharov.colorpicker.colorpickerdsl.components.circle

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.CircleArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun CircleHS(
    modifier: Modifier = Modifier,
    block: SpecificCircleScope.() -> Unit
) {
    val config = SpecificCircleScope().apply(block)

    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.hsCircle(
        !config.reversedX,
        config.reversedY
    )

    CircleArea(modifier, brush) {
        valueX = state.hue
        valueY = state.saturation
        onValueChangeX = state::changeHue
        onValueChangeY = state::changeSaturation
        this.reversedX = config.reversedX
        this.reversedY = config.reversedY
        this.thumbInside = config.thumbInside
        this.contentPadding = config.contentPadding
        this.thumbSize = config.thumbSize
        this.thumb = config.thumb
    }
}

