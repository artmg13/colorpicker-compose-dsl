package ru.adgoncharov.colorpicker.colorpickerdsl.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.CircleColorPickerArea
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

    CircleColorPickerArea(
        modifier = Modifier
            .then(modifier),
        valueX = config.valueX,
        valueY = config.valueY,
        onValueChangeX = config.onValueChangeX,
        onValueChangeY = config.onValueChangeY,
        brush = brush,
        contentPadding = config.contentPadding,
        reversedX = config.reversedX,
        reversedY = config.reversedY,
        isThumbInside = config.thumbInside,
        thumbSize = thumbSize,
        thumb = thumb
    )
}