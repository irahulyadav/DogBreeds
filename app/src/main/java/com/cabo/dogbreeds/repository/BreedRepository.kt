package com.cabo.dogbreeds.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.cabo.dogbreeds.data.local.dao.BreedDao
import com.cabo.dogbreeds.data.local.dao.BreedProtocol
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.data.local.remote.BreedApiResponse
import com.cabo.dogbreeds.data.local.remote.BreedApiService
import com.cabo.dogbreeds.data.local.remote.BreedImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BreedRepository @Inject constructor(
    val breedDao: BreedDao,
    val breedApiService: BreedApiService
) : BreedProtocol {


    init {

        breedApiService.fetchDogBreeds().enqueue(object : Callback<BreedApiResponse> {
            override fun onResponse(call: Call<BreedApiResponse>, response: Response<BreedApiResponse>) {
                val list = arrayListOf<BreedEntity>()
                response.body()?.message?.forEach { ket, _ ->
                    list.add(BreedEntity(ket))
                }
                insertBreedEntity(list)
            }

            override fun onFailure(call: Call<BreedApiResponse>, t: Throwable) {

            }
        })
    }

    fun loadBreedImage(breedEntity: BreedEntity) {
        breedApiService.fetchBreedImage(breedEntity.breed).enqueue(object : Callback<BreedImageResponse> {
            override fun onResponse(call: Call<BreedImageResponse>, response: Response<BreedImageResponse>) {
                breedEntity.image = response.message()
                updateBreedEntity(breedEntity)
            }

            override fun onFailure(call: Call<BreedImageResponse>, t: Throwable) {

            }
        })
    }



    override fun insertBreedEntity(list: List<BreedEntity>) {
        Companion.DaoTask<List<BreedEntity>>(action = {
            breedDao.insertBreedEntity(list)
        }).execute(list)
    }

    override fun insertBreedEntity(entity: BreedEntity) {
        Companion.DaoTask<BreedEntity>(action = {
            breedDao.insertBreedEntity(entity)
        }).execute(entity)
    }

    override fun updateBreedEntity(entity: BreedEntity) {
        Companion.DaoTask<BreedEntity>(action = {
            breedDao.updateBreedEntity(entity)
        }).execute(entity)
    }

    override fun getBreedEntityByBreed(breed: String): LiveData<BreedEntity>? {
        return breedDao.getBreedEntityByBreed(breed)
    }


    override fun getAllPaged(): DataSource.Factory<Int, BreedEntity> {
        return breedDao.getAllPaged()
    }

    override fun flushBreedData() {
        breedDao.flushBreedData()
    }

    override fun getBreedCount(): Int {
        return breedDao.getBreedCount()
    }

    companion object {

        class DaoTask<T>(val action: (T?) -> Unit) : AsyncTask<T, Void, Void?>() {

            override fun doInBackground(vararg params: T?): Void? {
                action(params.firstOrNull())
                return null
            }
        }
    }

}