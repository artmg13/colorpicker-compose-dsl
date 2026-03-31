package ru.adgoncharov.colorpickerdemo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PeachBackground = Color(0xFFFFF1E6)
private val PeachMuted = Color(0xFFFAD2B1)
private val DeepPeach = Color(0xFFE7A977)
private val AccentSage = Color(0xFF8DAA91)

private val LightColorScheme = lightColorScheme(
    primary = AccentSage,
    onPrimary = Color.White,
    primaryContainer = AccentSage.copy(alpha = 0.2f),
    onPrimaryContainer = Color(0xFF2E3D30),

    secondary = DeepPeach,
    onSecondary = Color.White,

    background = PeachBackground,
    surface = Color(0xFFFFF8F3),
    onSurface = Color(0xFF422B1A),

    outline = DeepPeach.copy(alpha = 0.5f)
)

@Composable
fun ColorPickerDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}