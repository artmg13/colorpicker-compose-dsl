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
fun HorizontalSlider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    brush: ColorPickerBrush,
    contentPadding: PaddingValues = PaddingValues(),
    shape: Shape = RectangleShape,
    isThumbInside: Boolean = true,
    reversed: Boolean = false,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() },
) {
    val limiter = remember(isThumbInside, reversed) {
        Limiter.horizontalSlider(isThumbInside, reversed)
    }
    val style = remember(shape, contentPadding) {
        ColorPickerAreaStyle.horizontalSlider(shape = shape, contentPadding = contentPadding)
    }

    ColorPickerArea(
        modifier = modifier,
        style = style,
        valueX = value,
        valueY = 0f,
        onValueChangeX = onValueChange,
        onValueChangeY = {},
        brush = brush,
        limiter = limiter,
        thumbSize = thumbSize,
        thumb = thumb
    )
}