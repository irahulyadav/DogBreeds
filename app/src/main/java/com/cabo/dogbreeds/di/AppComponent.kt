package com.cabo.dogbreeds.di


import com.cabo.dogbreeds.MainActivity
import com.cabo.dogbreeds.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, AppModule::class, RetrofitModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(application: MainApplication)

    // fun breedRepository(): BreedRepository

}