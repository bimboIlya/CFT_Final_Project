package com.example.cft_final_project.loans.data.sources.network


import com.google.gson.annotations.SerializedName

class LoanRequestParams(
    @SerializedName("amount") val amount: Int,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("percent") val percent: Double,
    @SerializedName("period") val period: Int,
    @SerializedName("phoneNumber") val phoneNumber: String
)