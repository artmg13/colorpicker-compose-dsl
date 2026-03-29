package ru.adgoncharov.colorpicker.area.areastyle

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

@Immutable
data class ColorPickerAreaStyle(
    val shape: Shape = RectangleShape,
    val contentPadding: PaddingValues = PaddingValues(0.dp),
    val clip: Boolean = true,
    val draw: DrawScope.() -> Unit = {},
) {

    companion object {

        fun rectangle(
            shape: Shape = RectangleShape,
            contentPadding: PaddingValues = PaddingValues(0.dp),
            clip: Boolean = true,
            draw: DrawScope.() -> Unit = {}
        ) = ColorPickerAreaStyle(
            shape = shape,
            contentPadding = contentPadding,
            clip = clip,
            draw = draw
        )

        fun circle(
            shape: Shape = CircleShape,
            contentPadding: PaddingValues = PaddingValues(0.dp),
            clip: Boolean = true,
            draw: DrawScope.() -> Unit = {}
        ) = ColorPickerAreaStyle(
            shape = shape,
            contentPadding = contentPadding,
            clip = clip,
            draw = draw
        )

        fun ring(
            ringWidthPx: Float,
            shape: Shape = CircleShape,
            contentPadding: PaddingValues = PaddingValues(0.dp),
            clip: Boolean = true,
            draw: DrawScope.() -> Unit = {
                val outer = size.minDimension / 2f
                val inner = outer - ringWidthPx

                drawCircle(
                    color = Color.Transparent,
                    radius = inner,
                    blendMode = BlendMode.Clear
                )
            }
        ) = ColorPickerAreaStyle(
            shape = shape,
            contentPadding = contentPadding,
            clip = clip,
            draw = draw,
        )

        fun verticalSlider(
            shape: Shape = RoundedCornerShape(50),
            contentPadding: PaddingValues = PaddingValues(0.dp),
            clip: Boolean = true,
            draw: DrawScope.() -> Unit = {}
        ) = ColorPickerAreaStyle(
            shape = shape,
            contentPadding = contentPadding,
            clip = clip,
            draw = draw
        )

        fun horizontalSlider(
            shape: Shape = RoundedCornerShape(50),
            contentPadding: PaddingValues = PaddingValues(0.dp),
            clip: Boolean = true,
            draw: DrawScope.() -> Unit = {}
        ) = ColorPickerAreaStyle(
            shape = shape,
            contentPadding = contentPadding,
            clip = clip,
            draw = draw
        )
    }
}