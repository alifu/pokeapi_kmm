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
    val stats: List<Stats> = emptyList(),
    val abilities: List<Ability> = emptyList()
)

@Serializable
data class TypeSlot(val slot: Int, val type: Pokedex)
@Serializable
data class Sprites(val other: SpritesOther? = null)
@Serializable
data class SpritesOther( @SerialName("official-artwork") val officialArtwork: SpritesOfficialArtwork? = null)
@Serializable
data class SpritesOfficialArtwork( @SerialName("front_default") val frontDefault: String? = null)

@Serializable
data class Stats(
    @SerialName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: StatsInfo
)

@Serializable
data class StatsInfo(
    val name: String,
    val url: String
) {
    fun displayName(): String {
        return when (name.lowercase()) {
            "hp" -> "HP"
            "attack" -> "ATK"
            "defense" -> "DEF"
            "special-attack" -> "SATK"
            "special-defense" -> "SDEF"
            "speed" -> "SPD"
            else -> "-"
        }
    }
}

@Serializable
data class Ability(
    val ability: AbilityInfo? = null,
    @SerialName("is_hidden") val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class AbilityInfo(
    val name: String,
    @SerialName("url") val detailURL: String
)
