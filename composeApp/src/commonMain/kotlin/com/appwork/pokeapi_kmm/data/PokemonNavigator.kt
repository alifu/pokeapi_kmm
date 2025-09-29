package com.appwork.pokeapi_kmm.data

import com.appwork.pokeapi_kmm.models.Pokedex

class PokemonNavigator(
    private val allResults: List<Pokedex>,
    startIndex: Int? = 0
) {
    private var currentIndex: Int? = if (startIndex in allResults.indices) startIndex else null

    val current: Pokedex?
        get() = currentIndex?.let { allResults[it] }

    fun moveNext(): Pokedex? {
        val index = currentIndex
        if (index != null && index + 1 < allResults.size) {
            currentIndex = index + 1
        }
        return current
    }

    fun movePrevious(): Pokedex? {
        val index = currentIndex
        if (index != null && index - 1 >= 0) {
            currentIndex = index - 1
        }
        return current
    }
}


