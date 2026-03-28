package ru.adgoncharov.colorpicker.colorpickerdsl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.components.ring.HueRing
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.HueSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.HueSliderVertical
import ru.adgoncharov.colorpicker.colorpickerdsl.components.rectangle.RectangleSV
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.AlphaSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.AlphaSliderVertical
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.SaturationSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.SaturationSliderVertical
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.ValueSlider
import ru.adgoncharov.colorpicker.colorpickerdsl.components.slider.ValueSliderVertical

@Composable
fun Test() {
    ColorPicker(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(modifier = Modifier.weight(1f)) {
            HueSliderVertical { }
            SaturationSliderVertical { }
            ValueSliderVertical { }
            AlphaSliderVertical { }
        }
        HueSlider {  }
        SaturationSlider { }
        ValueSlider { }
        AlphaSlider { }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun TestPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        Test()
    }
}