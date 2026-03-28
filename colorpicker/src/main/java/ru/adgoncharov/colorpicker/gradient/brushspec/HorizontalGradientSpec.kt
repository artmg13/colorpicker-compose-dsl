package ru.adgoncharov.colorpicker.gradient.brushspec

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class HorizontalGradientSpec(
    val colors: List<Color>,
    val reversed: Boolean = false
) : BrushSpec {

    @Composable
    override fun build(): Brush {
        return remember(colors, reversed) {
            Brush.horizontalGradient(
                if (reversed) colors.reversed() else colors
            )
        }
    }
}