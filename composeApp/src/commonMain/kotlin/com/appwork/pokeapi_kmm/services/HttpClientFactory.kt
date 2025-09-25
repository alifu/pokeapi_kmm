package com.appwork.pokeapi_kmm.services

import io.ktor.client.*

expect class HttpClientFactory() {
    fun create(): HttpClient
}
