package com.example.cft_final_project.loans.data.model


import com.google.gson.annotations.SerializedName

class LoanConditionsApiModel(
    @SerializedName("maxAmount") val maxAmount: Int?,
    @SerializedName("percent") val percent: Double?,
    @SerializedName("period") val period: Int?
)