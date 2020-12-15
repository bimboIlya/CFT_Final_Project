package com.example.cft_final_project.loans.data.mappers

import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.loans.data.model.Loan
import com.example.cft_final_project.loans.data.model.LoanStatus
import com.example.cft_final_project.loans.data.model.LoanUi
import java.text.SimpleDateFormat
import java.util.*

object LoanToLoanUiMapper : Mapper<Loan, LoanUi> {
    override fun map(input: Loan): LoanUi {
        input.apply {
            return LoanUi(
                id = id,
                amount = amount,
                date = date.changeDateFormat(),
                firstName = firstName,
                lastName = lastName,
                percent = percent,
                period = period,
                phoneNumber = phoneNumber,
                status = LoanStatus.valueOf(state)
            )
        }
    }

    // 2020-12-06T12:54:09.225+00:00 -> 06.12.2020
    private fun String.changeDateFormat(): String {
        val inputDateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
        val date = inputDateFormat.parse(this)
        return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
    }
}
