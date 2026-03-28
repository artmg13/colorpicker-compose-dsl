package ru.adgoncharov.colorpickerdemo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpicker.CircleColorPicker
import ru.adgoncharov.colorpicker.colorpicker.RectangleColorPicker
import ru.adgoncharov.colorpicker.colorpicker.RingColorPicker
import ru.adgoncharov.colorpicker.colorpicker.SliderColorPicker
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpickerdemo.ui.components.ColorCard
import ru.adgoncharov.colorpickerdemo.ui.theme.ColorPickerDemoTheme

@Composable
fun ColorPickerDemoScreen(
    modifier: Modifier = Modifier,
) {

    val state = rememberColorPickerState(Color.Blue)

    var currentColorPicker by remember { mutableStateOf(ColorPickerType.CIRCLE) }
    var showAlpha by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ColorCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
                .shadow(elevation = 2.dp), state = state
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (currentColorPicker) {
                ColorPickerType.RING -> RingColorPicker(
                    state = state,
                    showAlpha = showAlpha
                )

                ColorPickerType.SLIDER -> SliderColorPicker(
                    state = state,
                    showAlpha = showAlpha
                )

                ColorPickerType.RECTANGLE -> RectangleColorPicker(
                    state = state,
                    showAlpha = showAlpha
                )

                ColorPickerType.CIRCLE -> CircleColorPicker(
                    state = state,
                    showAlpha = showAlpha
                )
            }
        }

        ColorPickerVariant(
            modifier = Modifier.padding(vertical = 8.dp),
            showAlpha = showAlpha,
            selected = currentColorPicker,
            onChange = { currentColorPicker = it },
            onShowAlphaChanged = { showAlpha = it }
        )
    }
}

enum class ColorPickerType {
    RING,
    SLIDER,
    RECTANGLE,
    CIRCLE
}

@Composable
fun ColorPickerVariant(
    modifier: Modifier = Modifier,
    showAlpha: Boolean,
    onShowAlphaChanged: (Boolean) -> Unit,
    selected: ColorPickerType,
    onChange: (ColorPickerType) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ColorPickerVariantItem(
                title = "ShowAlpha",
                selected = showAlpha,
                onClick = { onShowAlphaChanged(!showAlpha) }
            )
        }

        item {
            ColorPickerVariantItem(
                title = "Ring",
                selected = selected == ColorPickerType.RING,
                onClick = { onChange(ColorPickerType.RING) }
            )
        }

        item {
            ColorPickerVariantItem(
                title = "Circle",
                selected = selected == ColorPickerType.CIRCLE,
                onClick = { onChange(ColorPickerType.CIRCLE) }
            )
        }

        item {
            ColorPickerVariantItem(
                title = "Rectangle",
                selected = selected == ColorPickerType.RECTANGLE,
                onClick = { onChange(ColorPickerType.RECTANGLE) }
            )
        }

        item {
            ColorPickerVariantItem(
                title = "Slider",
                selected = selected == ColorPickerType.SLIDER,
                onClick = { onChange(ColorPickerType.SLIDER) }
            )
        }
    }
}

@Composable
fun ColorPickerVariantItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
) {

    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary // Шалфейный
    } else {
        Color.Transparent // Прозрачный, чтобы видеть персиковый фон
    }

    val contentColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
    }

    val borderColor = if (selected) {
        Color.Transparent
    } else {
        MaterialTheme.colorScheme.outline
    }

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .border(1.dp, borderColor, CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = title,
            textAlign = TextAlign.Center,
            color = contentColor
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ColorPickerDemoScreen() {

    ColorPickerDemoTheme {
        ColorPickerDemoScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}