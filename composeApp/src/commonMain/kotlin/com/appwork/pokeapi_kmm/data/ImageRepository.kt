package com.appwork.pokeapi_kmm.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM
import io.ktor.utils.io.core.*
import io.github.reactivecircus.cache4k.Cache
import kotlin.time.Duration.Companion.minutes

class ImageRepository(
    private val httpClient: HttpClient,
    private val cacheDir: String // platform-specific path injected
) {
    private val fileSystem = FileSystem.SYSTEM

    suspend fun fetchAndCacheImage(url: String): String {
        val ext = extractExtension(url)
        val fileName = url.hashCode().toString() + ext
        val filePath = "$cacheDir/$fileName".toPath()

        // Return cached if exists
        if (fileSystem.exists(filePath)) {
            return filePath.toString()
        }

        // Download image
        val response: HttpResponse = httpClient.get(url)
        val bytes = response.readBytes()

        // Save to cache
        withContext(Dispatchers.IO) {
            fileSystem.write(filePath) {
                write(bytes)
            }
        }

        return filePath.toString()
    }

    fun extractExtension(url: String): String {
        val idx = url.lastIndexOf('.')
        return if (idx != -1 && idx < url.length - 1) {
            url.substring(idx) // includes the dot
        } else {
            ".img" // fallback
        }
    }
}

class SharedImageLoader(private val httpClient: HttpClient) {
    private val cache = Cache.Builder<String, ByteArray>()
        .expireAfterWrite(10.minutes)
        .build()

    suspend fun load(url: String): ByteArray {
        return cache.get(url) {
            val response: HttpResponse = httpClient.get(url)
            response.bodyAsChannel().readRemaining().readBytes()
        }
    }
}
