package ru.adgoncharov.colorpicker.area.limiter

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.CircleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.HorizontalSliderLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RectangleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RingLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.VerticalSliderLimiter

/**
 * Интерфейс для управления логикой перемещения и преобразования координат указателя (thumb).
 * Limiter выполняет роль "прослойки" между жестами пользователя и состоянием цвета:
 * 1. **Ограничение:** Не дает указателю выйти за пределы (например, за границы круга).
 * 2. **Проекция:** Преобразует пиксели экрана в значения компонентов цвета (0..1).
 * 3. **Восстановление:** Вычисляет позицию указателя на основе текущего цвета.
 */
@Stable
interface Limiter {

    /**
     * Ограничивает [position] касания в рамках доступной области.
     * @param position Сырая координата касания относительно левого верхнего угла области.
     * @param sliderSize Общий размер области выбора.
     * @param thumbSize Размер указателя (используется для учета отступов, если [isThumbInside] = true).
     * @return Безопасная координата [Offset] для отрисовки и дальнейших расчетов.
     */
    fun limitPoint(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    /**
     * Преобразует экранную координату [position] в нормализованные значения (0.0 - 1.0).
     * @param position Скорректированная позиция (рекомендуется передавать результат [limitPoint]).
     * @param sliderSize Общий размер области выбора.
     * @param thumbSize Размер указателя.
     * @return [Offset], где [Offset.x] и [Offset.y] — это значения компонентов цвета от 0f до 1f.
     */
    fun pointToValues(position: Offset, sliderSize: IntSize, thumbSize: IntSize): Offset

    /**
     * Обратное преобразование: вычисляет экранную координату на основе значений [valueX] и [valueY].
     * @param valueX Значение по горизонтали или углу (0..1).
     * @param valueY Значение по вертикали или радиусу (0..1).
     * @return Физическая координата [Offset] для размещения указателя на экране.
     */
    fun valuesToPoint(
        valueX: Float,
        valueY: Float,
        sliderSize: IntSize,
        thumbSize: IntSize
    ): Offset

    companion object {

        /**
         * Создает ограничитель для радиальных областей.
         * @param isThumbInside Определяет, может ли указатель выходить за визуальный край круга.
         * @param reversedX Позволяет поменять отсчет градусов (по умолчанию против часовой стрелки для перевернутой оси y).
         * @param reversedY Позволяет поменять точку отсчета радиуса с "от центра" на "от края окружности".
         * @return [Limiter], где нормализованный X — это угол (0..1), а Y — радиус (0..1).
         * @see CircleLimiter
         */
        fun circle(
            isThumbInside: Boolean = true,
            reversedX: Boolean = false,
            reversedY: Boolean = false
        ): Limiter = CircleLimiter(
            isThumbInside = isThumbInside,
            reversedX = reversedX,
            reversedY = reversedY
        )

        /**
         * Создает ограничитель для прямоугольных областей.
         * @param isThumbInside Определяет, может ли указатель выходить за визуальный край прямоугольника.
         * @param reversedX Позволяет инвертировать ось x.
         * @param reversedY Позволяет инвертировать ось y.
         * @return [Limiter], где нормализованный X — это нормализованное отдаление указателя по оси x (0..1),
         * а Y — нормализованное отдаление указателя по оси y (0..1).
         * @see RectangleLimiter
         */
        fun rectangle(
            isThumbInside: Boolean = true,
            reversedX: Boolean = false,
            reversedY: Boolean = false
        ): Limiter = RectangleLimiter(
            isThumbInside = isThumbInside,
            reversedX = reversedX,
            reversedY = reversedY
        )

        /**
         * Создает ограничитель для кольцевых областей.
         * @param ringWidthPx Желаемая толщина кольца в пикселях.
         * @param reversed Инвертирует направление отсчета градусов.
         * @return [Limiter], где нормализованный X — угол (0..1), Y — позиция на ширине кольца.
         * @see RingLimiter
         */
        fun ring(
            ringWidthPx: Float,
            reversed: Boolean = false
        ): Limiter = RingLimiter(ringWidthPx = ringWidthPx, reversed = reversed)

        /**
         * Создает ограничитель для горизонтальных слайдеров.
         * @param isThumbInside Определяет, может ли указатель выходить за границы слайдера.
         * @param reversed Позволяет инвертировать ось X.
         * @return [HorizontalSliderLimiter], где нормализованный X - это расстояние от начала координат по оси x (0..1),
         * а Y - координата середины высоты слайдера.
         * @see HorizontalSliderLimiter
         */
        fun horizontalSlider(
            isThumbInside: Boolean = true,
            reversed: Boolean = false,
        ): Limiter = HorizontalSliderLimiter(isThumbInside = isThumbInside, reversed = reversed)

        /**
         * Создает ограничитель для вертикальных слайдеров.
         * @param isThumbInside Определяет, может ли указатель выходить за границы слайдера.
         * @param reversed Позволяет инвертировать ось Y.
         * @return [VerticalSliderLimiter], где нормализованный X - координата середины ширины слайдера,
         * а Y - это расстояние от начала координат по оси y (0..1).
         * @see VerticalSliderLimiter
         */
        fun verticalSlider(
            isThumbInside: Boolean = true,
            reversed: Boolean = false
        ): Limiter = VerticalSliderLimiter(isThumbInside = isThumbInside, reversed = reversed)
    }
}