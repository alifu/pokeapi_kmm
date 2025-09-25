package com.appwork.pokeapi_kmm

import com.appwork.pokeapi_kmm.data.PokemonRepository
import com.appwork.pokeapi_kmm.models.PokedexResponse
import com.appwork.pokeapi_kmm.models.Pokemon
import com.appwork.pokeapi_kmm.models.PokemonSpecies
import com.appwork.pokeapi_kmm.services.HttpClientFactory
//import io.ktor.utils.io.CancellationException

import io.ktor.client.*
import kotlin.coroutines.cancellation.CancellationException

class PokemonSDK {
    // do not create client here
    private var client: HttpClient? = null
    private var repo: PokemonRepository? = null

    private suspend fun ensureInit() {
        if (client == null) {
            try {
                // create client lazily inside suspend
                client = HttpClientFactory().create()
                repo = PokemonRepository(client!!)
            } catch (t: Throwable) {
                // rethrow as Exception so Swift sees it as NSError (annotated below)
                throw Exception("Failed to init HttpClient: ${t::class.simpleName}: ${t.message}", t)
            }
        }
    }

    @Throws(Exception::class, CancellationException::class)
    suspend fun fetchPokedex(limit: Int = 50, offset: Int = 0): PokedexResponse {
        ensureInit()
        return repo!!.getPokedex(limit, offset)
    }

    @Throws(Exception::class, CancellationException::class)
    suspend fun fetchPokemon(nameOrId: String): Pokemon {
        ensureInit()
        return repo!!.getPokemonDetail(nameOrId)
    }

    @Throws(Exception::class, CancellationException::class)
    suspend fun fetchSpecies(nameOrId: String): PokemonSpecies {
        ensureInit()
        return repo!!.getPokemonSpecies(nameOrId)
    }
}
