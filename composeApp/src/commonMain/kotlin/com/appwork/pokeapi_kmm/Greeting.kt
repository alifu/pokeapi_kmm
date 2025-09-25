package com.appwork.pokeapi_kmm

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    private val platform = getPlatform()

    private val client = HttpClient()

    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
