package com.appwork.pokeapi_kmm.utils

enum class AppColor(val hex: String) {
    Primary("#DC0A2D"),
    GrayscaleDark("#212121"),
    GrayscaleMedium("#666666"),
    GrayscaleLight("#E0E0E0"),
    Background("#EFEFEF"),
    White("#FFFFFF"),
    Wireframe("#B8B8B8");

    companion object {
        fun fromString(value: String?): AppColor? {
            return entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}