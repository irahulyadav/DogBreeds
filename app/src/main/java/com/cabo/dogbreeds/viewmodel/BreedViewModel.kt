package com.cabo.dogbreeds.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedRepository
import javax.inject.Inject
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1


class BreedViewModel @Inject constructor(application: Application, val breedRepository: BreedRepository) :
    AndroidViewModel(application), BreedProtocol1 {


    val breedLiveData: LiveData<PagedList<BreedEntity>>

    val filter: MutableLiveData<String?> = MutableLiveData()

    init {
        val factory = getAllPaged()

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(20)
            .setPageSize(10)
            .build()

        breedLiveData = LivePagedListBuilder<Int, BreedEntity>(factory, pagedListConfig).build()
    }


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

    override fun getAllPaged(): DataSource.Factory<Int, BreedEntity> {
        return breedRepository.getAllPaged()
    }

    override fun updateBreedEntity(entity: BreedEntity) {
        breedRepository.updateBreedEntity(entity)
    }
}