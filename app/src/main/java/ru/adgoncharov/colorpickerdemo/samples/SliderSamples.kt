package ru.adgoncharov.colorpickerdemo.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPicker
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.AlphaSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.component.slider.HueSliderVertical
import ru.adgoncharov.colorpicker.rememberColorPickerState

@Composable
fun HueSliderVerticalBasic() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        HueSliderVertical(
            modifier = Modifier
                .fillMaxHeight()
                .width(24.dp)
        ) {

        }
    }
}

@Composable
fun HueSliderVerticalLargeThumb() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        HueSliderVertical(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .width(24.dp)
        ) {
            thumbSize = DpSize(48.dp, 48.dp)
        }
    }
}

@Composable
fun HueSliderVerticalOutsideThumb() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        HueSliderVertical(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .width(24.dp)
        ) {
            contentPadding = PaddingValues(vertical = 24.dp)
            thumbInside = false
            thumbSize = DpSize(48.dp, 48.dp)
        }
    }
}

@Composable
fun HueSliderVerticalCustomShape() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        val customThumb = remember {
            @Composable { color: Color ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(color)
                )
            }
        }

        HueSliderVertical(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .width(24.dp)
        ) {
            shape = CircleShape
            contentPadding = PaddingValues(vertical = 24.dp)
            thumbInside = false
            thumbColor = Color.Yellow
            thumbSize = DpSize(48.dp, 48.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun HueSliderVerticalIconThumb() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        val customThumb = remember {
            @Composable { _: Color ->
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.LightGray
                )
            }
        }

        HueSliderVertical(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 12.dp)
                .width(24.dp)
        ) {
            shape = CircleShape
            contentPadding = PaddingValues(vertical = 24.dp)
            thumbInside = false
            thumbColor = Color.Yellow
            thumbSize = DpSize(48.dp, 48.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun AlphaSliderBasic() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(24.dp)
        ) {
            shape = CircleShape
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}

@Composable
fun AlphaSliderSmallGrid() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(24.dp)
        ) {
            gridSize = 4.dp
            shape = CircleShape
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}

@Composable
fun AlphaSliderCustomGridColors() {
    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(24.dp)
        ) {
            gridSize = 4.dp
            lightGridColor = Color.Yellow
            darkGridColor = Color.Green
            shape = CircleShape
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}

@Composable
fun AlphaSliderNoGrid() {
    val state = rememberColorPickerState(Color.Blue)
    ColorPicker(
        modifier = Modifier.wrapContentSize(),
        state = state
    ) {
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(24.dp)
        ) {
            showGrid = false
            shape = CircleShape
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun VerticalSliderSamplesPreview() {

    Row(
        modifier = Modifier.height(400.dp).padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        HueSliderVerticalBasic()
        HueSliderVerticalLargeThumb()
        HueSliderVerticalOutsideThumb()
        HueSliderVerticalCustomShape()
        HueSliderVerticalIconThumb()
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun HorizontalSliderSamplesPreview() {

    Column(
        modifier = Modifier
            .width(400.dp)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AlphaSliderBasic()
        AlphaSliderSmallGrid()
        AlphaSliderCustomGridColors()
        AlphaSliderNoGrid()
    }
}