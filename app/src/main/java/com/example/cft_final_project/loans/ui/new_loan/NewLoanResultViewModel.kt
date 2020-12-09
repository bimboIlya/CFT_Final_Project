package com.example.cft_final_project.loans.ui.new_loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.ui.new_loan.NewLoanFragment.Companion.NEW_LOAN_REQUEST_KEY

class NewLoanResultViewModel(
    savedState: SavedStateHandle
) : ViewModel() {

    val loanLiveData: LiveData<LoanUi> = savedState.getLiveData(NEW_LOAN_REQUEST_KEY)
}