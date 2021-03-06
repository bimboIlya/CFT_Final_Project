package com.example.cft_final_project.loans.data.sources.network

import com.example.cft_final_project.loans.data.model.LoanApiModel
import com.example.cft_final_project.loans.data.model.LoanConditionsApiModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoanApiService {

    @POST("loans")
    suspend fun createLoan(
        @Body loanRequest: LoanRequestParams
    ): LoanApiModel

    @GET("loans/{id}")
    suspend fun getLoanById(
        @Path("id") loanId: Int
    ): LoanApiModel

    @GET("loans/all")
    suspend fun getAllLoans(): List<LoanApiModel>

    @GET("loans/conditions")
    suspend fun getLoanConditions(): LoanConditionsApiModel
}