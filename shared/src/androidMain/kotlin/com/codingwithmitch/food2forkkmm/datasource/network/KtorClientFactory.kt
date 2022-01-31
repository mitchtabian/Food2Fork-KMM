package com.codingwithmitch.food2forkkmm.datasource.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true // if the server sends extra fields, ignore them
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
}
