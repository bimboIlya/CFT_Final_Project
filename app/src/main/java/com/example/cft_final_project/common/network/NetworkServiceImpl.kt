package com.example.cft_final_project.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkServiceImpl(
    client: OkHttpClient,
    private val retrofit: Retrofit
) : NetworkService {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val newClient = client.newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    override fun <T> create(clazz: Class<T>): T {
        return retrofit.newBuilder()
            .client(newClient)
            .build()
            .create(clazz)
    }
}