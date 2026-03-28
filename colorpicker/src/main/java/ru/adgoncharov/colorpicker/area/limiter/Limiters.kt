package ru.adgoncharov.colorpicker.area.limiter

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.CircleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.HorizontalSliderLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RectangleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RingLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.VerticalSliderLimiter

//object Limiters {
//
//    fun circle(isThumbInside: Boolean = true): Limiter = CircleLimiter(isThumbInside)
//
//    fun rectangle(isThumbInside: Boolean = true): Limiter = RectangleLimiter(isThumbInside)
//
//    fun ring(density: Density, ringWidth: Dp): Limiter = RingLimiter(density, ringWidth)
//
//    fun horizontalSlider(isThumbInside: Boolean = true): Limiter = HorizontalSliderLimiter(isThumbInside)
//
//    fun verticalSlider(isThumbInside: Boolean = true): Limiter = VerticalSliderLimiter(isThumbInside)
//}