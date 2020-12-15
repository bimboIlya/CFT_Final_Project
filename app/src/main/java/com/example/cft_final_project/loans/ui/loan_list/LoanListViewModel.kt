package com.example.cft_final_project.loans.ui.loan_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import com.example.cft_final_project.common.util.mapListOrEmpty
import com.example.cft_final_project.loans.data.mappers.LoanToLoanUiMapper
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.domain.ClearCachedLoansUseCase
import com.example.cft_final_project.loans.domain.GetAllLoansUseCase
import kotlinx.coroutines.launch

class LoanListViewModel(
    private val getAllLoansUseCase: GetAllLoansUseCase,
    private val clearCachedLoansUseCase: ClearCachedLoansUseCase,
    errorParser: ErrorParser
) : BaseViewModel(errorParser) {

    private val _loanListLiveData = MutableLiveData<List<LoanUi>>()
    val loanListLiveData: LiveData<List<LoanUi>> get() = _loanListLiveData

    private val _toLoanDetailsEvent = MutableLiveData<Event<LoanUi>>()
    val toLoanDetailEvent: LiveData<Event<LoanUi>> get() = _toLoanDetailsEvent

    init {
        loadLoanList()
    }

    private fun loadLoanList() {
        viewModelScope.launch {
            loadingStarted()

            when (val result = getAllLoansUseCase(Unit)) {
                is Result.Success -> {
                    _loanListLiveData.value =
                        LoanToLoanUiMapper.mapListOrEmpty(result.data)
                }
                is Result.Error -> {
                    loadingStopped()
                    emitErrorEvent(result.exception)
                }
            }

            loadingStopped()
        }
    }

    fun clearCachedLoans() {
        viewModelScope.launch {
            clearCachedLoansUseCase(Unit)
        }
    }

    fun navigateToLoanDetails(loan: LoanUi) {
        _toLoanDetailsEvent.value = Event(loan)
    }

    fun retry() {
        loadLoanList()
    }
}