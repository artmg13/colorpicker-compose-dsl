package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.HorizontalSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.VerticalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@Composable
fun AlphaSlider(
    modifier: Modifier = Modifier,
    block: SpecificAlphaSliderScope.() -> Unit,
) {
    val config = SpecificAlphaSliderScope().apply(block)

    val state = LocalColorPickerState.current

    val defaultColor = remember(state.hue, state.saturation, state.value) {
        Color.hsv(state.hue * 360f, state.saturation, state.value)
    }

    val brush = ColorPickerBrush.alphaHorizontalSlider(
        state.color,
        config.reversed,
        config.showGrid,
        config.gridSize,
        config.lightGridColor,
        config.darkGridColor
    )

    HorizontalSlider(modifier, brush) {
        value = state.alpha
        onValueChange = state::changeAlpha
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumbColor = config.thumbColor ?: defaultColor
        this.thumb = config.thumb
    }
}

@Composable
fun AlphaSliderVertical(
    modifier: Modifier = Modifier,
    block: SpecificAlphaSliderScope.() -> Unit,
) {
    val config = SpecificAlphaSliderScope().apply(block)

    val state = LocalColorPickerState.current

    val defaultColor = remember(state.hue, state.saturation, state.value) {
        state.color.copy(alpha = 1f)
    }

    val brush = ColorPickerBrush.alphaVerticalSlider(
        state.color,
        !config.reversed,
        config.showGrid,
        config.gridSize,
        config.lightGridColor,
        config.darkGridColor
    )

    VerticalSlider(modifier, brush) {
        value = state.alpha
        onValueChange = state::changeAlpha
        this.reversed = config.reversed
        this.shape = config.shape
        this.contentPadding = config.contentPadding
        this.thumbInside = config.thumbInside
        this.thumbSize = config.thumbSize
        this.thumbColor = config.thumbColor ?: defaultColor
        this.thumb = config.thumb
    }
}

@ColorPickerDsl
class SpecificAlphaSliderScope : SpecificSliderScope() {
    var showGrid: Boolean = true
    var gridSize: Dp = 8.dp
    var lightGridColor: Color = Color.Transparent
    var darkGridColor: Color = Color.LightGray
}