package ru.adgoncharov.colorpicker.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun RingColorPickerArea(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    brush: ColorPickerBrush,
    ringWidth: Dp = 48.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reversed: Boolean = false,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() },
) {

    val density = LocalDensity.current
    val ringWidthPx = remember(ringWidth, density) {
        with(density) { ringWidth.toPx() }
    }

    val style = remember(ringWidthPx, contentPadding) {
        ColorPickerAreaStyle.ring(ringWidthPx = ringWidthPx, contentPadding = contentPadding)
    }

    val limiter = remember(ringWidthPx, reversed) {
        Limiter.ring(ringWidthPx = ringWidthPx, reversed = reversed)
    }

    ColorPickerArea(
        modifier = modifier,
        style = style,
        valueX = value,
        valueY = 0f,
        onValueChangeX = onValueChange,
        onValueChangeY = { },
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

        RingColorPickerArea(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            state.hue,
            onValueChange = state::changeHue,
            brush = ColorPickerBrush.circleHS(),
        )
    }
}