package com.example.cft_final_project.authentication.data.network

import com.example.cft_final_project.authentication.data.model.UserApiModel
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("registration")
    suspend fun registration(
        @Body credentials: AuthParams
    ): UserApiModel

    @POST("login")
    suspend fun login(
        @Body credentials: AuthParams
    ): ResponseBody?
}