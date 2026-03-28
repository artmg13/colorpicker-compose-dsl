package ru.adgoncharov.colorpickerdemo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.colorpicker.ColorPickerState
import ru.adgoncharov.colorpicker.rememberColorPickerState
import java.util.Locale

@Composable
fun ColorCard(
    modifier: Modifier = Modifier,
    state: ColorPickerState
) {

    Column(
        modifier = modifier.background(Color.White).padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(state.color)
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = rgbString(state),
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = hsvString(state),
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Hex: ${state.hex}",
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "HexRGB: ${state.hexRGB}",
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold
        )
    }
}

fun rgbString(state: ColorPickerState): String {
    return String.format(
        Locale.ENGLISH, "RGB: %3d/%3d/%3d",
        (state.red * 255).toInt(),
        (state.green * 255).toInt(),
        (state.blue * 255).toInt(),
    )
}

fun hsvString(state: ColorPickerState): String {
    return String.format(
        Locale.ENGLISH, "HSV: %3d/%3d%%/%3d%%",
        (state.hue * 360f).toInt(),
        (state.saturation * 100).toInt(),
        (state.value * 100).toInt(),
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ColorCardPreview() {
    val state = rememberColorPickerState(Color.Blue)

    ColorCard(
        modifier = Modifier.fillMaxWidth(),
        state = state
    )
}