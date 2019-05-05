package com.cabo.dogbreeds.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.cabo.dogbreeds.data.local.BreedsDatabase
import com.cabo.dogbreeds.data.local.dao.BreedDao
import com.cabo.dogbreeds.data.local.dao.BreedProtocol
import com.cabo.dogbreeds.data.local.entity.BreedEntity

class BreedRepository(application: Application) : BreedProtocol {

    val breedDao: BreedDao

    init {
        val database = BreedsDatabase.getInstance(application) ?: throw Exception("Database not created")
        breedDao = database.breedDao()
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

    override fun getBreedList(): LiveData<List<BreedEntity>> {
        return breedDao.getBreedList()
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