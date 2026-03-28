package ru.adgoncharov.colorpicker.convertor

fun hsvToRgb(h: Float, s: Float, v: Float): Triple<Float, Float, Float> {
    val c = v * s
    val x = c * (1 - kotlin.math.abs((h / 60f) % 2 - 1))
    val m = v - c

    val (r1, g1, b1) = when {
        h < 60f  -> Triple(c, x, 0f)
        h < 120f -> Triple(x, c, 0f)
        h < 180f -> Triple(0f, c, x)
        h < 240f -> Triple(0f, x, c)
        h < 300f -> Triple(x, 0f, c)
        else     -> Triple(c, 0f, x)
    }

    return Triple(r1 + m, g1 + m, b1 + m)
}

fun rgbToHsvNormalized(r: Float, g: Float, b: Float): Triple<Float, Float, Float> {
    val (h, s, v) = rgbToHsv(r, g, b)
    return Triple(h / 360f, s, v)
}