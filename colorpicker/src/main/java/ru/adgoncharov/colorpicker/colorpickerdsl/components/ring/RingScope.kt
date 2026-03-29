package ru.adgoncharov.colorpicker.colorpickerdsl.components.ring

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.BaseAreaScope
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.RingPadding

@ColorPickerDsl
class SpecificRingScope : BaseAreaScope() {
    var ringWidth: Dp = 24.dp
    var reversed: Boolean = true
    var contentInsidePadding: RingPadding = RingPadding.INNER
    var gapContentInside: Dp = 0.dp
    var contentInside: (@Composable () -> Unit)? = null
}