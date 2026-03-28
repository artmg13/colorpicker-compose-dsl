package ru.adgoncharov.colorpicker.colorpickerdsl.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyles
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.rectangle.RectangleColorPickerArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@ColorPickerDsl
class RectangleAreaScope : BaseAreaScope() {
    var valueX: Float = 0f
    var valueY: Float = 0f
    var onValueChangeX: (Float) -> Unit = {}
    var onValueChangeY: (Float) -> Unit = {}
    var reversedX: Boolean = false
    var reversedY: Boolean = false
    var shape: Shape = RectangleShape
}

@Composable
fun RectangleArea(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    block: RectangleAreaScope.() -> Unit
) {
    val config = RectangleAreaScope().apply(block)
    val thumbSize = config.thumbSize ?: LocalThumbSize.current
    val thumb = config.thumb ?: LocalColorPickerThumb.current

    val style = ColorPickerAreaStyles.rectangle(
        shape = config.shape,
        contentPadding = config.contentPadding
    )

    RectangleColorPickerArea(
        modifier = Modifier
            .then(modifier),
        valueX = config.valueX,
        valueY = config.valueY,
        onValueChangeX = config.onValueChangeX,
        onValueChangeY = config.onValueChangeY,
        brush = brush,
        reversedX = config.reversedX,
        reversedY = config.reversedY,
        isThumbInside = config.thumbInside,
        thumbSize = thumbSize,
        style = style,
        thumb = thumb
    )
}