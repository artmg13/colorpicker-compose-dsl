package ru.adgoncharov.colorpicker.colorpickerdsl.components.ring

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.RingSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun HueRing(
    modifier: Modifier = Modifier,
    block: SpecificRingScope.() -> Unit,
) {
    val config = SpecificRingScope().apply(block)
    val state = LocalColorPickerState.current

    val brush = ColorPickerBrushes.hueRing(!config.reversed)

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
        this.thumb = config.thumb
    }
}