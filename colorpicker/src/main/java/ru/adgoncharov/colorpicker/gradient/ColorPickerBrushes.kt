package ru.adgoncharov.colorpicker.gradient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import ru.adgoncharov.colorpicker.area.limiter.limiterimpl.VerticalSliderLimiter
import ru.adgoncharov.colorpicker.gradient.brushspec.HorizontalGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.RadialGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.SweepGradientSpec
import ru.adgoncharov.colorpicker.gradient.brushspec.VerticalGradientSpec


object ColorPickerBrushes {

    @Composable
    fun hueHorizontalSlider(reversed: Boolean = false): ColorPickerBrush {

        val spec = remember(reversed) {
            HorizontalGradientSpec(ColorPickerGradients.Hue, reversed)
        }

        return rememberColorPickerBrush(spec) { layer(spec) }
    }

    @Composable
    fun hueVerticalSlider(reversed: Boolean = false): ColorPickerBrush {

        val spec = remember(reversed) {
            VerticalGradientSpec(ColorPickerGradients.Hue, reversed)
        }

        return rememberColorPickerBrush(spec) { layer(spec) }
    }

    @Composable
    fun saturationHorizontalSlider(
        baseColor: Color? = null,
        reversed: Boolean = false
    ): ColorPickerBrush {

        val spec = remember(reversed) {
            HorizontalGradientSpec(ColorPickerGradients.Saturation, reversed)
        }

        return rememberColorPickerBrush(baseColor, spec) {
            this.baseColor = baseColor
            layer(spec)
        }
    }

    @Composable
    fun saturationVerticalSlider(
        baseColor: Color? = null,
        reversed: Boolean = false
    ): ColorPickerBrush {

        val spec = remember(reversed) {
            VerticalGradientSpec(ColorPickerGradients.Saturation, reversed)
        }

        return rememberColorPickerBrush(baseColor, spec) {
            this.baseColor = baseColor
            layer(spec)
        }
    }

    @Composable
    fun valueHorizontalSlider(
        baseColor: Color? = null,
        reversed: Boolean = false
    ): ColorPickerBrush {

        val spec = remember(reversed) {
            HorizontalGradientSpec(ColorPickerGradients.Value, reversed)
        }

        return rememberColorPickerBrush(baseColor, spec) {
            this.baseColor = baseColor
            layer(spec)
        }
    }

    @Composable
    fun valueVerticalSlider(baseColor: Color? = null, reversed: Boolean = false): ColorPickerBrush {

        val spec = remember(reversed) {
            VerticalGradientSpec(ColorPickerGradients.Value, reversed)
        }

        return rememberColorPickerBrush(baseColor, spec) {
            this.baseColor = baseColor
            layer(spec)
        }
    }

    @Composable
    fun alphaHorizontalSlider(baseColor: Color, reversed: Boolean = false): ColorPickerBrush {

        val spec = remember(baseColor, reversed) {
            HorizontalGradientSpec(
                ColorPickerGradients.alpha(baseColor),
                reversed
            )
        }

        return rememberColorPickerBrush(spec) {
            layer(spec)
        }
    }

    @Composable
    fun alphaVerticalSlider(baseColor: Color, reversed: Boolean = false): ColorPickerBrush {

        val spec = remember(baseColor, reversed) {
            VerticalGradientSpec(ColorPickerGradients.alpha(baseColor), reversed)
        }

        return rememberColorPickerBrush(spec) {
            layer(spec)
        }
    }

    @Composable
    fun hueRing(reversed: Boolean = true): ColorPickerBrush {

        val spec = remember(reversed) {
            SweepGradientSpec(ColorPickerGradients.Hue, reversed)
        }

        return rememberColorPickerBrush(spec) {
            layer(spec)
        }
    }


    @Composable
    fun svRectangle(
        baseColor: Color,
        reversedHorizontal: Boolean = false,
        reversedVertical: Boolean = true
    ): ColorPickerBrush {

        val firstSpec = remember(reversedHorizontal) {
            HorizontalGradientSpec(ColorPickerGradients.Saturation, reversedHorizontal)
        }

        val secondSpec = remember(reversedVertical) {
            VerticalGradientSpec(ColorPickerGradients.Value, reversedVertical)
        }

        return rememberColorPickerBrush(baseColor, firstSpec, secondSpec) {
            this.baseColor = baseColor

            layer(firstSpec)

            layer(secondSpec)
        }
    }

    @Composable
    fun hsCircle(
        reversedHue: Boolean = true,
        reversedSaturation: Boolean = false
    ): ColorPickerBrush {

        val firstSpec = remember(reversedHue) {
            SweepGradientSpec(ColorPickerGradients.Hue, reversedHue)
        }

        val secondSpec = remember(reversedSaturation) {
            RadialGradientSpec(ColorPickerGradients.Saturation, reversedSaturation)
        }

        return rememberColorPickerBrush(firstSpec, secondSpec) {
            layer(firstSpec)

            layer(secondSpec)
        }
    }

    @Composable
    fun svCircle(
        baseColor: Color,
        reversedSaturation: Boolean = true,
        reversedValue: Boolean = false
    ): ColorPickerBrush {

        val firstSpec = remember(reversedSaturation) {
            SweepGradientSpec(ColorPickerGradients.Saturation, reversedSaturation)
        }

        val secondSpec = remember(reversedValue) {
            RadialGradientSpec(ColorPickerGradients.Value, reversedValue)
        }

        return rememberColorPickerBrush(baseColor, firstSpec, secondSpec) {
            this.baseColor = baseColor

            layer(firstSpec)

            layer(secondSpec)
        }
    }
}