package ru.adgoncharov.colorpicker.area.limiter

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.CircleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.HorizontalSliderLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RectangleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RingLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.VerticalSliderLimiter

@Stable
interface Limiter {

    fun limitPosition(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    fun normalizedPosition(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    fun normalizedToPosition(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset

    companion object {

        fun circle(
            isThumbInside: Boolean = true,
            reversedX: Boolean = false,
            reversedY: Boolean = false
        ): Limiter = CircleLimiter(
            isThumbInside = isThumbInside,
            reversedX = reversedX,
            reversedY = reversedY
        )

        fun rectangle(
            isThumbInside: Boolean = true,
            reversedX: Boolean = false,
            reversedY: Boolean = false
        ): Limiter = RectangleLimiter(
            isThumbInside = isThumbInside,
            reversedX = reversedX,
            reversedY = reversedY
        )

        fun ring(
            ringWidthPx: Float,
            reversed: Boolean = false
        ): Limiter = RingLimiter(ringWidthPx = ringWidthPx, reversed = reversed)

        fun horizontalSlider(
            isThumbInside: Boolean = true,
            reversed: Boolean = false,
        ): Limiter = HorizontalSliderLimiter(isThumbInside = isThumbInside, reversed = reversed)

        fun verticalSlider(
            isThumbInside: Boolean = true,
            reversed: Boolean = false
        ): Limiter = VerticalSliderLimiter(isThumbInside = isThumbInside, reversed = reversed)
    }
}