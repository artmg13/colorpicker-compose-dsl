package ru.adgoncharov.colorpicker.colorpickerdsl.scope

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbColor
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.VerticalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

class VerticalSliderScope : BaseAreaScope() {
    var value: Float = 0f
    var onValueChange: (Float) -> Unit = {}
    var reversed: Boolean = false
    var shape: Shape = RectangleShape
}

@Composable
fun VerticalSlider(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    block: VerticalSliderScope.() -> Unit
) {
    val config = VerticalSliderScope().apply(block)
    val thumbSize = config.thumbSize ?: LocalThumbSize.current
    val thumbColor = config.thumbColor ?: LocalThumbColor.current

    val thumbRenderer =  config.thumb ?: LocalColorPickerThumb.current
    val thumb = remember(thumbColor, thumbRenderer) {
        @Composable { thumbRenderer(thumbColor) }
    }

    VerticalSlider(
        modifier = Modifier
            .width(24.dp)
            .fillMaxHeight()
            .then(modifier),
        value = config.value,
        onValueChange = config.onValueChange,
        brush = brush,
        reversed = config.reversed,
        isThumbInside = config.thumbInside,
        contentPadding = config.contentPadding,
        shape = config.shape,
        thumbSize = thumbSize,
        thumb = thumb
    )
}