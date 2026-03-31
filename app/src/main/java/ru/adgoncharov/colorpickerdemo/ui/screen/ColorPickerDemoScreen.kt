package ru.adgoncharov.colorpickerdemo.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpicker.CircleColorPicker
import ru.adgoncharov.colorpicker.colorpicker.RectangleColorPicker
import ru.adgoncharov.colorpicker.colorpicker.RingColorPicker
import ru.adgoncharov.colorpicker.colorpicker.SliderColorPicker
import ru.adgoncharov.colorpicker.rememberColorPickerState
import ru.adgoncharov.colorpickerdemo.ui.components.ColorCard
import ru.adgoncharov.colorpickerdemo.ui.theme.ColorPickerDemoTheme
import java.util.Locale.getDefault

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
            AnimatedContent(
                targetState = currentColorPicker,
                transitionSpec = {
                    fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                },
                label = "PickerChange"
            ) { type ->
                when (type) {
                    ColorPickerType.RING -> RingColorPicker(state = state, showAlpha = showAlpha)
                    ColorPickerType.SLIDER -> SliderColorPicker(state = state, showAlpha = showAlpha)
                    ColorPickerType.RECTANGLE -> RectangleColorPicker(state = state, showAlpha = showAlpha)
                    ColorPickerType.CIRCLE -> CircleColorPicker(state = state, showAlpha = showAlpha)
                }
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

        items(ColorPickerType.entries.toTypedArray()) { type ->
            ColorPickerVariantItem(
                title = type.name.lowercase()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(getDefault()) else it.toString() },
                selected = selected == type,
                onClick = { onChange(type) }
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

    val backgroundColor = if (selected)
        MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surface

    Surface(
        selected = selected,
        onClick = onClick,
        shape = CircleShape,
        color = backgroundColor,
        border = BorderStroke(
            1.dp,
            if (selected) Color.Transparent else MaterialTheme.colorScheme.outline
        )
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            style = MaterialTheme.typography.labelLarge,
            color = if (selected) MaterialTheme.colorScheme.onPrimaryContainer
            else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ColorPickerDemoScreenPreview() {

    ColorPickerDemoTheme {
        ColorPickerDemoScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}