package com.example.cft_final_project.loans.data.model

import com.google.gson.annotations.SerializedName

class LoanApiModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("amount") val amount: Int?,
    @SerializedName("date") val date: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("percent") val percent: Double?,
    @SerializedName("period") val period: Int?,
    @SerializedName("phoneNumber") val phoneNumber: String?,
    @SerializedName("state") val state: String?
)