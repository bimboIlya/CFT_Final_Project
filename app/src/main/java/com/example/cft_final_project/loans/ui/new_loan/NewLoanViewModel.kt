package com.example.cft_final_project.loans.ui.new_loan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import com.example.cft_final_project.loans.data.mappers.LoanToLoanUiMapper
import com.example.cft_final_project.loans.data.model.LoanConditions
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.data.sources.network.LoanRequestParams
import com.example.cft_final_project.loans.domain.CreateLoanUseCase
import com.example.cft_final_project.loans.domain.GetLoanConditionsUseCase
import kotlinx.coroutines.launch

class NewLoanViewModel(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val createLoanUseCase: CreateLoanUseCase,
) : BaseViewModel() {

    private val _loanConditionsLiveData: MutableLiveData<LoanConditions> = MutableLiveData()
    val loanConditionsLiveData: LiveData<LoanConditions> get() = _loanConditionsLiveData

    private val _toLoanRequestResultEvent = MutableLiveData<Event<LoanUi>>()
    val toLoanRequestResultEvent: LiveData<Event<LoanUi>> get() = _toLoanRequestResultEvent

    init {
        loadLoanConditions()
    }

    private fun loadLoanConditions() {
        viewModelScope.launch {
            withIndicator {
                getLoanConditionsUseCase(Unit).handleVm {
                    _loanConditionsLiveData.value = it
                }
            }
        }
    }

    fun attemptToCreateLoan(loanParams: LoanRequestParams) {
        viewModelScope.launch {
            withIndicator {
                createLoanUseCase(loanParams).handleVm {
                    val loanUi = LoanToLoanUiMapper.map(it)
                    navigateToLoanRequestResult(loanUi)
                }
            }
        }
    }

    private fun navigateToLoanRequestResult(loan: LoanUi) {
        _toLoanRequestResultEvent.value = Event(loan)
    }
}