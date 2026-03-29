package ru.adgoncharov.colorpicker.colorpickerdsl.components.circle

import ru.adgoncharov.colorpicker.colorpickerdsl.ColorPickerDsl
import ru.adgoncharov.colorpicker.colorpickerdsl.scope.BaseAreaScope

@ColorPickerDsl
class SpecificCircleScope() : BaseAreaScope() {

    var reversedX: Boolean = false
    var reversedY: Boolean = false
}