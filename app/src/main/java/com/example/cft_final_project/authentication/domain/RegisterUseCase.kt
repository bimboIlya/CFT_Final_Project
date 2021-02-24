package com.example.cft_final_project.authentication.domain

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.sources.UserRepository
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.domain.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class RegisterUseCase(
    private val userRepository: UserRepository,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<AuthParams, User>(coroutineDispatcher) {
    override suspend fun execute(params: AuthParams): User {
        return userRepository.register(params)
    }
}