package com.example.cft_final_project.loans.data.model

import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import com.example.cft_final_project.common.util.Mapper

object LoanApiToLoanMapper : Mapper<LoanApiModel, Loan> {
    override fun map(input: LoanApiModel): Loan {
        input.apply {
            return Loan(
                id = id ?: throw MissingBaseDataFromApiException(),
                amount = amount ?: throw MissingBaseDataFromApiException(),
                date = date ?: throw MissingBaseDataFromApiException(),
                firstName = firstName  ?: throw MissingBaseDataFromApiException(),
                lastName = lastName ?: throw MissingBaseDataFromApiException(),
                percent = percent ?: throw MissingBaseDataFromApiException(),
                period = period ?: throw MissingBaseDataFromApiException(),
                phoneNumber = phoneNumber ?: throw MissingBaseDataFromApiException(),
                state = state ?: throw MissingBaseDataFromApiException()
            )
        }
    }
}