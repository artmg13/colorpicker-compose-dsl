package ru.adgoncharov.colorpickerdemo.samples

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import ru.adgoncharov.colorpicker.colorpickerdsl.component.ring.HueRing


@Composable
fun HueRingSampleBasic() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        HueRing(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
        }
    }
}

@Composable
fun HueRingSampleThumbOutside() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        HueRing(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            ringWidth = 36.dp
            thumbSize = DpSize(48.dp, 48.dp)
            contentPadding = PaddingValues(6.dp)
            thumbInside = false
        }
    }
}

@Composable
fun HueRingSampleCustomThumb() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        val customThumb = remember {
            @Composable { color: Color ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CutCornerShape(4.dp))
                        .background(color)
                        .border(
                            width = 1.dp,
                            Color.White,
                            CutCornerShape(4.dp)
                        )
                )
            }
        }

        HueRing(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            ringWidth = 36.dp
            thumbSize = DpSize(48.dp, 48.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun HueRingSampleCustomThumbWithIcon() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        val customThumb = remember {
            @Composable { _: Color ->
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Red
                )
            }
        }

        HueRing(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            ringWidth = 24.dp
            thumbInside = false
            contentPadding = PaddingValues(24.dp)
            thumbSize = DpSize(48.dp, 48.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun HueRingSampleReversed() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        HueRing(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            reversed = true
        }
    }
}

@Preview(
    showBackground = true,
    name = "Basic hue ring"
)
@Composable
private fun HueRingBasicSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HueRingSampleBasic()
        HueRingSampleThumbOutside()
    }
}

@Preview(
    showBackground = true,
    name = "Hue ring with custom thumb"
)
@Composable
private fun HueRingCustomThumbSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HueRingSampleCustomThumbWithIcon()
        HueRingSampleCustomThumb()
    }
}

@Preview(
    showBackground = true,
    name = "Reversed hue ring"
)
@Composable
private fun HueRingReversedSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HueRingSampleReversed()
    }
}
