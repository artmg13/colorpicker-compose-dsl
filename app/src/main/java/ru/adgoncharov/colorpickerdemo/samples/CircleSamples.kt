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
import ru.adgoncharov.colorpicker.colorpickerdsl.component.circle.CircleHS

@Composable
fun CircleHSSampleBasic() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
        }
    }
}

@Composable
fun CircleHSSampleThumbOutside() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            thumbSize = DpSize(36.dp, 36.dp)
            contentPadding = PaddingValues(18.dp)
            thumbInside = false
        }
    }
}

@Composable
fun CircleHSSampleCustomThumb() {

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

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {

            thumbSize = DpSize(32.dp, 32.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun CircleHSSampleCustomThumbWithIcon() {

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

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            thumbInside = false
            contentPadding = PaddingValues(16.dp)
            thumbSize = DpSize(32.dp, 32.dp)
            thumb = customThumb
        }
    }
}

@Composable
fun CircleHSSampleReversedY() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            reversedY = true
        }
    }
}

@Composable
fun CircleHSSampleReversedX() {

    ColorPicker(
        modifier = Modifier.wrapContentSize()
    ) {

        CircleHS(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            reversedX = true
        }
    }
}

@Preview(
    showBackground = true,
    name = "Basic circle HS"
)
@Composable
private fun CircleHSBasicSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleHSSampleBasic()
        CircleHSSampleThumbOutside()
    }
}

@Preview(
    showBackground = true,
    name = "Circle HS with custom thumb"
)
@Composable
private fun CircleHSCustomThumbSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleHSSampleCustomThumbWithIcon()
        CircleHSSampleCustomThumb()
    }
}

@Preview(
    showBackground = true,
    name = "Circle HS with reversed x and y"
)
@Composable
private fun CircleHSReversedSamplesPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleHSSampleReversedY()
        CircleHSSampleReversedX()
    }
}





