package com.cabo.dogbreeds.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedImageRepository
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    application: Application,
    val imageRepository: BreedImageRepository
) : ViewModel() {

    var subBreedList: MutableLiveData<List<String>> = MutableLiveData()

    var images: MutableLiveData<List<String>> = MutableLiveData()

    var subBreed: String? = null
        set(value) {
            field = value
            if (breedEntity != null) {
                imageRepository.fetchBreedImage(breedEntity!!, value) {
                    images.value = it
                }
            }

        }

    var breedEntity: BreedEntity? = null
        set(value) {
            field = value
            if (value != null) {
                imageRepository.fetchSubBreeds(value) {
                    subBreedList.value = it
                }
            }
            subBreed = null
        }


}
