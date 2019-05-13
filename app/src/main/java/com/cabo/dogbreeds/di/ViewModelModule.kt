package com.cabo.dogbreeds.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabo.dogbreeds.viewmodel.BreedFilterViewModel
import com.cabo.dogbreeds.viewmodel.BreedViewModel
import com.cabo.dogbreeds.viewmodel.ImageListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BreedViewModel::class)
    protected abstract fun breedViewModel(breedViewModel: BreedViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ImageListViewModel::class)
    protected abstract fun imageListViewModel(imageListViewModel: ImageListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BreedFilterViewModel::class)
    protected abstract fun breedFilterViewModel(imageListViewModel: BreedFilterViewModel): ViewModel
}