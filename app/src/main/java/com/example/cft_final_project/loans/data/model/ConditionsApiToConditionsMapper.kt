package com.example.cft_final_project.loans.data.model

import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.common.util.MissingBaseDataException

object ConditionsApiToConditionsMapper : Mapper<LoanConditionsApiModel, LoanConditions> {
    override fun map(input: LoanConditionsApiModel): LoanConditions {
        input.apply {
            return LoanConditions(
                maxAmount = maxAmount ?: throw MissingBaseDataException(),
                percent = percent ?: throw MissingBaseDataException(),
                period = period ?: throw MissingBaseDataException()
            )
        }
    }

}