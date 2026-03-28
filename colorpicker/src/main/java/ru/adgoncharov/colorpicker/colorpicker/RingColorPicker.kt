package ru.adgoncharov.colorpicker.colorpicker

import android.text.Layout
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ru.adgoncharov.colorpicker.colorpickerdsl.builder.RingPadding
import ru.adgoncharov.colorpicker.colorpickerdsl.components.rectangle.RectangleSV
import ru.adgoncharov.colorpicker.colorpickerdsl.components.ring.HueRing
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.AlphaSlider
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpicker.thumb.ColoredThumb
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun RingColorPicker(
    modifier: Modifier = Modifier,
    state: ColorPickerState,
    ringWidth: Dp = 24.dp,
    showAlpha: Boolean = false,
    rectangleThumbSize: DpSize = DpSize(24.dp, 24.dp),
    alphaSliderShape: Shape = CircleShape,
    alphaSliderThumbSize: DpSize = DpSize(24.dp, 24.dp),
    alphaSliderThumb: @Composable () -> Unit = { DefaultThumb() },
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
                thumb = {
                    ColoredThumb(Color.hsv(state.hue * 360f, 1f, 1f))
                }
                contentInside = {
                    RectangleSV(
                        modifier = Modifier.aspectRatio(1f)
                    ) {
                        thumbSize = rectangleThumbSize
                        thumb = {
                            ColoredThumb(state.color.copy(alpha = 1f))
                        }
                    }
                }
            }

            if (showAlpha) {
                AlphaSlider(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = alphaSliderShape
                        )
                ) {
                    shape = alphaSliderShape
                    thumbSize = alphaSliderThumbSize
                    thumb = alphaSliderThumb
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

