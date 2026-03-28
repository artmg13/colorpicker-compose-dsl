package ru.adgoncharov.colorpicker.area.areastyle

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

data class ColorPickerAreaStyle(
    val shape: Shape = RectangleShape,
    val contentPadding: PaddingValues = PaddingValues(0.dp),
    val clip: Boolean = true,
    val draw: DrawScope.() -> Unit = {},
)