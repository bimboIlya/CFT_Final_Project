package com.example.cft_final_project.di

import com.example.cft_final_project.common.network.NetworkService
import com.example.cft_final_project.loans.data.sources.LoanRepository
import com.example.cft_final_project.loans.data.sources.LoanRepositoryImpl
import com.example.cft_final_project.loans.data.sources.db.AppDatabase
import com.example.cft_final_project.loans.data.sources.network.LoanApiService
import com.example.cft_final_project.loans.domain.ClearCachedLoansUseCase
import com.example.cft_final_project.loans.domain.CreateLoanUseCase
import com.example.cft_final_project.loans.domain.GetAllLoansUseCase
import com.example.cft_final_project.loans.domain.GetLoanConditionsUseCase
import com.example.cft_final_project.loans.ui.loan_details.LoanDetailsViewModel
import com.example.cft_final_project.loans.ui.loan_list.LoanListViewModel
import com.example.cft_final_project.loans.ui.new_loan.NewLoanResultViewModel
import com.example.cft_final_project.loans.ui.new_loan.NewLoanViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loanModule = module {
    single { provideLoanApiService(get(named(TOKEN_NETWORK_SERVICE))) }
    single { provideLoanDao(get()) }
    single<LoanRepository> { LoanRepositoryImpl(get(), get(), get()) }

    factory { ClearCachedLoansUseCase(get()) }
    factory { CreateLoanUseCase(get()) }
    factory { GetAllLoansUseCase(get()) }
    factory { GetLoanConditionsUseCase(get()) }

    viewModel { LoanListViewModel(get(), get(), get()) }
    viewModel { LoanDetailsViewModel(get()) }
    viewModel { NewLoanViewModel(get(), get(), get()) }
    viewModel { NewLoanResultViewModel(get()) }
}

private fun provideLoanApiService(networkService: NetworkService): LoanApiService {
    return networkService.create(LoanApiService::class.java)
}

private fun provideLoanDao(db: AppDatabase) = db.loanDao()