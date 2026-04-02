# Язык / Language

Read this in: [Английский (English)](README.md)

# ColorPicker Compose DSL

[![JitPack](https://jitpack.io/v/artmg13/colorpicker-compose-dsl.svg)](https://jitpack.io/#artmg13/colorpicker-compose-dsl)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Гибкая и легковесная библиотека для создания Color Picker на Jetpack Compose.
Основная фишка — использование DSL, который позволяет собрать уникальный
инструмент выбора цвета как конструктор всего за пару строк кода.

---

# Превью

<p align="center">
<img src="images/cp_circle.jpg" width="24%" alt="Circle ColorPicker">
<img src="images/cp_rectangle.jpg" width="24%" alt="Rectangle ColorPicker">
<img src="images/cp_ring.jpg" width="24%" alt="Ring ColorPicker">
<img src="images/cp_slider.jpg" width="24%" alt="Slider ColorPicker">
</p>

---

# Установка

Добавьте репозиторий JitPack в ваш `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // <--- Данная строка
    }
}
```

Добавьте зависимость в build.gradle.kts вашего модуля:

```kotlin
dependencies {
    implementation("com.github.artmg13:colorpicker-compose-dsl:1.0.0")
}
```

# Особенности

* Модульный DSL: Собирайте свой Picker из готовых блоков (Hue, Saturation, Value, Alpha).
* Полная кастомизация: Настраивайте размеры, цвета указателей (thumb) и их поведение.
* Низкоуровневые API: Доступ к базовым областям (Area) для создания абсолютно уникальных форм.
* Готовые решения: 4 предустановленных вида Color Picker для быстрого старта.

# Быстрый старт

Используйте rememberColorPickerState для управления цветом и одну из готовых функций:

```kotlin
@Composable
fun ColorScreen() {
    val state = rememberColorPickerState(initialColor = Color.Blue)
    
    // hue ring + sv rectangle + alpha slider
    RingColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    )
    
    // hs circle + value slider + alpha slider
    CircleColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    )
    
    // sv rectangle + hue slider + alpha slider
    RectangleColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    )
    
    // hue, saturation, value, alpha sliders
    SliderColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    )

    // Получить текущий цвет: state.color
}
```

# Кастомный Color Picker

Вы можете комбинировать компоненты внутри блока ColorPicker, настраивая их свойства «на лету»:

```kotlin
@Composable
fun CustomColorScreen() {
    val state = rememberColorPickerState()

    ColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    ) {
        // Квадратная область выбора Насыщенности и Яркости
        RectangleSV(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        ) {
            thumbInside = false // Указатель может выходить за границы
        }

        Spacer(Modifier.height(16.dp))

        // Слайдер оттенка (Hue)
        HueSlider(
            modifier = Modifier.fillMaxWidth().height(24.dp)
        ) {
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}
```

Ознакомиться с примерами кастомизации можно в пакете *[samples](app/src/main/java/ru/adgoncharov/colorpickerdemo/samples)*

# Доступные компоненты

## Высокоуровневые элементы

Готовые блоки

| Компонент                                   | Описание                                           |
|---------------------------------------------|----------------------------------------------------|
| CircleHS                                    | Круглая область Оттенок-Насыщенность               |
| RectangleSV                                 | Квадратная область Насыщенность-Яркость            |
| HueRing                                     | Кольцо выбора оттенка                              |
| HueSlider / HueSliderVertical               | Слайдер оттенка (Горизонтальный/Вертикальный)      |
| SaturationSlider / SaturationSliderVertical | Слайдер насыщенности (Горизонтальный/Вертикальный) |
| ValueSlider / ValueSliderVertical           | Слайдер яркости (Горизонтальный/Вертикальный)      |
| AlphaSlider / AlphaSliderVertical           | Слайдер прозрачности (Горизонтальный/Вертикальный) |

## Низкоуровневые элементы

В том случае, если вас не устраивает как реализованы по умолчанию данные элементы,
либо вы хотите создать свой собственный, можете использовать следующие функции:

* CircleArea
* RectangleArea
* RingSlider
* HorizontalSlider
* VerticalSlider

# Лицензия

Распространяется под лицензией Apache 2.0. См. файл LICENSE для подробностей.


