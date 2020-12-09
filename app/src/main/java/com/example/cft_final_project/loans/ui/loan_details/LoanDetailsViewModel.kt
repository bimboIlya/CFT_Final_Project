package com.example.cft_final_project.loans.ui.loan_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.ui.loan_list.LoanListFragment.Companion.LOAN_KEY

class LoanDetailsViewModel(
    savedState: SavedStateHandle,
) : ViewModel() {

    val loanLiveData: LiveData<LoanUi> = savedState.getLiveData(LOAN_KEY)
}