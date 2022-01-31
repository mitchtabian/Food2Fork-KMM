package com.codingwithmitch.food2forkkmm.datasource.network

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual class KtorClientFactory {
    actual fun build(): HttpClient {
        return HttpClient(Darwin) {
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