package com.example.cft_final_project.loans.data.model

import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.common.util.MissingBaseDataException

object LoanApiToLoanMapper : Mapper<LoanApiModel, Loan> {
    override fun map(input: LoanApiModel): Loan {
        input.apply {
            return Loan(
                id = id ?: throw MissingBaseDataException(),
                amount = amount ?: throw MissingBaseDataException(),
                date = date ?: throw MissingBaseDataException(),
                firstName = firstName  ?: throw MissingBaseDataException(),
                lastName = lastName ?: throw MissingBaseDataException(),
                percent = percent ?: throw MissingBaseDataException(),
                period = period ?: throw MissingBaseDataException(),
                phoneNumber = phoneNumber ?: throw MissingBaseDataException(),
                state = state ?: throw MissingBaseDataException()
            )
        }
    }
}