package com.appwork.pokeapi_kmm.services

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(io.ktor.client.plugins.logging.Logging) {
            logger = io.ktor.client.plugins.logging.Logger.SIMPLE
            level = io.ktor.client.plugins.logging.LogLevel.INFO
        }
    }
}
