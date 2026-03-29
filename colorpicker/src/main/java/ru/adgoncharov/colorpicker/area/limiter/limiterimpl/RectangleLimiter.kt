package ru.adgoncharov.colorpicker.area.limiter.limiterimpl

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.Limiter

internal class RectangleLimiter(
    private val isThumbInside: Boolean = true,
    val reversedX: Boolean = false,
    val reversedY: Boolean = true,
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

        val (minY, maxY) = if (isThumbInside) {
            0f to (sliderSize.height - thumbSize.height).toFloat()
        } else {
            -thumbSize.height / 2f to (sliderSize.height - thumbSize.height / 2f)
        }

        return Offset(
            x = position.x.coerceIn(minX, maxX),
            y = position.y.coerceIn(minY, maxY)
        )
    }

    override fun normalizedPosition(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val width = sliderSize.width.toFloat()
        val height = sliderSize.height.toFloat()

        val thumbW = thumbSize.width.toFloat()
        val thumbH = thumbSize.height.toFloat()

        val (minX, maxX) = if (isThumbInside) {
            0f to (width - thumbW)
        } else {
            -thumbW / 2f to (width - thumbW / 2f)
        }

        val (minY, maxY) = if (isThumbInside) {
            0f to (height - thumbH)
        } else {
            -thumbH / 2f to (height - thumbH / 2f)
        }

        val normX = normalize(position.x, minX, maxX)
        val normY = normalize(position.y, minY, maxY)

        return Offset(
            x = if (reversedX) 1f - normX else normX,
            y = if (reversedY) normY else 1f - normY // инверсия Y как у тебя
        )
    }

    override fun normalizedToPosition(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val width = sliderSize.width.toFloat()
        val height = sliderSize.height.toFloat()

        val thumbW = thumbSize.width.toFloat()
        val thumbH = thumbSize.height.toFloat()

        val (minX, maxX) = if (isThumbInside) {
            0f to (width - thumbW)
        } else {
            -thumbW / 2f to (width - thumbW / 2f)
        }

        val (minY, maxY) = if (isThumbInside) {
            0f to (height - thumbH)
        } else {
            -thumbH / 2f to (height - thumbH / 2f)
        }

        val valX = if (reversedX) 1f - valueX else valueX
        val valY = if (reversedY) valueY else 1f - valueY

        val x = denormalize(valX, minX, maxX)
        val y = denormalize(valY, minY, maxY)

        return Offset(x, y)
    }

    // --- helpers ---

    private fun normalize(value: Float, min: Float, max: Float): Float {
        return if (max - min == 0f) 0f else (value - min) / (max - min)
    }

    private fun denormalize(value: Float, min: Float, max: Float): Float {
        return min + value * (max - min)
    }
}