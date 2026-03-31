package ru.adgoncharov.colorpicker.gradient.brushspec

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ai generated
data class CheckerboardSpec(
    val gridSize: Dp = 8.dp,
    val lightColor: Color = Color.White,
    val darkColor: Color = Color.LightGray
) : BrushSpec {

    @Composable
    override fun build(): Brush {
        val density = LocalDensity.current
        return remember(gridSize, lightColor, darkColor, density) {
            val sizePx = with(density) { gridSize.toPx() }.toInt()
            if (sizePx <= 0) return@remember SolidColor(Color.Transparent)

            val bitmap = ImageBitmap(sizePx * 2, sizePx * 2)
            val canvas = Canvas(bitmap)
            val paint = Paint()

            paint.color = lightColor
            canvas.drawRect(0f, 0f, sizePx.toFloat(), sizePx.toFloat(), paint)
            canvas.drawRect(sizePx.toFloat(), sizePx.toFloat(), sizePx * 2f, sizePx * 2f, paint)

            paint.color = darkColor
            canvas.drawRect(sizePx.toFloat(), 0f, sizePx * 2f, sizePx.toFloat(), paint)
            canvas.drawRect(0f, sizePx.toFloat(), sizePx.toFloat(), sizePx * 2f, paint)

            ShaderBrush(
                ImageShader(
                    image = bitmap,
                    tileModeX = TileMode.Repeated,
                    tileModeY = TileMode.Repeated
                )
            )
        }
    }
}