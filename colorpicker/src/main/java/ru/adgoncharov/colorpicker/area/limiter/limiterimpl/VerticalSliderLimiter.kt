package ru.adgoncharov.colorpicker.area.limiter.limiterimpl

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.Limiter

class VerticalSliderLimiter(
    private val isThumbInside: Boolean = true
) : Limiter {

    override fun limitPosition(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val (minY, maxY) = if (isThumbInside) {
            0f to (sliderSize.height - thumbSize.height).toFloat()
        } else {
            -thumbSize.height / 2f to (sliderSize.height - thumbSize.height / 2f)
        }

        val x = (sliderSize.width - thumbSize.width) / 2f

        return Offset(
            x = x,
            y = position.y.coerceIn(minY, maxY)
        )
    }

    override fun normalizedPosition(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val height = sliderSize.height.toFloat()
        val thumb = thumbSize.height.toFloat()

        val (min, max) = if (isThumbInside) {
            0f to (height - thumb)
        } else {
            -thumb / 2f to (height - thumb / 2f)
        }

        val normalized = normalize(position.y, min, max)

        return Offset(
            x = 0f, // 🔥 снизу вверх = 0 → 1
            y = 1f - normalized
        )
    }

    override fun normalizedToPosition(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val height = sliderSize.height.toFloat()
        val thumb = thumbSize.height.toFloat()

        val value = 1f - valueY

        val (min, max) = if (isThumbInside) {
            0f to (height - thumb)
        } else {
            -thumb / 2f to (height - thumb / 2f)
        }

        val y = denormalize(value, min, max)
        val x = (sliderSize.width - thumbSize.width) / 2f

        return Offset(x, y)
    }

    private fun normalize(value: Float, min: Float, max: Float): Float {
        return if (max - min == 0f) 0f else (value - min) / (max - min)
    }

    private fun denormalize(value: Float, min: Float, max: Float): Float {
        return min + value * (max - min)
    }
}