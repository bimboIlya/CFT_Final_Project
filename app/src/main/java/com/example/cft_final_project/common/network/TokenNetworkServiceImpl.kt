package com.example.cft_final_project.common.network

import com.example.cft_final_project.common.AuthManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Конкретная реализация [NetworkService].
 * При запросе добавляется access токен из [AuthManager]
 */
class TokenNetworkServiceImpl(
    client: OkHttpClient,
    private val retrofit: Retrofit,
    private val authManager: AuthManager
) : NetworkService {

    private val tokenInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            val accessToken = authManager.accessToken
                ?: return chain.proceed(original)

            val newRequest = original.newBuilder()
                .addHeader("Authorization", accessToken)
                .build()

            return chain.proceed(newRequest)
        }
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val newClient = client.newBuilder()
        .addInterceptor(tokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    override fun <T> create(clazz: Class<T>): T {
        return retrofit.newBuilder()
            .client(newClient)
            .build()
            .create(clazz)
    }
}