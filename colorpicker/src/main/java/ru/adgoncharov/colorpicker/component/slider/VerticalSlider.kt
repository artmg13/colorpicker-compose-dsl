package ru.adgoncharov.colorpicker.component.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.area.ColorPickerArea
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.VerticalSliderLimiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun VerticalSlider(
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
        Limiter.verticalSlider(isThumbInside, reversed)
    }

    val style = remember(shape, contentPadding) {
        ColorPickerAreaStyle.verticalSlider(shape = shape, contentPadding = contentPadding)
    }

    ColorPickerArea(
        modifier = modifier,
        style = style,
        valueX = 0f,
        valueY = value,
        onValueChangeX = { },
        onValueChangeY = onValueChange,
        brush = brush,
        limiter = limiter,
        thumbSize = thumbSize,
        thumb = thumb
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun VerticalSliderPreview() {

    val colorPickerState = rememberColorPickerState()

    val hueBrush = ColorPickerBrushes.hueVerticalSlider()

    val thumbSize = remember { DpSize(24.dp, 24.dp) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top,
    ) {

        VerticalSlider(
            modifier = Modifier
                .height(300.dp)
                .width(24.dp),
            value = colorPickerState.hue,
            onValueChange = { colorPickerState.changeHue(it) },
            brush = hueBrush,
        )

        VerticalSlider(
            modifier = Modifier
                .height(300.dp)
                .width(24.dp),
            value = colorPickerState.hue,
            onValueChange = { colorPickerState.changeHue(it) },
            brush = hueBrush,
            isThumbInside = false
        )

        VerticalSlider(
            modifier = Modifier
                .height(300.dp)
                .width(24.dp),
            value = colorPickerState.hue,
            onValueChange = { colorPickerState.changeHue(it) },
            brush = hueBrush,
            isThumbInside = false,
            thumbSize = thumbSize,
        )

        VerticalSlider(
            modifier = Modifier
                .height(300.dp)
                .width(24.dp),
            value = colorPickerState.hue,
            onValueChange = { colorPickerState.changeHue(it) },
            brush = hueBrush,
            reversed = true,
        )

        VerticalSlider(
            modifier = Modifier
                .height(300.dp)
                .width(24.dp),
            value = colorPickerState.hue,
            onValueChange = { colorPickerState.changeHue(it) },
            brush = hueBrush,
            isThumbInside = true,
            thumbSize = thumbSize,
        )
    }
}