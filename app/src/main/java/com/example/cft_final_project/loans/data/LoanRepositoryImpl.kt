package com.example.cft_final_project.loans.data

import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.common.util.mapListOrEmpty
import com.example.cft_final_project.loans.data.model.ConditionsApiToConditionsMapper
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.model.LoanApiToLoanMapper
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.network.LoanApiService
import com.example.cft_final_project.loans.data.network.LoanRequestParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoanRepositoryImpl(
    private val loanApiService: LoanApiService,
    private val dispatcherIO: CoroutineDispatcher
) : LoanRepository {

    override suspend fun createLoan(loanParams: LoanRequestParams): Result<Loan> =
        withContext(dispatcherIO) {
            return@withContext try {
                val loanApi = loanApiService.createLoan(loanParams)
                val loan = LoanApiToLoanMapper.map(loanApi)

                Result.Success(loan)
            } catch (ex: Throwable) {
                Result.Error(ex)
            }
        }

    override suspend fun getLoanById(id: Int): Result<Loan> = withContext(dispatcherIO) {
        return@withContext try {
            val loanApi = loanApiService.getLoanById(id)
            val loan = LoanApiToLoanMapper.map(loanApi)

            Result.Success(loan)
        } catch (ex: Throwable) {
            Result.Error(ex)
        }
    }

    override suspend fun getAllLoans(): Result<List<Loan>> = withContext(dispatcherIO) {
        return@withContext try {
            val loanApiList = loanApiService.getAllLoans()
            val loan = LoanApiToLoanMapper.mapListOrEmpty(loanApiList)

            Result.Success(loan)
        } catch (ex: Throwable) {
            Result.Error(ex)
        }
    }

    override suspend fun getLoanConditions(): Result<LoanConditions> = withContext(dispatcherIO) {
        return@withContext try {
            val loanConditionsApi = loanApiService.getLoanConditions()
            val loanConditions = ConditionsApiToConditionsMapper.map(loanConditionsApi)

            Result.Success(loanConditions)
        } catch (ex: Throwable) {
            Result.Error(ex)
        }
    }
}