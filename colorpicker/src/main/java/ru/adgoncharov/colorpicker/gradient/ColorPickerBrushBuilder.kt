package ru.adgoncharov.colorpicker.gradient

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.gradient.brushspec.BrushSpec

class ColorPickerBrushBuilder {

    var baseColor: Color? = null
    private val layers = mutableListOf<BrushSpec>()

    fun layer(spec: BrushSpec) {
        layers += spec
    }

    @Composable
    fun build(): ColorPickerBrush {
        return ColorPickerBrush(
            baseColor = baseColor,
            layers = layers.map { it.build() }
        )
    }
}