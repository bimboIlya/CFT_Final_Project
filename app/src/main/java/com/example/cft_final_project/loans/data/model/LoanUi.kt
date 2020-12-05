package com.example.cft_final_project.loans.data.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.cft_final_project.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoanUi(
    val id: Int,
    val amount: Int,
    val date: String,
    val firstName: String,
    val lastName: String,
    val percent: Double?,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
) : Parcelable


enum class LoanState(
    @StringRes textId: Int,
    @ColorRes colorId: Int
) {
    APPROVED(R.string.loan_approved, R.color.approved),
    REGISTERED(R.string.loan_registered, R.color.registered),
    REJECTED(R.string.loan_rejected, R.color.rejected),
}