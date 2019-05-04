package com.cabo.dogbreeds.data.local.remote


data class BreedApiResponse(
    val status: String,
    val message: List<Map<String, List<String>>>
)
