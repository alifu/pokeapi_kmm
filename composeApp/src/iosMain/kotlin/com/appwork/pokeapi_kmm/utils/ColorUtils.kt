package com.appwork.pokeapi_kmm.utils

import platform.UIKit.UIColor

private fun hexToUIColor(hex: String): UIColor {
    val r = hex.substring(1..2).toInt(16) / 255.0
    val g = hex.substring(3..4).toInt(16) / 255.0
    val b = hex.substring(5..6).toInt(16) / 255.0
    return UIColor(red = r, green = g, blue = b, alpha = 1.0)
}

fun ColorType.toUIColor(): UIColor = hexToUIColor(hex)
fun AppColor.toUIColor(): UIColor = hexToUIColor(hex)