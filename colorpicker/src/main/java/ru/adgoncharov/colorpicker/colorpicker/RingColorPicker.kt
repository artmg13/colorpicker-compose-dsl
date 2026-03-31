package ru.adgoncharov.colorpicker.colorpicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPicker
import ru.adgoncharov.colorpicker.colorpickerdsl.component.rectangle.RectangleSV
import ru.adgoncharov.colorpicker.colorpickerdsl.component.ring.HueRing
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.AlphaSlider
import ru.adgoncharov.colorpicker.rememberColorPickerState

@Composable
fun RingColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState,
    ringWidth: Dp = 24.dp,
    showAlpha: Boolean = false,
    rectangleThumbSize: DpSize = DpSize(24.dp, 24.dp),
    sliderShape: Shape = CircleShape,
    sliderThumbSize: DpSize = DpSize(24.dp, 24.dp),
) {

    ColorPicker(
        modifier = modifier,
        state = state,
        thumbSize = rectangleThumbSize,
    ) {

        Column(
            modifier = Modifier.width(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HueRing(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(1f)
            ) {
                this.ringWidth = ringWidth
                thumbSize = DpSize(ringWidth, ringWidth)
                contentInside = {
                    RectangleSV(
                        modifier = Modifier.aspectRatio(1f)
                    ) {
                        thumbSize = rectangleThumbSize
                    }
                }
            }

            if (showAlpha) {

                AlphaSlider {
                    shape = sliderShape
                    thumbSize = sliderThumbSize
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun RingColorPickerPreview() {

    val state = rememberColorPickerState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "RingColorPicker showAlpha = false",
            textAlign = TextAlign.Center
        )

//        RingColorPicker(
//            state = state,
//        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "RingColorPicker showAlpha = true ringWidth = 48.dp",
            textAlign = TextAlign.Center
        )

        RingColorPicker(
            state = state,
            ringWidth = 48.dp,
            showAlpha = true
        )
    }
}

