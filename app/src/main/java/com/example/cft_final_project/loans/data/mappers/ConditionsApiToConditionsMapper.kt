package com.example.cft_final_project.loans.data.mappers

import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.model.LoanConditionsApiModel

object ConditionsApiToConditionsMapper : Mapper<LoanConditionsApiModel, LoanConditions> {
    override fun map(input: LoanConditionsApiModel): LoanConditions {
        input.apply {
            return LoanConditions(
                maxAmount = maxAmount ?: throw MissingBaseDataFromApiException(),
                percent = percent ?: throw MissingBaseDataFromApiException(),
                period = period ?: throw MissingBaseDataFromApiException()
            )
        }
    }

}