package com.appwork.pokeapi_kmm.data

import com.appwork.pokeapi_kmm.models.PokedexResponse
import com.appwork.pokeapi_kmm.models.Pokemon
import com.appwork.pokeapi_kmm.models.PokemonSpecies
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PokemonRepository(private val client: HttpClient) {
    companion object {
//        private const val BASE = "https://pokeapi.co/api/v2"
        private const val BASE = "http://10.0.2.2:3002"
//        private const val BASE = "http://localhost:3002"
    }

    suspend fun getPokedex(limit: Int = 50, offset: Int = 0): PokedexResponse {
        val url = "$BASE/pokemon?limit=$limit&offset=$offset"
        return client.get(url).body()
    }

    suspend fun getPokemonDetail(nameOrId: String): Pokemon {
        val url = "$BASE/pokemon/$nameOrId"
        return client.get(url).body()
    }

    suspend fun getPokemonSpecies(nameOrId: String): PokemonSpecies {
        val url = "$BASE/pokemon-species/$nameOrId"
        return client.get(url).body()
    }
}
