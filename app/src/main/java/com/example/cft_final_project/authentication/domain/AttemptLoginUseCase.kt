package com.example.cft_final_project.authentication.domain

import com.example.cft_final_project.authentication.data.sources.UserRepository
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.domain.CoroutineUseCase

class AttemptLoginUseCase(
    private val userRepository: UserRepository
) : CoroutineUseCase<AuthParams, String>() {
    override suspend fun invoke(parameters: AuthParams) = userRepository.login(parameters)
}