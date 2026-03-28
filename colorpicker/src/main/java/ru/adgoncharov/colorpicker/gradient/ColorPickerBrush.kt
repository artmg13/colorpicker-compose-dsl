package ru.adgoncharov.colorpicker.gradient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class ColorPickerBrush(
    val baseColor: Color? = null,
    val layers: List<Brush>
)

@Composable
fun rememberColorPickerBrush(
    vararg keys: Any?,
    block: ColorPickerBrushBuilder.() -> Unit
): ColorPickerBrush {
    val builder = remember(*keys) {
        ColorPickerBrushBuilder().apply(block)
    }

    return builder.build()
}