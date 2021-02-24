package com.example.cft_final_project.authentication.data.sources

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.sources.network.AuthParams

interface UserRepository {

    suspend fun register(credentials: AuthParams): User

    suspend fun login(credentials: AuthParams): String
}