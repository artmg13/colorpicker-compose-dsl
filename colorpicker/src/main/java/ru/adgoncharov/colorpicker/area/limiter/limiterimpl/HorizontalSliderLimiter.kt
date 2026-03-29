package ru.adgoncharov.colorpicker.area.limiter.limiterimpl

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.Limiter

internal class HorizontalSliderLimiter(
    private val isThumbInside: Boolean = true,
    val reversed: Boolean = false,
) : Limiter {

    override fun limitPosition(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val (minX, maxX) = if (isThumbInside) {
            0f to (sliderSize.width - thumbSize.width).toFloat()
        } else {
            -thumbSize.width / 2f to (sliderSize.width - thumbSize.width / 2f)
        }

        val y = (sliderSize.height - thumbSize.height) / 2f

        return Offset(
            x = position.x.coerceIn(minX, maxX),
            y = y
        )
    }

    override fun normalizedPosition(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val width = sliderSize.width.toFloat()
        val thumb = thumbSize.width.toFloat()

        val (min, max) = if (isThumbInside) {
            0f to (width - thumb)
        } else {
            -thumb / 2f to (width - thumb / 2f)
        }

        val normalized = normalize(position.x, min, max)

        return Offset(
            x = if (reversed) 1f - normalized else normalized,
            y = 0f
        )
    }

    override fun normalizedToPosition(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val width = sliderSize.width.toFloat()
        val thumb = thumbSize.width.toFloat()

        val (min, max) = if (isThumbInside) {
            0f to (width - thumb)
        } else {
            -thumb / 2f to (width - thumb / 2f)
        }

        val valX = if (reversed) 1f - valueX else valueX
        val x = denormalize(valX, min, max)
        val y = (sliderSize.height - thumbSize.height) / 2f

        return Offset(x, y)
    }

    private fun normalize(value: Float, min: Float, max: Float): Float {
        return if (max - min == 0f) 0f else (value - min) / (max - min)
    }

    private fun denormalize(value: Float, min: Float, max: Float): Float {
        return min + value * (max - min)
    }
}