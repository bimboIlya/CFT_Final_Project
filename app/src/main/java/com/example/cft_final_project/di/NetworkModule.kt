package com.example.cft_final_project.di

import com.example.cft_final_project.BuildConfig
import com.example.cft_final_project.common.network.NetworkService
import com.example.cft_final_project.common.network.NetworkServiceImpl
import com.example.cft_final_project.common.network.TokenNetworkServiceImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideBaseHttpClient() }
    single { provideBaseRetrofit() }

    single<NetworkService>(BASE_NETWORK_SERVICE) {
        NetworkServiceImpl(get(), get())
    }

    single<NetworkService>(TOKEN_NETWORK_SERVICE) {
        TokenNetworkServiceImpl(get(), get(), get())
    }
}

private fun provideBaseHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

private fun provideBaseRetrofit(): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.SERVER_URL)
        .build()
}

inline fun <reified S> provideNetworkService(
    service: NetworkService
): S = service.create(S::class.java)

