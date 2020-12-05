package com.example.cft_final_project.loans.data.model

import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.common.util.MissingBaseDataException

object LoanToLoanUiMapper : Mapper<Loan, LoanUi> {
    override fun map(input: Loan): LoanUi {
        input.apply {
            return LoanUi(
                id = id,
                amount = amount,
                date = date,
                firstName = firstName,
                lastName = lastName,
                percent = percent,
                period = period,
                phoneNumber = phoneNumber,
                state = LoanState.valueOf(state)
            )
        }
    }
}
