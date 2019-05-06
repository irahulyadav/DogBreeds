package com.cabo.dogbreeds.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cabo.dogbreeds.repository.BreedImageRepository
import javax.inject.Inject

class ImageListViewModel @Inject constructor(application: Application, val imageRepository: BreedImageRepository) :
    AndroidViewModel(application) {

}
