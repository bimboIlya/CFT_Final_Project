package com.example.cft_final_project.loans.data.model

data class Loan(
    val id: Int,
    val amount: Int,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String
)