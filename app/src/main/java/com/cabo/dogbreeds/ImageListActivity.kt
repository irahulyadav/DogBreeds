package com.cabo.dogbreeds

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.di.ViewModelFactory
import com.cabo.dogbreeds.viewmodel.ImageListViewModel
import javax.inject.Inject

class ImageListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: ImageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_list_activity)
        (application as MainApplication).appComponent.inject(this)
        val breed = intent.getParcelableExtra("breed") as BreedEntity
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ImageListViewModel::class.java)
        viewModel.imageRepository.fetchBreedImage(breed)
        title = breed.breed
    }

}
