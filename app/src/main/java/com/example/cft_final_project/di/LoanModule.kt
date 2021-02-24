package com.example.cft_final_project.di

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
import org.koin.dsl.module

val loanModule = module {
    single<LoanApiService> { provideNetworkService(get(TOKEN_NETWORK_SERVICE)) }
    single { provideLoanDao(get()) }
    single<LoanRepository> { LoanRepositoryImpl(get(), get()) }

    factory { ClearCachedLoansUseCase(get(), get()) }
    factory { CreateLoanUseCase(get(), get()) }
    factory { GetAllLoansUseCase(get(), get()) }
    factory { GetLoanConditionsUseCase(get(), get()) }

    viewModel { LoanListViewModel(get(), get()) }
    viewModel { LoanDetailsViewModel(get()) }
    viewModel { NewLoanViewModel(get(), get()) }
    viewModel { NewLoanResultViewModel(get()) }
}

private fun provideLoanDao(db: AppDatabase) = db.loanDao()