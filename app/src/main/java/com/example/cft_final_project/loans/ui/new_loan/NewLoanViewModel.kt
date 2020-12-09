package com.example.cft_final_project.loans.ui.new_loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import com.example.cft_final_project.loans.data.LoanRepository
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.model.LoanToLoanUiMapper
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.data.network.LoanRequestParams
import kotlinx.coroutines.launch

class NewLoanViewModel(
    private val loanRepo: LoanRepository,
    errorParser: ErrorParser
) : BaseViewModel(errorParser) {

    private val _loanConditionsLiveData: MutableLiveData<LoanConditions> = MutableLiveData()
    val loanConditionsLiveData: LiveData<LoanConditions> get() = _loanConditionsLiveData

    private val _toLoanRequestResultEvent = MutableLiveData<Event<LoanUi>>()
    val toLoanRequestResultEvent: LiveData<Event<LoanUi>> get() = _toLoanRequestResultEvent

    init {
        loadLoanConditions()
    }

    private fun loadLoanConditions() {
        viewModelScope.launch {
            loadingStarted()

            when (val result = loanRepo.getLoanConditions()) {
                is Result.Success -> _loanConditionsLiveData.value = result.data
                is Result.Error -> emitErrorEvent(result.exception)
            }

            loadingStopped()
        }
    }

    fun attemptToCreateLoan(loanParams: LoanRequestParams) {
        viewModelScope.launch {
            loadingStarted()

            when (val result = loanRepo.createLoan(loanParams)) {
                is Result.Success -> {
                    val loanUi = LoanToLoanUiMapper.map(result.data)
                    navigateToLoanRequestResult(loanUi)
                }
                is Result.Error -> emitErrorEvent(result.exception)
            }

            loadingStopped()
        }
    }

    private fun navigateToLoanRequestResult(loan: LoanUi) {
        _toLoanRequestResultEvent.value = Event(loan)
    }
}