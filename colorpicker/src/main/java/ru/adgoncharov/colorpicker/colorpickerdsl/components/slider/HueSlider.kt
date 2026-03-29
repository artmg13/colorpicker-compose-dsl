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
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes

@Composable
fun HueSlider(
    modifier: Modifier = Modifier,
    block: SpecificSliderScope.() -> Unit,
) {
    val config = SpecificSliderScope().apply(block)

    val state = LocalColorPickerState.current

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

//@Composable
//fun HueSlider(
//    modifier: Modifier = Modifier,
//    block: SpecificSliderScope.() -> Unit,
//) {
//    val state = LocalColorPickerState.current
//    val config = SpecificSliderScope().apply(block)
//    val thumbSize = config.thumbSize ?: LocalThumbSize.current
//
//    val defaultColor = remember(state.hue) {
//        Color.hsv(state.hue * 360f, 1f, 1f)
//    }
//
//    val thumbColor = config.thumbColor ?: defaultColor
//
//    val globalThumbRenderer = LocalColorPickerThumb.current
//    val thumb = config.thumb ?: remember(thumbColor, globalThumbRenderer) {
//        @Composable { globalThumbRenderer(thumbColor) }
//    }
//
//    val brush = ColorPickerBrushes.hueHorizontalSlider(config.reversed)
//
//    ru.adgoncharov.colorpicker.component.HorizontalSlider(
//        modifier = Modifier
//            .height(24.dp)
//            .fillMaxWidth()
//            .then(modifier),
//        value = state.hue,
//        onValueChange = state::changeHue,
//        brush = brush,
//        reversed = config.reversed,
//        isThumbInside = config.thumbInside,
//        thumbSize = thumbSize,
//        contentPadding = config.contentPadding,
//        shape = config.shape,
//        thumb = thumb
//    )
//}