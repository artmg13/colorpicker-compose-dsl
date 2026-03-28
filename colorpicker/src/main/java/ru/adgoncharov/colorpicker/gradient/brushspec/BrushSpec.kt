package ru.adgoncharov.colorpicker.gradient.brushspec

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

sealed interface BrushSpec {

    @Composable
    fun build(): Brush
}