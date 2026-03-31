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
 * Реализация [Limiter] для радиальных (круговых) областей выбора цвета.
 *
 * Логика основана на полярной системе координат:
 * - **Value X**: Угол (Hue), нормализованный от 0 до 1 (соответствует 0–360°).
 * - **Value Y**: Расстояние от центра (Saturation/Value), нормализованное от 0 до 1.
 * @property isThumbInside Если true, расчеты производятся так, чтобы весь указатель (thumb)
 * всегда оставался в пределах круга. Если false, центр указателя может достигать края круга.
 * @property reversedX Инвертирует направление отсчета угла (по часовой / против часовой).
 * @property reversedY Инвертирует направление радиуса (от центра к краю / от края к центру).
 */
internal class CircleLimiter(
    val isThumbInside: Boolean = true,
    val reversedX: Boolean = false,
    val reversedY: Boolean = false,
) : Limiter {

    override fun limitPoint(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {

        val radius = countRadius(sliderSize, thumbSize)
        val center = countCenter(sliderSize, thumbSize)

        val target = if (isThumbInside) position else toCenter(position, thumbSize)

        val limited = countLimitedPosition(center, target, radius)

        return if (isThumbInside) limited else fromCenter(limited, thumbSize)
    }

    override fun pointToValues(
        position: Offset,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {
        val radius = countRadius(sliderSize, thumbSize)
        val center = countCenter(sliderSize, thumbSize)

        val target = if (isThumbInside) position else toCenter(position, thumbSize)

        val normalizedAngleDeg = countNormalizedDegree(center, target)

        val distance = countDistance(center, target)
        val normalizedRadius = countNormalizedRadius(distance, radius)

        return Offset(
            x = if (reversedX) 1f - normalizedAngleDeg else normalizedAngleDeg, // градусы 0..1
            y = if (reversedY) 1f - normalizedRadius else normalizedRadius // радиус от 0..1
        )
    }

    override fun valuesToPoint(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset {
        val radius = countRadius(sliderSize, thumbSize)
        val center = countCenter(sliderSize, thumbSize)

        val valX = if (reversedX) 1f - valueX else valueX
        val valY = if (reversedY) 1f - valueY else valueY

        val angle = valX * 2f * PI.toFloat()

        val x = valY * radius * cos(angle)
        val y = -valY * radius * sin(angle) // экранная инверсия

        return Offset(x = center.x + x, y = center.y + y)
    }

    private fun countRadius(sliderSize: IntSize, thumbSize: IntSize): Float {
        val base = minOf(sliderSize.width, sliderSize.height) / 2f

        return if (isThumbInside) base - thumbSize.width / 2f else base
    }

    private fun countCenter(sliderSize: IntSize, thumbSize: IntSize): Offset {
        return Offset(
            x = (sliderSize.width - thumbSize.width) / 2f,
            y = (sliderSize.height - thumbSize.height) / 2f
        )
    }

    private fun countDx(center: Offset, position: Offset): Float {
        return position.x - center.x
    }

    private fun countDy(center: Offset, position: Offset): Float {
        return position.y - center.y
    }

    private fun countDistance(center: Offset, position: Offset): Float {
        val dx = countDx(center, position)
        val dy = countDy(center, position)

        return sqrt(dx * dx + dy * dy)
    }

    private fun countDistance(dx: Float, dy: Float): Float {
        return sqrt(dx * dx + dy * dy)
    }

    private fun countLimitedPosition(center: Offset, position: Offset, radius: Float): Offset {

        val dx = countDx(center, position)
        val dy = countDy(center, position)

        val distance = countDistance(dx, dy)

        return if (distance <= radius) {
            position
        } else {
            val scale = radius / distance
            Offset(
                x = center.x + dx * scale,
                y = center.y + dy * scale
            )
        }
    }

    private fun countNormalizedRadius(distance: Float, radius: Float): Float {
        return (distance / radius).coerceIn(0f, 1f)
    }

    private fun toCenter(position: Offset, thumbSize: IntSize): Offset {
        return Offset(
            x = position.x + thumbSize.width / 2f,
            y = position.y + thumbSize.height / 2f
        )
    }

    private fun fromCenter(center: Offset, thumbSize: IntSize): Offset {
        return Offset(
            x = center.x - thumbSize.width / 2f,
            y = center.y - thumbSize.height / 2f
        )
    }

    private fun countNormalizedDegree(center: Offset, position: Offset): Float {
        val dx = position.x - center.x
        val dy = center.y - position.y

        val angleRad = atan2(dy, dx)
        val normalized = (angleRad / (2 * PI)).toFloat()

        return if (normalized < 0f) normalized + 1f else normalized
    }
}