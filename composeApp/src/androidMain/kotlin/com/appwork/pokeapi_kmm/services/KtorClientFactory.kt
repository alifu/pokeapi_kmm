package com.appwork.pokeapi_kmm.services

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual class KtorClientFactory {
    actual fun create(): HttpClient = HttpClient(OkHttp)
}