package ru.adgoncharov.colorpicker.colorpickerdsl.component.circle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.CircleArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@Composable
fun CircleHS(
    modifier: Modifier = Modifier,
    block: SpecificCircleScope.() -> Unit
) {
    val config = SpecificCircleScope().apply(block)

    val state = LocalColorPickerState.current

    val defaultColor = remember(state.hue, state.saturation) {
        Color.hsv(state.hue * 360f, state.saturation, 1f)
    }

    val brush = ColorPickerBrush.circleHS(
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
        this.thumbColor = config.thumbColor ?: defaultColor
        this.thumb = config.thumb
    }
}

