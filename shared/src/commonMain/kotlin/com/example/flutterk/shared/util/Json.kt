package com.example.flutterk.shared.util

import kotlinx.serialization.json.Json

val JSON by lazy {
    Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
}
