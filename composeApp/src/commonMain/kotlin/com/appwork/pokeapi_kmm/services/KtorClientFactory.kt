package com.appwork.pokeapi_kmm.services

import io.ktor.client.HttpClient

expect class KtorClientFactory {
    fun create(): HttpClient
}