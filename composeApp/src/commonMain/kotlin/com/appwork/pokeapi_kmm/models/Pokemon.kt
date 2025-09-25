package com.appwork.pokeapi_kmm.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val id: Int,
    val name: String,
    @SerialName("base_experience") val baseExperience: Int? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val types: List<TypeSlot> = emptyList(),
    val sprites: Sprites? = null,
)

@Serializable
data class TypeSlot(val slot: Int, val type: Pokedex)
@Serializable
data class Sprites(val front_default: String? = null)
