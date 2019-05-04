package com.cabo.dogbreeds.data.local.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreedApiService {

    @GET("/api/breeds/list/all")
    fun fetchDogBreeds(): Call<BreedApiResponse>

    @GET("/api/breed/{breed}/list")
    fun fetchDogSubBreeds(@Path("breed") breed: String): Call<BreedApiResponse>

    @GET("/api/breed/{breed}/images")
    fun fetchDogBreedImages(@Path("breed") breed: String): Call<BreedApiResponse>

    @GET("/api/breed/{breed}/{subbreed}/images")
    fun fetchDogSubBreedImages(
        @Path("breed") breed: String,
        @Path("subbreed") subbreed: String
    ): Call<BreedApiResponse>


}
