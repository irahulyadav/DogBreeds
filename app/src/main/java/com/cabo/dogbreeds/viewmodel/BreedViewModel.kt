package com.cabo.dogbreeds.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedRepository
import javax.inject.Inject
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1


class BreedViewModel @Inject constructor(val breedRepository: BreedRepository) :
    ViewModel() {


    val breedLiveData: LiveData<PagedList<BreedEntity>>



    init {
        val factory = breedRepository.getAllPaged()

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(20)
            .setPageSize(10)
            .build()

        breedLiveData = LivePagedListBuilder<Int, BreedEntity>(factory, pagedListConfig).build()

    }

}