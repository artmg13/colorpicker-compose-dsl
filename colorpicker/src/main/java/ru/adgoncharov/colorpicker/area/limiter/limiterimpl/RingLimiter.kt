package ru.adgoncharov.colorpicker.area.limiter.limiterimpl

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Реализация [Limiter] для кольцевых областей (например, Hue Ring).
 *
 * Фиксирует указатель на окружности, проходящей ровно посередине толщины кольца.
 */
internal class RingLimiter(
    private val ringWidthPx: Float,
    val reversed: Boolean = false
) : Limiter {

    override fun limitPoint(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val center = countCenter(sliderSize, thumbSize)

        val dx = position.x - center.x
        val dy = position.y - center.y

        val distance = sqrt(dx * dx + dy * dy)
        val safeDistance = if (distance == 0f) 0.0001f else distance

        val radius = countRingRadius(sliderSize, thumbSize)

        val scale = radius / safeDistance

        return Offset(
            x = center.x + dx * scale,
            y = center.y + dy * scale
        )
    }

    override fun pointToValues(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val center = countCenter(sliderSize, thumbSize)

        val dx = position.x - center.x
        val dy = center.y - position.y

        val angle = atan2(dy, dx)
        val normalized = ((angle / (2 * PI)) + 1f).toFloat() % 1f

        return Offset(
            x = if (reversed) 1f - normalized else normalized,
            y = 0f
        )
    }

    override fun valuesToPoint(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val center = countCenter(sliderSize, thumbSize)

        val radius = countRingRadius(sliderSize, thumbSize)

        val valX = if (reversed) 1f - valueX else valueX
        val angle = valX * 2f * PI.toFloat()

        val x = radius * cos(angle)
        val y = -radius * sin(angle)

        return Offset(
            x = center.x + x,
            y = center.y + y
        )
    }

    // --- helpers ---

    private fun countRingRadius(
        sliderSize: IntSize,
        thumbSize: IntSize,
    ): Float {
        val outer = minOf(sliderSize.width, sliderSize.height) / 2f
        val inner = (outer - ringWidthPx)

        return (inner + outer) / 2f
    }

    private fun countCenter(
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {
        return Offset(
            x = (sliderSize.width - thumbSize.width) / 2f,
            y = (sliderSize.height - thumbSize.height) / 2f
        )
    }
}