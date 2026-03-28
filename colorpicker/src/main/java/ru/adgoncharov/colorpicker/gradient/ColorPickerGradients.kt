package ru.adgoncharov.colorpicker.gradient

import androidx.compose.ui.graphics.Color

object ColorPickerGradients {

    val Hue: List<Color> = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta,
        Color.Red
    )

    val Saturation: List<Color> =
        listOf(Color.White, Color.Transparent)

    val Value: List<Color> =
        listOf(Color.Black, Color.Transparent)

    fun alpha(color: Color): List<Color> =
        listOf(
            color.copy(alpha = 0f),
            color.copy(alpha = 1f)
        )
}