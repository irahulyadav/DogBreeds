package com.cabo.dogbreeds

import android.app.Application
import com.cabo.dogbreeds.di.*

class MainApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .retrofitModule(RetrofitModule("https://dog.ceo"))
            .dBModule(DBModule())
            .build()

        appComponent.inject(this)
    }
}
