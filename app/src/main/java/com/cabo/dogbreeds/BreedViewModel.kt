package com.cabo.dogbreeds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.data.local.remote.ApiBuilder
import com.cabo.dogbreeds.data.local.remote.BreedApiResponse
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.repository.BreedRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1

class BreedViewModel(application: Application) : AndroidViewModel(application), BreedProtocol1 {
    private val api = ApiBuilder.buildService(BreedApiService::class.java)

    init {
        api.fetchDogBreeds().enqueue(object : Callback<BreedApiResponse> {
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

    var repository = BreedRepository(application)

    var allBreeds: LiveData<List<BreedEntity>> = repository.getBreedList()

    override fun insertBreedEntity(list: List<BreedEntity>) {
        repository.insertBreedEntity(list)
    }

    override fun insertBreedEntity(entity: BreedEntity) {
        repository.insertBreedEntity(entity)
    }

    override fun flushBreedData() {
        repository.flushBreedData()
    }

    override fun getBreedCount(): Int {
        return repository.getBreedCount()
    }

    override fun getBreedEntityByBreed(breed: String): LiveData<BreedEntity>? {
        return repository.getBreedEntityByBreed(breed)
    }

    override fun getBreedList(): LiveData<List<BreedEntity>> {
        return repository.getBreedList()
    }

    override fun updateBreedEntity(entity: BreedEntity) {
        repository.updateBreedEntity(entity)
    }
}