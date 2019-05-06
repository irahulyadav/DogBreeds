package com.cabo.dogbreeds.repository

import androidx.lifecycle.MutableLiveData
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.data.local.remote.BreedListApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BreedImageRepository @Inject constructor(var breedApiService: BreedApiService) {

    var list: MutableLiveData<List<String>> = MutableLiveData()

    fun fetchBreedImage(breedEntity: BreedEntity, subBreed: String? = null) {

        val call = if (subBreed.isNullOrBlank())
            breedApiService.fetchDogBreedImages(breedEntity.breed)
        else
            breedApiService.fetchDogSubBreedImages(breedEntity.breed, subBreed)

        call.enqueue(object : Callback<BreedListApiResponse> {
            override fun onResponse(call: Call<BreedListApiResponse>, response: Response<BreedListApiResponse>) {
                list.value = response.body()?.message ?: arrayListOf()
            }

            override fun onFailure(call: Call<BreedListApiResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

}