package com.example.cft_final_project.authentication.data.domain

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.network.AuthParams
import com.example.cft_final_project.common.network.Result

interface UserRepository {

    suspend fun register(credentials: AuthParams): Result<User>

    suspend fun login(credentials: AuthParams): Result<String>
}