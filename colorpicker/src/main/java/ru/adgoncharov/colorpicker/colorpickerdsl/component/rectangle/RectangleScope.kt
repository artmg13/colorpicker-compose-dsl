package ru.adgoncharov.colorpicker.colorpickerdsl.component.rectangle

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.BaseAreaScope

@ColorPickerDsl
class SpecificRectangleScope : BaseAreaScope() {
    var reversedX: Boolean = false
    var reversedY: Boolean = false
    var shape: Shape = RectangleShape
}