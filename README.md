# Language / Язык

Read this in: [Русский (Russian)](README_RU.md)

# ColorPicker Compose DSL

[![JitPack](https://jitpack.io/v/artmg13/colorpicker-compose-dsl.svg)](https://jitpack.io/#artmg13/colorpicker-compose-dsl)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A flexible and lightweight library for creating Color Pickers in **Jetpack Compose**.
The core feature is the **DSL-based approach**, which allows you to assemble a unique color
selection tool like a construction set in just a few lines of code.

---

# Preview

<p align="center">
<img src="images/cp_circle.jpg" width="24%" alt="Circle ColorPicker">
<img src="images/cp_rectangle.jpg" width="24%" alt="Rectangle ColorPicker">
<img src="images/cp_ring.jpg" width="24%" alt="Ring ColorPicker">
<img src="images/cp_slider.jpg" width="24%" alt="Slider ColorPicker">
</p>

---

# Installation

1. Add the JitPack repository to your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // <-- this row
    }
}
```

Add the dependency to your module's build.gradle.kts:

```kotlin
dependencies {
    implementation("com.github.artmg13:colorpicker-compose-dsl:1.0.0")
}
```

# Features

* Modular DSL: Build your Picker using ready-made blocks (Hue, Saturation, Value, Alpha).
* Full Customization: Adjust sizes, thumb colors, and behavior.
* Low-level APIs: Access base "Areas" to create completely unique shapes and forms.
* Out-of-the-box Solutions: 4 pre-configured Color Pickers for a quick start.

# Quick Start

Use rememberColorPickerState to manage the color and one of the built-in functions:

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

    // Access current color: state.color
}
```

# Custom Color Picker (DSL)

You can combine components inside the ColorPicker block and configure their properties on the fly:

```kotlin
@Composable
fun CustomColorScreen() {
    val state = rememberColorPickerState()

    ColorPicker(
        modifier = Modifier.fillMaxWidth(),
        state = state
    ) {
        // Square area for Saturation and Value selection
        RectangleSV(
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        ) {
            thumbInside = false // Thumb can move outside the boundaries
        }

        Spacer(Modifier.height(16.dp))

        // Hue selection slider
        HueSlider(
            modifier = Modifier.fillMaxWidth().height(24.dp)
        ) {
            thumbSize = DpSize(36.dp, 36.dp)
        }
    }
}
```

Check out more customization examples in the *[samples package](app/src/main/java/ru/adgoncharov/colorpickerdemo/samples)*

# Available Components

## High-level Elements (Ready-made Blocks)

| Component                                   | Description                                     |
|---------------------------------------------|-------------------------------------------------|
| CircleHS                                    | Circular Hue-Saturation area                    |
| RectangleSV                                 | Square Saturation-Value area                    |                    
| HueRing                                     | Hue selection ring                              |              
| HueSlider / HueSliderVertical               | Hue slider (Horizontal/Vertical)                |                
| SaturationSlider / SaturationSliderVertical | Saturation slider (Horizontal/Vertical)         |         
| ValueSlider / ValueSliderVertical           | Value (Brightness) slider (Horizontal/Vertical) |
| AlphaSlider / AlphaSliderVertical           | Transparency slider (Horizontal/Vertical)       | 

## Low-level Elements

If the default implementation doesn't fit your needs or you want to build something from scratch,
use these base functions:

* CircleArea
* RectangleArea
* RingSlider
* HorizontalSlider
* VerticalSlider

# License

Distributed under the Apache 2.0 License. See the LICENSE file for details.