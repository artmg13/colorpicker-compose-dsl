package ru.adgoncharov.colorpicker.area.limiter

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize

interface Limiter {

    fun limitPosition(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    fun normalizedPosition(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    fun normalizedToPosition(valueX: Float, valueY: Float, sliderSize: IntSize, thumbSize: IntSize): Offset
}