package com.appwork.pokeapi_kmm.utils

import platform.UIKit.UIFont

actual class FontBase actual constructor(size: Double, weight: FontWeight) {
    val uiFont: UIFont = when (weight) {
        FontWeight.Regular -> UIFont.systemFontOfSize(size)
        FontWeight.Bold -> UIFont.boldSystemFontOfSize(size)
    }
}