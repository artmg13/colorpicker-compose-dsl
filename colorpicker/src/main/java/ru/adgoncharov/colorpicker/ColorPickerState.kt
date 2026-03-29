package ru.adgoncharov.colorpicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import ru.adgoncharov.colorpicker.convertor.rgbToHsvNormalized

@Composable
fun rememberColorPickerState(initialColor: Color = Color.White): ColorPickerState {
    return remember {
        ColorPickerState(initialColor)
    }
}

@Stable
class ColorPickerState(
    initialColor: Color
) {
    var hue by mutableFloatStateOf(0f)
        private set
    var saturation by mutableFloatStateOf(0f)
        private set
    var value by mutableFloatStateOf(0f)
        private set
    var alpha by mutableFloatStateOf(initialColor.alpha)
        private set

    init {
        val hsv = rgbToHsvNormalized(
            initialColor.red,
            initialColor.green,
            initialColor.blue
        )
        hue = hsv.first
        saturation = hsv.second
        value = hsv.third
    }

    val red get() = color.red
    val green get() = color.green
    val blue get() = color.blue

    val color: Color
        get() = Color.hsv(hue * 360f, saturation, value, alpha)

    fun changeHue(h: Float) {
        hue = h.coerceIn(0f, 1f)
    }

    fun changeSaturation(s: Float) {
        saturation = s.coerceIn(0f, 1f)
    }

    fun changeValue(v: Float) {
        value = v.coerceIn(0f, 1f)
    }

    fun changeAlpha(a: Float) {
        alpha = a.coerceIn(0f, 1f)
    }

    val hex: String
        get() = String.format("#%08X", (color.toArgb()))

    val hexRGB: String
        get() = String.format("#%06X", (0xFFFFFF and color.toArgb()))
}