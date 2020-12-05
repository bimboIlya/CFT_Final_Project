package com.example.cft_final_project.loans.ui.loan_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.example.cft_final_project.common.error_parser.ErrorParser
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.loans.data.LoanRepository
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.ui.loan_list.LoanListFragment.Companion.LOAN_KEY

class LoanDetailsViewModel(
    savedState: SavedStateHandle,
    errorParser: ErrorParser,
    private val loanRepo: LoanRepository
) : BaseViewModel(errorParser) {

    private val _loanLiveData = savedState.getLiveData<LoanUi>(LOAN_KEY)
    val loanLiveData: LiveData<LoanUi> get() = _loanLiveData

    val loanId = loanLiveData.value?.id
        ?: throw IllegalArgumentException("no Loan was passed")
}