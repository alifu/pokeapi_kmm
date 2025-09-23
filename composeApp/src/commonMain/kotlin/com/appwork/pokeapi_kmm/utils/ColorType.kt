package com.appwork.pokeapi_kmm.utils

enum class ColorType(val hex: String) {
    Bug("#A7B723"),
    Dark("#75574C"),
    Dragon("#7037FF"),
    Electric("#F9CF30"),
    Fairy("#E69EAC"),
    Fighting("#C12239"),
    Fire("#F57D31"),
    Flying("#A891EC"),
    Ghost("#70559B"),
    Grass("#74CB48"),
    Ground("#DEC16B"),
    Ice("#9AD6DF"),
    Poison("#A43E9E"),
    Psychic("#FB5584"),
    Rock("#B69E31"),
    Steel("#B7B9D0"),
    Water("#6493EB"),
    None("#B8B8B8");

    companion object {
        fun fromString(value: String?): ColorType {
            if (value == null) return None
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: None
        }
    }
}