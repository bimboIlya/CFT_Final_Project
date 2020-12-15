package com.example.cft_final_project.authentication.data.sources

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.network.Result

interface UserRepository {

    suspend fun register(credentials: AuthParams): Result<User>

    suspend fun login(credentials: AuthParams): Result<String>
}