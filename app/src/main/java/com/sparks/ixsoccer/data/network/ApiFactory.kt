package com.sparks.ixsoccer.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://storage.googleapis.com"
    private fun retrofit(baseUrl: String) : Retrofit {
        return Retrofit.Builder()
                .client(OkHttpClient().newBuilder()
                        .build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val soccerApi: SoccerApi = retrofit(BASE_URL).create(SoccerApi::class.java)
}