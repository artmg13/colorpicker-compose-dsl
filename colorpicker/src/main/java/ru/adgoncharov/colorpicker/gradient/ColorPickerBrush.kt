package ru.adgoncharov.colorpicker.gradient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ColorPickerBrush(
    val baseColor: Color? = null,
    val layers: List<Brush>
) {

    companion object {
        @Composable
        fun hueHorizontalSlider(reversed: Boolean = false): ColorPickerBrush {

            return rememberColorPickerBrush(reversed) {
                horizontalGradient(ColorPickerGradients.Hue, reversed)
            }
        }

        @Composable
        fun hueVerticalSlider(reversed: Boolean = false): ColorPickerBrush {

            return rememberColorPickerBrush(reversed) {
                verticalGradient(ColorPickerGradients.Hue, reversed)
            }
        }

        @Composable
        fun saturationHorizontalSlider(
            baseColor: Color? = null,
            reversed: Boolean = false
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed) {
                this.baseColor = baseColor
                horizontalGradient(ColorPickerGradients.Saturation, reversed)
            }
        }

        @Composable
        fun saturationVerticalSlider(
            baseColor: Color? = null,
            reversed: Boolean = false
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed) {
                this.baseColor = baseColor
                verticalGradient(ColorPickerGradients.Saturation, reversed)
            }
        }

        @Composable
        fun valueHorizontalSlider(
            baseColor: Color? = null,
            reversed: Boolean = false
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed) {
                this.baseColor = baseColor
                horizontalGradient(ColorPickerGradients.Value, reversed)
            }
        }

        @Composable
        fun valueVerticalSlider(baseColor: Color? = null, reversed: Boolean = false): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed) {
                this.baseColor = baseColor
                verticalGradient(ColorPickerGradients.Value, reversed)
            }
        }

        @Composable
        fun alphaHorizontalSlider(
            baseColor: Color,
            reversed: Boolean = false,
            showGrid: Boolean = true,
            gridSize: Dp = 8.dp,
            lightColor: Color = Color.White,
            darkColor: Color = Color.LightGray
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed, showGrid) {
                if (showGrid)
                    checkerboard(gridSize, lightColor, darkColor)

                horizontalGradient(ColorPickerGradients.alpha(baseColor), reversed)
            }
        }

        @Composable
        fun alphaVerticalSlider(
            baseColor: Color,
            reversed: Boolean = false,
            showGrid: Boolean = true,
            gridSize: Dp = 8.dp,
            lightColor: Color = Color.White,
            darkColor: Color = Color.LightGray
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversed, showGrid) {
                if (showGrid)
                    checkerboard(gridSize, lightColor, darkColor)

                verticalGradient(ColorPickerGradients.alpha(baseColor), reversed)
            }
        }

        @Composable
        fun hueRing(reversed: Boolean = true): ColorPickerBrush {

            return rememberColorPickerBrush(reversed) {
                sweepGradient(ColorPickerGradients.Hue, reversed)
            }
        }


        @Composable
        fun rectangleSV(
            baseColor: Color,
            reversedHorizontal: Boolean = false,
            reversedVertical: Boolean = true
        ): ColorPickerBrush {

            return rememberColorPickerBrush(baseColor, reversedHorizontal, reversedVertical) {
                this.baseColor = baseColor

                horizontalGradient(ColorPickerGradients.Saturation, reversedHorizontal)
                verticalGradient(ColorPickerGradients.Value, reversedVertical)
            }
        }

        @Composable
        fun circleHS(
            reversedHue: Boolean = true,
            reversedSaturation: Boolean = false
        ): ColorPickerBrush {

            return rememberColorPickerBrush(reversedHue, reversedSaturation) {
                sweepGradient(ColorPickerGradients.Hue, reversedHue)
                radialGradient(ColorPickerGradients.Saturation, reversedSaturation)
            }
        }
    }
}

@Composable
fun rememberColorPickerBrush(
    vararg keys: Any?,
    block: ColorPickerBrushBuilder.() -> Unit
): ColorPickerBrush {
    val builder = remember(*keys) {
        ColorPickerBrushBuilder().apply(block)
    }

    return builder.build()
}