package com.cabo.dogbreeds.repository

import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.data.local.remote.BreedListApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BreedImageRepository @Inject constructor(var breedApiService: BreedApiService) {


    fun fetchBreedImage(breedEntity: BreedEntity, subBreed: String? = null, action: (List<String>?) -> Unit) {

        val call = if (subBreed.isNullOrBlank())
            breedApiService.fetchDogBreedImages(breedEntity.breed)
        else
            breedApiService.fetchDogSubBreedImages(breedEntity.breed, subBreed)

        call.enqueue(object : Callback<BreedListApiResponse> {
            override fun onResponse(call: Call<BreedListApiResponse>, response: Response<BreedListApiResponse>) {
                action(response.body()?.message ?: arrayListOf())
            }

            override fun onFailure(call: Call<BreedListApiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun fetchSubBreeds(breedEntity: BreedEntity, action: (List<String>?) -> Unit) {
        breedApiService.fetchDogSubBreeds(breedEntity.breed)
            .enqueue(object : Callback<BreedListApiResponse> {
                override fun onResponse(call: Call<BreedListApiResponse>, response: Response<BreedListApiResponse>) {
                    action(response.body()?.message ?: arrayListOf())
            }

            override fun onFailure(call: Call<BreedListApiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}