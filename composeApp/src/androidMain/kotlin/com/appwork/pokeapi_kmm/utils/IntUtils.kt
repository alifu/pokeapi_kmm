package com.appwork.pokeapi_kmm.utils

fun Int.toKg(): String {
    return "${forTrailingZero(this / 10.0)} kg"
}

fun Int.toMeters(): String {
    return "${forTrailingZero(this / 10.0)} m"
}

private fun forTrailingZero(value: Double): String {
    // removes trailing .0 just like %g in Swift
    return if (value % 1.0 == 0.0) {
        value.toInt().toString()
    } else {
        value.toString()
    }
}
