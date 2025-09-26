package com.appwork.pokeapi_kmm.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    val name: String,
    val url: String
) {
    // Computed property to mimic `id` in Swift
    val id: String?
        get() = url
            .trimEnd('/')             // remove trailing slash if any
            .substringAfterLast("/")  // take last path component

    // Computed property to mimic `imageURL` in Swift
    val imageURL: String?
        get() = id?.let { "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$it.png" }
}

@Serializable
data class PokedexResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokedex>
)