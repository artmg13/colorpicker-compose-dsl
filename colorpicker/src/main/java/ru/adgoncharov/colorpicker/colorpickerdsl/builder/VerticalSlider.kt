package ru.adgoncharov.colorpicker.colorpickerdsl.builder

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyles
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
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
    val thumb = config.thumb ?: LocalColorPickerThumb.current

    val style = ColorPickerAreaStyles.verticalSlider(
        shape = config.shape,
        contentPadding = config.contentPadding
    )

    ru.adgoncharov.colorpicker.component.slider.VerticalSlider(
        modifier = Modifier
            .width(24.dp)
            .fillMaxHeight()
            .then(modifier),
        value = config.value,
        onValueChange = config.onValueChange,
        brush = brush,
        reversed = config.reversed,
        isThumbInside = config.thumbInside,
        style = style,
        thumbSize = thumbSize,
        thumb = thumb
    )
}