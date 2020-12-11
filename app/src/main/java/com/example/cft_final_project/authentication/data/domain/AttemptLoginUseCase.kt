package com.example.cft_final_project.authentication.data.domain

import com.example.cft_final_project.authentication.data.network.AuthParams
import com.example.cft_final_project.common.domain.CoroutineUseCase

class AttemptLoginUseCase(
    private val userRepository: UserRepository
) : CoroutineUseCase<AuthParams, String>() {
    override suspend fun invoke(parameters: AuthParams) = userRepository.login(parameters)
}