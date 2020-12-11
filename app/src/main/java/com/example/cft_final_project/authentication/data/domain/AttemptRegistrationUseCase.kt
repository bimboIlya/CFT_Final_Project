package com.example.cft_final_project.authentication.data.domain

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.network.AuthParams
import com.example.cft_final_project.common.domain.CoroutineUseCase

class AttemptRegistrationUseCase(
    private val userRepository: UserRepository
) : CoroutineUseCase<AuthParams, User>() {
    override suspend fun invoke(parameters: AuthParams) =
        userRepository.register(parameters)
}