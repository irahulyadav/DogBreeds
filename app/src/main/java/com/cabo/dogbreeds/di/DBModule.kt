package com.cabo.dogbreeds.di

import android.app.Application
import com.cabo.dogbreeds.data.local.BreedsDatabase
import com.cabo.dogbreeds.data.local.dao.BreedDao

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BreedsDatabase {
        return BreedsDatabase.getInstance(application)!!
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: BreedsDatabase): BreedDao {
        return appDatabase.breedDao()
    }

}
