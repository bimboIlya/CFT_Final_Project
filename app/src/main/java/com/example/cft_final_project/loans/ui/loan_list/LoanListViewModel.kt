package com.example.cft_final_project.loans.ui.loan_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
) : BaseViewModel() {

    private val _loanListLiveData = MutableLiveData<List<LoanUi>>()
    val loanListLiveData: LiveData<List<LoanUi>> get() = _loanListLiveData

    private val _toLoanDetailsEvent = MutableLiveData<Event<LoanUi>>()
    val toLoanDetailEvent: LiveData<Event<LoanUi>> get() = _toLoanDetailsEvent

    private val _toGuestScreenEvent = MutableLiveData<Event<Unit>>()
    val toGuestScreenEvent: LiveData<Event<Unit>> get() = _toGuestScreenEvent

    init {
        loadLoanList()
    }

    private fun loadLoanList() {
        viewModelScope.launch {
            withIndicator {
                getAllLoansUseCase(Unit).handle(
                    onSuccess = { _loanListLiveData.value = LoanToLoanUiMapper.mapListOrEmpty(it) },
                    onFailure = { emitErrorEvent(it) }
                )
            }
        }
    }

    fun clearCachedLoans() {
        viewModelScope.launch {
            clearCachedLoansUseCase(Unit).handle(
                onSuccess = { navigateToGuestScreen() },
                onFailure = { emitErrorEvent(it) }
            )
        }
    }

    fun navigateToGuestScreen() {
        _toGuestScreenEvent.value = Event(Unit)
    }

    fun navigateToLoanDetails(loan: LoanUi) {
        _toLoanDetailsEvent.value = Event(loan)
    }

    fun retry() {
        loadLoanList()
    }
}