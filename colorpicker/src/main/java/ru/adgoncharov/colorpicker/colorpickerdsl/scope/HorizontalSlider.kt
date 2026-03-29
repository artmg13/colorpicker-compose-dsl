package ru.adgoncharov.colorpicker.colorpickerdsl.scope

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.HorizontalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush

@ColorPickerDsl
class HorizontalSliderScope : BaseAreaScope() {
    var value: Float = 0f
    var onValueChange: (Float) -> Unit = {}
    var reversed: Boolean = false
    var shape: Shape = RectangleShape
}

@Composable
fun HorizontalSlider(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    block: HorizontalSliderScope.() -> Unit
) {
    val config = HorizontalSliderScope().apply(block)
    val thumbSize = config.thumbSize ?: LocalThumbSize.current
    val thumb = config.thumb ?: LocalColorPickerThumb.current

    HorizontalSlider(
        modifier = Modifier
            .height(24.dp)
            .fillMaxWidth()
            .then(modifier),
        value = config.value,
        onValueChange = config.onValueChange,
        brush = brush,
        reversed = config.reversed,
        isThumbInside = config.thumbInside,
        thumbSize = thumbSize,
        contentPadding = config.contentPadding,
        shape = config.shape,
        thumb = thumb
    )
}