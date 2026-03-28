package ru.adgoncharov.colorpickerdemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val PeachBackground = Color(0xFFFFF1E6) // Тот самый нежный персик
private val PeachMuted = Color(0xFFFAD2B1)      // Для неактивных кнопок
private val DeepPeach = Color(0xFFE7A977)       // Для границ и акцентов
private val AccentSage = Color(0xFF8DAA91)      // Контрастный шалфей (для выбранного)

private val LightColorScheme = lightColorScheme(
    primary = AccentSage,             // Основной контраст (выбранная кнопка)
    onPrimary = Color.White,
    primaryContainer = AccentSage.copy(alpha = 0.2f),
    onPrimaryContainer = Color(0xFF2E3D30),

    secondary = DeepPeach,            // Вторичный акцент (например, слайдеры)
    onSecondary = Color.White,

    background = PeachBackground,
    surface = Color(0xFFFFF8F3),      // Чуть светлее фона для карточек
    onSurface = Color(0xFF422B1A),    // Темно-коричневый вместо черного (мягче)

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