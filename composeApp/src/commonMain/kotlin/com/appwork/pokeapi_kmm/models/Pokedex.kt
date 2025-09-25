package com.appwork.pokeapi_kmm.models

import kotlinx.serialization.Serializable

@Serializable
data class Pokedex (
    val name: String,
    val url: String
)

@Serializable
data class PokedexResponse(
    val count: Int,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokedex>
)