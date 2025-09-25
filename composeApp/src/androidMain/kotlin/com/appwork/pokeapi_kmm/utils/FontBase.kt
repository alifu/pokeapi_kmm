package com.appwork.pokeapi_kmm.utils

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight as ComposeFontWeight
import androidx.compose.ui.unit.sp

actual class FontBase actual constructor(size: Double, weight: FontWeight) {
    val textStyle: TextStyle = TextStyle(
        fontSize = size.sp,
        fontWeight = when (weight) {
            FontWeight.Regular -> ComposeFontWeight.Normal
            FontWeight.Bold -> ComposeFontWeight.Bold
        }
    )
}