package ru.adgoncharov.colorpicker.colorpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPicker
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.AlphaSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.HueSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.SaturationSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.ValueSlider
import ru.adgoncharov.colorpicker.rememberColorPickerState


@Composable
fun SliderColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState,
    gap: Dp = 8.dp,
    showAlpha: Boolean = false,
    sliderShape: Shape = RectangleShape,
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
) {

    ColorPicker(
        modifier = modifier,
        state = state,
        thumbSize = thumbSize,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(gap),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HueSlider {
                shape = sliderShape
            }
            SaturationSlider {
                shape = sliderShape
            }
            ValueSlider {
                shape = sliderShape
            }
            if (showAlpha) {
                AlphaSlider {
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
private fun SliderColorPickerPreview() {

    val state = rememberColorPickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "SliderColorPicker showAlpha = false",
            textAlign = TextAlign.Center
        )

        SliderColorPicker(
            modifier = Modifier.fillMaxWidth(),
            state = state,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "SliderColorPicker showAlpha = true",
            textAlign = TextAlign.Center
        )

        SliderColorPicker(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            showAlpha = true,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "SliderColorPicker sliderShape = CircleShape",
            textAlign = TextAlign.Center
        )

        SliderColorPicker(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            sliderShape = CircleShape
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "SliderColorPicker thumbSize = 36.dp x 36.dp gap = 24",
            textAlign = TextAlign.Center
        )

        SliderColorPicker(
            modifier = Modifier.fillMaxWidth(),
            state = state,
            gap = 24.dp,
            thumbSize = DpSize(36.dp, 36.dp)
        )
    }
}