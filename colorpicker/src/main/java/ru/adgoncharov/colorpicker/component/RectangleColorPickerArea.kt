package ru.adgoncharov.colorpicker.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun RectangleColorPickerArea(
    modifier: Modifier = Modifier,
    valueX: Float,
    valueY: Float,
    onValueChangeX: (Float) -> Unit,
    onValueChangeY: (Float) -> Unit,
    brush: ColorPickerBrush,
    contentPadding: PaddingValues = PaddingValues(),
    shape: Shape = RectangleShape,
    reversedX: Boolean = false,
    reversedY: Boolean = false,
    isThumbInside: Boolean = true,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() },
) {

    val limiter = remember(isThumbInside, reversedX, reversedY) {
        Limiter.rectangle(isThumbInside, reversedX, reversedY)
    }
    val style = remember(shape, contentPadding) {
        ColorPickerAreaStyle.rectangle(shape = shape, contentPadding = contentPadding)
    }
    ColorPickerArea(
        modifier = modifier,
        style = style,
        valueX = valueX,
        valueY = valueY,
        onValueChangeX = onValueChangeX,
        onValueChangeY = onValueChangeY,
        brush = brush,
        limiter = limiter,
        thumbSize = thumbSize,
        thumb = thumb
    )
}