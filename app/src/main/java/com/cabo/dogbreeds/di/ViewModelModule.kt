package com.cabo.dogbreeds.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.an.trailers.di.module.ViewModelKey
import com.cabo.dogbreeds.BreedViewModel
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


}