package com.example.cft_final_project.di

import com.example.cft_final_project.common.network.NetworkService
import com.example.cft_final_project.loans.data.LoanRepository
import com.example.cft_final_project.loans.data.LoanRepositoryImpl
import com.example.cft_final_project.loans.data.network.LoanApiService
import com.example.cft_final_project.loans.ui.loan_details.LoanDetailsViewModel
import com.example.cft_final_project.loans.ui.loan_list.LoanListViewModel
import com.example.cft_final_project.loans.ui.new_loan.NewLoanResultViewModel
import com.example.cft_final_project.loans.ui.new_loan.NewLoanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loanModule = module {
    single { provideLoanApiService(get(named(TOKEN_NETWORK_SERVICE))) }
    single<LoanRepository> { LoanRepositoryImpl(get(), get()) }
    viewModel { LoanListViewModel(get(), get()) }
    viewModel { LoanDetailsViewModel(get()) }
    viewModel { NewLoanViewModel(get(), get()) }
    viewModel { NewLoanResultViewModel(get()) }
}

private fun provideLoanApiService(networkService: NetworkService): LoanApiService {
    return networkService.create(LoanApiService::class.java)
}