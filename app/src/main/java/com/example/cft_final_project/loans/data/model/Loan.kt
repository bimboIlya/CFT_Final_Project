package com.example.cft_final_project.loans.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Loan(
    @PrimaryKey val id: Int,
    val amount: Int,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String
)