package com.example.cft_final_project.di

import com.example.cft_final_project.authentication.data.sources.UserRepository
import com.example.cft_final_project.authentication.data.sources.UserRepositoryImpl
import com.example.cft_final_project.authentication.data.sources.network.AuthApiService
import com.example.cft_final_project.authentication.domain.LoginUseCase
import com.example.cft_final_project.authentication.domain.RegisterUseCase
import com.example.cft_final_project.authentication.domain.RegistrationScenario
import com.example.cft_final_project.authentication.ui.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single<AuthApiService> { provideNetworkService(get(BASE_NETWORK_SERVICE)) }
    single<UserRepository> { UserRepositoryImpl(get()) }

    factory { LoginUseCase(get(), get(), get()) }
    factory { RegisterUseCase(get(), get()) }
    factory { RegistrationScenario(get(), get(), get(), get()) }

    viewModel { AuthViewModel(get(), get()) }
}
