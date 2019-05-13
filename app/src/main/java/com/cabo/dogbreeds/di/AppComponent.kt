package com.cabo.dogbreeds.di


import com.cabo.dogbreeds.FilterListActivity
import com.cabo.dogbreeds.ImageListActivity
import com.cabo.dogbreeds.MainActivity
import com.cabo.dogbreeds.MainApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DBModule::class, AppModule::class, RetrofitModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: ImageListActivity)

    fun inject(application: MainApplication)

    fun inject(application: FilterListActivity)

}