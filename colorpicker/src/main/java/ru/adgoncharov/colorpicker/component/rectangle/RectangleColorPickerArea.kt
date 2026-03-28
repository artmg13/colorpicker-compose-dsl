package ru.adgoncharov.colorpicker.component.rectangle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyles
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RectangleLimiter
import ru.adgoncharov.colorpicker.component.slider.HorizontalSlider
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun RectangleColorPickerArea(
    modifier: Modifier = Modifier,
    valueX: Float,
    valueY: Float,
    onValueChangeX: (Float) -> Unit,
    onValueChangeY: (Float) -> Unit,
    brush: ColorPickerBrush,
    reversedX: Boolean = false,
    reversedY: Boolean = true,
    isThumbInside: Boolean = true,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    style: ColorPickerAreaStyle = ColorPickerAreaStyles.rectangle(),
    thumb: @Composable () -> Unit = { DefaultThumb() },
) {

    val limiter = remember(isThumbInside) { RectangleLimiter(isThumbInside) }

    ColorPickerArea(
        modifier = modifier,
        style = style,
        valueX = if (reversedX) 1f - valueX else valueX,
        valueY = if (reversedY) 1f - valueY else valueY,
        onValueChangeX = { newX ->
            if (reversedX)
                onValueChangeX(1f - newX)
            else
                onValueChangeX(newX)
        },
        onValueChangeY = { newY ->
            if (reversedY)
                onValueChangeY(1f - newY)
            else
                onValueChangeY(newY)
        },
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
private fun RectangleColorPickerAreaPreview() {

    val state = rememberColorPickerState()

    val hueBrush = ColorPickerBrushes.hueHorizontalSlider()

    val brush = ColorPickerBrushes.svRectangle(baseColor = Color.hsv(state.hue * 360f, 1f, 1f))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalSlider(
            modifier = Modifier.fillMaxWidth().height(24.dp),
            value = state.hue,
            onValueChange = {state.changeHue(it)},
            brush = hueBrush
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Default RectangleColorPickerArea",
            textAlign = TextAlign.Center
        )

        RectangleColorPickerArea(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            valueX = state.saturation,
            valueY = state.value,
            onValueChangeX = { state.changeSaturation(it) },
            onValueChangeY = { state.changeValue(it) },
            brush = brush,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "isThumbInside = false RectangleColorPickerArea",
            textAlign = TextAlign.Center
        )

        RectangleColorPickerArea(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            valueX = state.saturation,
            valueY = state.value,
            onValueChangeX = { state.changeSaturation(it) },
            onValueChangeY = { state.changeValue(it) },
            brush = brush,
            isThumbInside = false
        )
    }
}