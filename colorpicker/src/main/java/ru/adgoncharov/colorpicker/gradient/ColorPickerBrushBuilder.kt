package ru.adgoncharov.colorpicker.gradient

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.gradient.brushspec.BrushSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.CheckerboardSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.HorizontalGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.RadialGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.SweepGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.VerticalGradientSpec

class ColorPickerBrushBuilder {

    var baseColor: Color? = null
    private val layers = mutableListOf<BrushSpec>()

    fun layer(spec: BrushSpec) {
        layers += spec
    }

    fun horizontalGradient(colors: List<Color>, reversed: Boolean = false) {
        layer(HorizontalGradientSpec(colors, reversed))
    }

    fun verticalGradient(colors: List<Color>, reversed: Boolean = false) {
        layer(VerticalGradientSpec(colors, reversed))
    }

    fun radialGradient(colors: List<Color>, reversed: Boolean = false) {
        layer(RadialGradientSpec(colors, reversed))
    }

    fun sweepGradient(colors: List<Color>, reversed: Boolean = false) {
        layer(SweepGradientSpec(colors, reversed))
    }

    fun checkerboard(gridSize: Dp = 8.dp, lightColor: Color = Color.White, darkColor: Color = Color.LightGray) {
        layer(CheckerboardSpec(gridSize = gridSize, lightColor, darkColor))
    }

    @Composable
    fun build(): ColorPickerBrush {
        return ColorPickerBrush(
            baseColor = baseColor,
            layers = layers.map { it.build() }
        )
    }
}