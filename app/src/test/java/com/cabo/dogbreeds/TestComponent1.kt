package com.cabo.dogbreeds


import com.cabo.dogbreeds.di.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface TestComponent1 {

    fun poke(activity: ApiUnitTest)

}