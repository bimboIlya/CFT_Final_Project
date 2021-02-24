package com.example.cft_final_project.authentication.data.sources

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.model.UserApiToUserMapper
import com.example.cft_final_project.authentication.data.sources.network.AuthApiService
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException

class UserRepositoryImpl(
    private val authApiService: AuthApiService,
) : UserRepository {

    override suspend fun register(credentials: AuthParams): User {
        val userApi = authApiService.registration(credentials)
        val user = UserApiToUserMapper.map(userApi)

        return user
    }


    override suspend fun login(credentials: AuthParams): String {
        val tokenApi = authApiService.login(credentials)?.string()
        val token: String = tokenApi ?: throw MissingBaseDataFromApiException()

        return token
    }
}