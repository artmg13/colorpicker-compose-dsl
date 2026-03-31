package ru.adgoncharov.colorpicker.colorpickerdsl.component.ring

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.RingSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@Composable
fun HueRing(
    modifier: Modifier = Modifier,
    block: SpecificRingScope.() -> Unit,
) {
    val config = SpecificRingScope().apply(block)
    val state = LocalColorPickerState.current

    val defaultColor = remember(state.hue) {
        Color.hsv(state.hue * 360f, 1f, 1f)
    }

    val brush = ColorPickerBrush.hueRing(!config.reversed)

    RingSlider(modifier, brush) {
        value = state.hue
        onValueChange = state::changeHue
        this.ringWidth = config.ringWidth
        this.reversed = config.reversed
        this.contentPadding = config.contentPadding
        this.contentInsidePadding = config.contentInsidePadding
        this.gapContentInside = config.gapContentInside
        this.contentInside = config.contentInside
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumbColor = config.thumbColor ?: defaultColor
        this.thumb = config.thumb
    }
}