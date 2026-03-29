package ru.adgoncharov.colorpicker.component.ring

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RingLimiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes
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
    val ringWidthPx = with(density) { ringWidth.toPx() }
    val style = remember(
        ringWidthPx,
        contentPadding
    ) {
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
private fun RingColorPickerAreaPreview() {
    val state = rememberColorPickerState()

    val brush = ColorPickerBrushes.hueRing()
    val brush2 = ColorPickerBrushes.hueRing(reversed = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.hsv(state.hue * 360f, 1f, 1f))
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Default RingColorPickerArea",
            textAlign = TextAlign.Center
        )

        RingColorPickerArea(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            value = state.hue,
            onValueChange = { state.changeHue(it) },
            brush = brush,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "ringWidth < thumbSize and reversed = true RingColorPickerArea",
            textAlign = TextAlign.Center
        )

        RingColorPickerArea(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            value = state.hue,
            onValueChange = { state.changeHue(it) },
            brush = brush2,
            ringWidth = 12.dp,
            reversed = true
        )
    }
}