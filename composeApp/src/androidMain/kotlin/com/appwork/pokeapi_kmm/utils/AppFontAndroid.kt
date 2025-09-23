package com.appwork.pokeapi_kmm.utils


import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun AppFont.toTextStyle(): TextStyle {
    val fw = when (weight) {
        com.appwork.pokeapi_kmm.utils.FontWeight.Bold -> FontWeight.Bold
        com.appwork.pokeapi_kmm.utils.FontWeight.Regular -> FontWeight.Normal
    }
    return TextStyle(fontSize = size.sp, fontWeight = fw)
}