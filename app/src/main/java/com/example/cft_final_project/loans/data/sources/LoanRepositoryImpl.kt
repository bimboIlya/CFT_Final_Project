package com.example.cft_final_project.loans.data.sources

import com.example.cft_final_project.common.util.mapListOrEmpty
import com.example.cft_final_project.loans.data.mappers.ConditionsApiToConditionsMapper
import com.example.cft_final_project.loans.data.mappers.LoanApiToLoanMapper
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.sources.db.LoanDao
import com.example.cft_final_project.loans.data.sources.network.LoanApiService
import com.example.cft_final_project.loans.data.sources.network.LoanRequestParams

class LoanRepositoryImpl(
    private val loanApiService: LoanApiService,
    private val loanDao: LoanDao,
) : LoanRepository {

    override suspend fun createLoan(loanParams: LoanRequestParams): Loan {
        val loanApi = loanApiService.createLoan(loanParams)
        val loan = LoanApiToLoanMapper.map(loanApi)

        return loan
    }

    override suspend fun getLoanById(id: Int): Loan {
        val loanApi = loanApiService.getLoanById(id)
        val loan = LoanApiToLoanMapper.map(loanApi)

        return loan
    }

    override suspend fun getAllLoans(): List<Loan> {
        return try {
            val loanApiList = loanApiService.getAllLoans()
            val loanList = LoanApiToLoanMapper.mapListOrEmpty(loanApiList)

            loanDao.insertAll(loanList)

            loanList
        } catch (ex: Throwable) {
            val cachedLoanList = loanDao.getAllLoans()

            when (cachedLoanList.isEmpty()) {
                false -> cachedLoanList
                true -> throw ex
            }
        }
    }

    override suspend fun getLoanConditions(): LoanConditions {
        val loanConditionsApi = loanApiService.getLoanConditions()
        val loanConditions = ConditionsApiToConditionsMapper.map(loanConditionsApi)

        return loanConditions
    }


    override suspend fun clearCachedLoans() {
        loanDao.dropTable()
    }
}