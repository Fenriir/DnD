package com.example.dnd_app.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val okHttpClient by lazy{
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_TOKEN))
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl( BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val charactersApi: CharactersApi by lazy{
        retrofit.create(CharactersApi::class.java)
    }

    val featyApi: FeatyApi by lazy{
        retrofit.create(FeatyApi::class.java)
    }

    val rasesApi: RasesApi by lazy{
        retrofit.create(RasesApi::class.java)
    }
}