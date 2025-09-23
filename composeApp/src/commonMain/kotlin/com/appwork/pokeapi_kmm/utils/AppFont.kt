package com.appwork.pokeapi_kmm.utils

enum class AppFont(val size: Int, val weight: FontWeight) {
    HeaderHeadline(24, FontWeight.Bold),
    HeaderSubtitle1(14, FontWeight.Bold),
    HeaderSubtitle2(12, FontWeight.Bold),
    HeaderSubtitle3(10, FontWeight.Bold),
    Body1(14, FontWeight.Regular),
    Body2(12, FontWeight.Regular),
    Body3(10, FontWeight.Regular),
    Caption(8, FontWeight.Regular);
}

enum class FontWeight {
    Regular,
    Bold
}