package com.example.cft_final_project.authentication.domain

import com.example.cft_final_project.authentication.data.sources.UserRepository
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.domain.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginUseCase(
    private val userRepository: UserRepository,
    private val authManager: AuthManager,
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<AuthParams, String>(coroutineDispatcher) {
    override suspend fun execute(params: AuthParams): String {
        val token = userRepository.login(params)
        authManager.setToken(token)

        return token
    }
}