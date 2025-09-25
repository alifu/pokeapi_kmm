package com.appwork.pokeapi_kmm.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class PokemonSpecies(
    val id: Int,
    val name: String,
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry> = emptyList(),
    @SerialName("evolution_chain") val evolutionChain: EvolutionChainRef? = null
)

@Serializable
data class FlavorTextEntry(val flavor_text: String, val language: Pokedex)
@Serializable
data class EvolutionChainRef(val url: String)
