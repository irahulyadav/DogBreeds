package com.cabo.dogbreeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.data.local.remote.BreedApiResponse
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.repository.BreedRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1


class BreedViewModel @Inject constructor(
    application: Application,
    val breedApiService: BreedApiService,
    val breedRepository: BreedRepository
) : AndroidViewModel(application), BreedProtocol1 {


    init {

        breedApiService.fetchDogBreeds().enqueue(object : Callback<BreedApiResponse> {
            override fun onResponse(call: Call<BreedApiResponse>, response: Response<BreedApiResponse>) {
                val list = arrayListOf<BreedEntity>()
                response.body()?.message?.forEach { ket, _ ->
                    list.add(BreedEntity(ket))
                }
                insertBreedEntity(list)
                allBreeds = getBreedList()
            }

            override fun onFailure(call: Call<BreedApiResponse>, t: Throwable) {

            }
        })
    }


    var allBreeds: LiveData<List<BreedEntity>> = breedRepository.getBreedList()

    override fun insertBreedEntity(list: List<BreedEntity>) {
        breedRepository.insertBreedEntity(list)
    }

    override fun insertBreedEntity(entity: BreedEntity) {
        breedRepository.insertBreedEntity(entity)
    }

    override fun flushBreedData() {
        breedRepository.flushBreedData()
    }

    override fun getBreedCount(): Int {
        return breedRepository.getBreedCount()
    }

    override fun getBreedEntityByBreed(breed: String): LiveData<BreedEntity>? {
        return breedRepository.getBreedEntityByBreed(breed)
    }

    override fun getBreedList(): LiveData<List<BreedEntity>> {
        return breedRepository.getBreedList()
    }

    override fun updateBreedEntity(entity: BreedEntity) {
        breedRepository.updateBreedEntity(entity)
    }
}