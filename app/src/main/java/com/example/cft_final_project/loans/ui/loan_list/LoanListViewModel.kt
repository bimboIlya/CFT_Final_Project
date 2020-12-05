package com.example.cft_final_project.loans.ui.loan_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.common.error_parser.ErrorParser
import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import com.example.cft_final_project.common.util.mapListOrEmpty
import com.example.cft_final_project.loans.data.LoanRepository
import com.example.cft_final_project.loans.data.model.LoanToLoanUiMapper
import com.example.cft_final_project.loans.data.model.LoanUi
import kotlinx.coroutines.launch

class LoanListViewModel(
    private val loanRepo: LoanRepository,
    errorParser: ErrorParser
) : BaseViewModel(errorParser) {

    private val _loanListLiveData = MutableLiveData<List<LoanUi>>()
    val loanListLiveData: LiveData<List<LoanUi>> get() = _loanListLiveData

    private val _toLoanDetailsEvent = MutableLiveData<Event<LoanUi>>()
    val toLoanDetailEvent: LiveData<Event<LoanUi>> get() = _toLoanDetailsEvent

    private val _toNewLoanEvent = MutableLiveData<Event<Unit>>()
    val toNewLoanEvent: LiveData<Event<Unit>> get() = _toNewLoanEvent

    init {
        loadLoanList()
    }

    private fun loadLoanList() {
        loadingStarted()

        viewModelScope.launch {

            when (val result = loanRepo.getAllLoans()) {
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

    fun navigateToLoanDetails(loan: LoanUi) {
        _toLoanDetailsEvent.value = Event(loan)
    }

    fun navigateToNewLoan() {
        _toNewLoanEvent.value = Event(Unit)
    }

    fun retry() {
        loadLoanList()
    }
}