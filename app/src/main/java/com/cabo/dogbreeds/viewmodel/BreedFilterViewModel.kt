package com.cabo.dogbreeds.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedRepository
import javax.inject.Inject
import com.cabo.dogbreeds.data.local.dao.BreedProtocol as BreedProtocol1


class BreedFilterViewModel @Inject constructor(private val breedRepository: BreedRepository) :
    ViewModel() {

    private val query = MutableLiveData<String>()

    var filteredData: LiveData<List<BreedEntity>> = Transformations.switchMap(query, ::search)

    init {
        filter(null)
    }


    fun filter(filter: String?) = apply { query.value = filter }


    private fun search(query: String?) =
        if (query.isNullOrEmpty()) breedRepository.getList() else breedRepository.search(query)

}