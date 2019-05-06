package com.cabo.dogbreeds.data.local.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {
    companion object {
        private val URL = "https://dog.ceo"

        val logger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttp = OkHttpClient.Builder()
            .addInterceptor(logger)

        private val builder = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = builder.build()

        fun <S> buildService(serviceType: Class<S>): S {
            return retrofit.create(serviceType)
        }
    }
}