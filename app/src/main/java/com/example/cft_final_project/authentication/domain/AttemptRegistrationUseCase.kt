package com.example.cft_final_project.authentication.domain

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.sources.UserRepository
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.domain.CoroutineUseCase

class AttemptRegistrationUseCase(
    private val userRepository: UserRepository
) : CoroutineUseCase<AuthParams, User>() {
    override suspend fun invoke(parameters: AuthParams) =
        userRepository.register(parameters)
}