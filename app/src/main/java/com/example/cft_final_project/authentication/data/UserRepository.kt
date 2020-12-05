package com.example.cft_final_project.authentication.data

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.common.network.Result

interface UserRepository {

    suspend fun register(name: String, password: String): Result<User>

    suspend fun login(name: String, password: String): Result<String>
}