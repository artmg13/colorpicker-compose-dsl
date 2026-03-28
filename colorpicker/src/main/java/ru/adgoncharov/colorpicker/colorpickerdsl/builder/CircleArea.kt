package ru.adgoncharov.colorpicker.colorpickerdsl.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyles
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.circle.CircleColorPickerArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@ColorPickerDsl
class CircleAreaScope : BaseAreaScope() {
    var valueX: Float = 0f
    var valueY: Float = 0f
    var onValueChangeX: (Float) -> Unit = {}
    var onValueChangeY: (Float) -> Unit = {}
    var reversedX: Boolean = false
    var reversedY: Boolean = false
}

@Composable
fun CircleArea(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    block: CircleAreaScope.() -> Unit
) {
    val config = CircleAreaScope().apply(block)
    val thumbSize = config.thumbSize ?: LocalThumbSize.current
    val thumb = config.thumb ?: LocalColorPickerThumb.current

    val style = ColorPickerAreaStyles.circle(contentPadding = config.contentPadding)

    CircleColorPickerArea(
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