package ru.adgoncharov.colorpicker.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun CircleColorPickerArea(
    modifier: Modifier = Modifier,
    valueX: Float,
    valueY: Float,
    onValueChangeX: (Float) -> Unit,
    onValueChangeY: (Float) -> Unit,
    brush: ColorPickerBrush,
    contentPadding: PaddingValues = PaddingValues(),
    reversedX: Boolean = false,
    reversedY: Boolean = false,
    isThumbInside: Boolean = true,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() },
) {

    val limiter = remember(isThumbInside, reversedX, reversedY) {
        Limiter.circle(isThumbInside, reversedX, reversedY)
    }

    val style = remember(contentPadding) {
        ColorPickerAreaStyle.circle(contentPadding = contentPadding)
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

@Preview(
    showBackground = true
)
@Composable
private fun CircleColorPickerAreaPreview() {

    val state = rememberColorPickerState()
    Column() {

        Text(text = "${state.hue}")

        CircleColorPickerArea(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            state.hue,
            state.saturation,
            onValueChangeX = state::changeHue,
            onValueChangeY = state::changeSaturation,
            brush = ColorPickerBrush.circleHS(),
        )
    }
}