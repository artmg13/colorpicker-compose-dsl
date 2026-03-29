package ru.adgoncharov.colorpicker.colorpickerdsl.components.slider

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.BaseAreaScope

@ColorPickerDsl
class SpecificSliderScope : BaseAreaScope() {
    var reversed: Boolean = false
    var shape: Shape = RectangleShape
}

@ColorPickerDsl
class SpecificSliderVerticalScope : BaseAreaScope() {
    var reversed: Boolean = false
    var shape: Shape = RectangleShape
}