package com.appwork.pokeapi_kmm.utils

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

fun AppColor.toComposeColor(): Color = Color(AndroidColor.parseColor(hex))
fun ColorType.toComposeColor(): Color = Color(AndroidColor.parseColor(hex))