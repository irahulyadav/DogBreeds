package com.cabo.dogbreeds.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedRepository
import javax.inject.Inject
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1


class BreedViewModel @Inject constructor(application: Application, val breedRepository: BreedRepository) :
    AndroidViewModel(application) {


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