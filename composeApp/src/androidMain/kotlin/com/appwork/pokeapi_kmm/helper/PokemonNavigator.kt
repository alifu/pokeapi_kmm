package com.appwork.pokeapi_kmm.helper

import com.appwork.pokeapi_kmm.data.PokemonNavigator
import com.appwork.pokeapi_kmm.models.Pokedex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PokemonNavigatorFlow(
    results: List<Pokedex>,
    startIndex: Int? = 0
) {
    private val navigator = PokemonNavigator(results, startIndex)

    private val _current = MutableStateFlow(navigator.current)
    val current: StateFlow<Pokedex?> = _current

    fun moveNext() {
        _current.value = navigator.moveNext()
    }

    fun movePrevious() {
        _current.value = navigator.movePrevious()
    }
}
