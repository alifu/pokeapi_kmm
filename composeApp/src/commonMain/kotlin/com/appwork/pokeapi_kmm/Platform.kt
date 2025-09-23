package com.appwork.pokeapi_kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform