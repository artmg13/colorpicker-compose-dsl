package ru.adgoncharov.colorpicker.colorpickerdsl.builder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalColorPickerThumb
import ru.adgoncharov.colorpicker.colorpickerdsl.LocalThumbSize
import ru.adgoncharov.colorpicker.component.RingColorPickerArea
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import kotlin.math.sqrt

@ColorPickerDsl
class RingSliderScope : BaseAreaScope() {
    var value: Float = 0f
    var onValueChange: (Float) -> Unit = {}
    var ringWidth: Dp = 24.dp
    var reversed: Boolean = false
    var contentInsidePadding: RingPadding = RingPadding.INNER
    var gapContentInside: Dp = 0.dp
    var contentInside: (@Composable () -> Unit)? = null
}

enum class RingPadding {
    INNER,
    OUTER,
    RING_WIDTH
}

@Composable
fun RingSlider(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    block: RingSliderScope.() -> Unit
) {
    val config = RingSliderScope().apply(block)

    if (config.contentInside == null) {
        RingSliderDefault(modifier, brush, config)
    } else {
        RingSliderWithContentInside(modifier, brush, config)
    }
}

@Composable
private fun RingSliderDefault(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    config: RingSliderScope
) {
    val thumbSize = config.thumbSize ?: LocalThumbSize.current
    val thumb = config.thumb ?: LocalColorPickerThumb.current

    RingColorPickerArea(
        modifier = Modifier
            .then(modifier),
        value = config.value,
        onValueChange = config.onValueChange,
        brush = brush,
        ringWidth = config.ringWidth,
        contentPadding = config.contentPadding,
        reversed = config.reversed,
        thumbSize = thumbSize,
        thumb = thumb
    )
}

@Composable
private fun RingSliderWithContentInside(
    modifier: Modifier = Modifier,
    brush: ColorPickerBrush,
    config: RingSliderScope
) {
    var ringSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    val contentInsidePadding by remember(config.ringWidth, config.contentInsidePadding) {
        derivedStateOf {
            if (ringSize == IntSize.Zero) return@derivedStateOf 0.dp

            with(density) {
                val outerRadius = ringSize.width / 2f
                val ringWidthPx = config.ringWidth.toPx()

                when (config.contentInsidePadding) {
                    RingPadding.INNER -> {
                        val innerRadius = outerRadius - ringWidthPx
                        val squareSide = innerRadius * sqrt(2f)
                        (outerRadius - squareSide / 2f).toDp()
                    }

                    RingPadding.OUTER -> {
                        val squareSide = outerRadius * sqrt(2f)
                        (outerRadius - squareSide / 2f).toDp()
                    }

                    RingPadding.RING_WIDTH -> config.ringWidth
                }
            }
        }
    }

    Box(
        modifier = modifier
            .onSizeChanged { ringSize = it },
        contentAlignment = Alignment.Center
    ) {
        RingSliderDefault(Modifier.fillMaxSize(), brush, config)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentInsidePadding)
                .padding(config.gapContentInside),
            contentAlignment = Alignment.Center
        ) {
            config.contentInside?.invoke()
        }
    }
}