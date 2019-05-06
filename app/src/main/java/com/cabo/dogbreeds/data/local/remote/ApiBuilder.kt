package com.cabo.dogbreeds.data.local.remote

//class ApiBuilder {
//    companion object {
//        private val URL = "https://dog.ceo"
//
//        val logger = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        val okHttp = OkHttpClient.Builder()
//            .addInterceptor(logger)
//
//        private val builder = Retrofit.Builder()
//            .baseUrl(URL)
//            .client(okHttp.build())
//            .addConverterFactory(GsonConverterFactory.create())
//
//        private val retrofit = builder.build()
//
//        fun <S> buildService(serviceType: Class<S>): S {
//            return retrofit.create(serviceType)
//        }
//    }
//}