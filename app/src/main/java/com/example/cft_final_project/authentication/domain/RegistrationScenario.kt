package com.example.cft_final_project.authentication.domain

import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.domain.Scenario
import kotlinx.coroutines.CoroutineDispatcher

class RegistrationScenario(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val authManager: AuthManager,
    coroutineDispatcher: CoroutineDispatcher
) : Scenario<AuthParams, Unit>(coroutineDispatcher) {

    override suspend fun execute(params: AuthParams) {
        registerUseCase.execute(params)
        val token = loginUseCase.execute(params)
        authManager.setToken(token)
    }
}