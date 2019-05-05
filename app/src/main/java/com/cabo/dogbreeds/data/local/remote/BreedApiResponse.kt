package com.cabo.dogbreeds.data.local.remote

data class BreedApiResponse(
    val status: String,
    val message: Map<String, List<String>>
)

data class BreedListApiResponse(
    val status: String,
    val message: List<String>
)

data class BreedImageResponse(
    val status: String,
    val message: String
)
