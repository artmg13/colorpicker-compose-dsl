package ru.adgoncharov.colorpicker.area

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyle
import ru.adgoncharov.colorpicker.area.areastyle.ColorPickerAreaStyles
import ru.adgoncharov.colorpicker.area.limiter.Limiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.CircleLimiter
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.RingLimiter
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrush
import ru.adgoncharov.colorpicker.gradient.ColorPickerBrushes
import ru.adgoncharov.colorpicker.thumb.DefaultThumb

@Composable
fun ColorPickerArea(
    modifier: Modifier = Modifier,
    style: ColorPickerAreaStyle,
    valueX: Float = 0f,
    valueY: Float = 0f,
    onValueChangeX: (x: Float) -> Unit = {},
    onValueChangeY: (y: Float) -> Unit = {},
    brush: ColorPickerBrush,
    limiter: Limiter = CircleLimiter(),
    thumbSize: DpSize = DpSize(24.dp, 24.dp),
    thumb: @Composable () -> Unit = { DefaultThumb() }
) {
    var sliderSize by remember { mutableStateOf(IntSize.Zero) }
    val density = LocalDensity.current

    val thumbSizePx = remember(thumbSize, density) {
        with(density) {
            IntSize(
                thumbSize.width.roundToPx(),
                thumbSize.height.roundToPx()
            )
        }
    }

    val handleInput: (Offset) -> Unit = remember(sliderSize, thumbSizePx, limiter) {
        { offset ->
            if (sliderSize == IntSize.Zero) return@remember

            val position = Offset(
                offset.x - thumbSizePx.width / 2f,
                offset.y - thumbSizePx.height / 2f
            )

            val limited = limiter.limitPosition(position, sliderSize, thumbSizePx)
            val normalized = limiter.normalizedPosition(limited, sliderSize, thumbSizePx)

            onValueChangeX(normalized.x)
            onValueChangeY(normalized.y)
        }
    }

    Box(
        modifier = modifier.padding(style.contentPadding),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .then(
                    if (style.clip) Modifier.clip(style.shape)
                    else Modifier
                )
                .onSizeChanged { if (sliderSize != it) sliderSize = it }
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .background(brush.baseColor ?: Color.Transparent)
                .drawWithContent {
                    brush.baseColor?.let {
                        drawRect(it)
                    }

                    brush.layers.forEach { layer ->
                        drawRect(brush = layer)
                    }

                    style.draw(this)
                }
                .pointerInput(Unit) {
                    awaitEachGesture {
                        val down = awaitFirstDown()
                        handleInput(down.position)

                        drag(down.id) { change ->
                            handleInput(change.position)
                            change.consume()
                        }
                    }
                }
        )

        if (sliderSize != IntSize.Zero) {
            val offset = limiter.normalizedToPosition(
                valueX,
                valueY,
                sliderSize,
                thumbSizePx
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .wrapContentSize(align = Alignment.TopStart, unbounded = true)
            ) {
                Box(
                    modifier = Modifier
                        .size(thumbSize)
                        .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) }
                ) {
                    thumb()
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ColorPickerAreaPreview() {

    val density = LocalDensity.current
    val ringWidthPx = with(density) { 48.dp.toPx() }

    var x by remember { mutableStateOf(0f) }
    var y by remember { mutableStateOf(0f) }

    val brush = ColorPickerBrushes.hueRing()
    val limiter = remember(ringWidthPx) { RingLimiter(ringWidthPx) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Slider(
            value = x,
            onValueChange = { x = it },
        )
        Slider(
            value = y,
            onValueChange = { y = it },
        )

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            //text = "X: $x Y: $y ${angleDeg}",
            text = "Result ${x * 360}",
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            //text = "X: $x Y: $y ${angleDeg}",
            text = "Radius ${y * 400}",
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "X: $x Y: $y ",
            fontSize = 24.sp
        )


        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            ColorPickerArea(
                modifier = Modifier
                    .aspectRatio(1f)
                    .size(400.dp),
                style = ColorPickerAreaStyles.ring(ringWidthPx = ringWidthPx),
                valueX = x,
                valueY = 0f,
                onValueChangeX = { x = it },
                onValueChangeY = { },
                brush = brush,
                limiter = limiter,
            )
        }
    }
}