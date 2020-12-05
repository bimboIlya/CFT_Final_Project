package com.example.cft_final_project.di

import com.example.cft_final_project.authentication.data.network.AuthApiService
import com.example.cft_final_project.authentication.data.UserRepository
import com.example.cft_final_project.authentication.data.UserRepositoryImpl
import com.example.cft_final_project.authentication.ui.AuthViewModel
import com.example.cft_final_project.common.network.NetworkService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val authModule = module {
    single { provideAuthService(get(named(BASE_NETWORK_SERVICE))) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    viewModel { AuthViewModel(get(), get(), get()) }
}

private fun provideAuthService(networkService: NetworkService): AuthApiService {
    return networkService.create(AuthApiService::class.java)
}