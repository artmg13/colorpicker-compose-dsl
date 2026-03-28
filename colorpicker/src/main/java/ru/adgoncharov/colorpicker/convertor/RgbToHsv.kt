package ru.adgoncharov.colorpicker.convertor

fun rgbToHsv(r: Float, g: Float, b: Float): Triple<Float, Float, Float> {
    val max = maxOf(r, g, b)
    val min = minOf(r, g, b)
    val delta = max - min

    val h = when {
        delta == 0f -> 0f
        max == r -> 60f * (((g - b) / delta) % 6)
        max == g -> 60f * (((b - r) / delta) + 2)
        else     -> 60f * (((r - g) / delta) + 4)
    }.let { if (it < 0) it + 360f else it }

    val s = if (max == 0f) 0f else delta / max
    val v = max

    return Triple(h, s, v)
}