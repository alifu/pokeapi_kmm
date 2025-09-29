package com.appwork.pokeapi_kmm.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.appwork.pokeapi_kmm.PokemonSDK
import com.appwork.pokeapi_kmm.data.SharedImageLoader
import com.appwork.pokeapi_kmm.helper.PokemonNavigatorFlow
import com.appwork.pokeapi_kmm.models.Pokedex
import com.appwork.pokeapi_kmm.models.Pokemon
import com.appwork.pokeapi_kmm.models.PokemonSpecies
import com.appwork.pokeapi_kmm.services.KtorClientFactory
import io.ktor.client.plugins.logging.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class PokemonViewModel(
    private val sdk: PokemonSDK = PokemonSDK(),
    results: List<Pokedex>,
    startIndex: Int? = 0
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val navigator = PokemonNavigatorFlow(results, startIndex)
    private val _current = MutableStateFlow<Pokedex?>(null)
    val current: StateFlow<Pokedex?> = _current.asStateFlow()
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon.asStateFlow()
    private val _pokemonSpecies = MutableStateFlow<PokemonSpecies?>(null)
    val pokemonSpecies: StateFlow<PokemonSpecies?> = _pokemonSpecies.asStateFlow()
    private val _imageBytes = MutableStateFlow<ByteArray?>(null)
    val imageBytes: StateFlow<ByteArray?> = _imageBytes.asStateFlow()

    private val loader = SharedImageLoader(httpClient = KtorClientFactory().create())

    init {
        // Observe navigator current
        scope.launch {
            navigator.current.collect { result ->
                _current.value = result
                _imageBytes.value = null // reset image
                result?.name?.let { loadPokemonAndSpecies(name = it) }
            }
        }
    }

    private fun loadPokemonAndSpecies(name: String) {
        scope.launch {
            try {
                val p = sdk.fetchPokemon(name)
                _pokemon.value = p
                val s = sdk.fetchSpecies(name)
                _pokemonSpecies.value = s

                p.sprites?.other?.officialArtwork?.frontDefault?.let { url ->
                    loadImage(url)
                }
            } catch (e: Exception) {
                // TODO handle error
            }
        }
    }

    private fun loadImage(url: String) {
        scope.launch {
            try {
                val data = loader.load(url) // suspend call
                _imageBytes.value = data
            } catch (e: Exception) {
                // handle
            }
        }
    }

    fun moveNext() = navigator.moveNext()

    fun movePrevious() = navigator.movePrevious()
}