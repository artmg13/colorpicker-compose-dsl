package ru.adgoncharov.colorpicker.colorpicker

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPicker
import ru.adgoncharov.colorpicker.colorpickerdsl.components.circle.CircleHS
import ru.adgoncharov.colorpicker.colorpickerdsl.components.rectangle.RectangleSV
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.AlphaSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.HueSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.ValueSlider
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun CircleColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState,
    gap: Dp = 16.dp,
    showAlpha: Boolean = false,
    circleThumbSize: DpSize = DpSize(24.dp, 24.dp),
    sliderShape: Shape = CircleShape,
    sliderThumbSize: DpSize = DpSize(24.dp, 24.dp),
) {

    ColorPicker(
        modifier = modifier,
        state = state,
        thumbSize = sliderThumbSize,
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(gap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleHS(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(1f)
            ) {
                thumbSize = circleThumbSize
            }

            ValueSlider {
                shape = sliderShape
            }

            if (showAlpha) {
                AlphaSlider(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = sliderShape
                    )
                ) {
                    shape = sliderShape
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CircleColorPickerPreview() {

    val state = rememberColorPickerState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "RectangleColorPicker showAlpha = false",
            textAlign = TextAlign.Center
        )

        CircleColorPicker(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            state = state,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "RectangleColorPicker showAlpha = true",
            textAlign = TextAlign.Center
        )

        CircleColorPicker(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            state = state,
            showAlpha = true
        )
    }
}