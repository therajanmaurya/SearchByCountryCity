package com.task.countriescities.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesnowService {

    @GET("countries")
    suspend fun getCountriesAndCities(): CountriesnowService

    companion object {
        private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

        fun create(): CountriesnowService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CountriesnowService::class.java)
        }
    }
}
