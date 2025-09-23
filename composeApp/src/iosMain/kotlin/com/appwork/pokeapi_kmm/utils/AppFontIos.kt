package com.appwork.pokeapi_kmm.utils

import platform.UIKit.UIFont

fun AppFont.toUIFont(): UIFont {
    val fontWeight = when (weight) {
        FontWeight.Bold -> UIFont.boldSystemFontOfSize(size.toDouble())
        FontWeight.Regular -> UIFont.systemFontOfSize(size.toDouble())
    }
    return fontWeight
}