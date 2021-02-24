package com.example.cft_final_project.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.authentication.domain.LoginUseCase
import com.example.cft_final_project.authentication.domain.RegistrationScenario
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import kotlinx.coroutines.launch

class AuthViewModel(
    private val registrationScenario: RegistrationScenario,
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {

    private val _toLoanListEvent = MutableLiveData<Event<Unit>>()
    val toLoanListEvent: LiveData<Event<Unit>> get() = _toLoanListEvent

    fun attemptRegistration(credentials: AuthParams) {
        viewModelScope.launch {
            withIndicator {
                registrationScenario(credentials).handleVm {
                    navigateToLoanList()
                }
            }
        }
    }

    fun attemptLogin(credentials: AuthParams) {
        viewModelScope.launch {
            withIndicator {
                loginUseCase(credentials).handleVm {
                    navigateToLoanList()
                }
            }
        }
    }

    private fun navigateToLoanList() {
        _toLoanListEvent.value = Event(Unit)
    }
}