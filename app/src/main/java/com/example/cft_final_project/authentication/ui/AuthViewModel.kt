package com.example.cft_final_project.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.authentication.domain.AttemptLoginUseCase
import com.example.cft_final_project.authentication.domain.AttemptRegistrationUseCase
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import kotlinx.coroutines.launch

class AuthViewModel(
    private val attemptRegistrationUseCase: AttemptRegistrationUseCase,
    private val attemptLoginUseCase: AttemptLoginUseCase,
    private val authManager: AuthManager,
) : BaseViewModel() {

    private val _toLoanListEvent = MutableLiveData<Event<Unit>>()
    val toLoanListEvent: LiveData<Event<Unit>> get() = _toLoanListEvent

    fun attemptRegistration(credentials: AuthParams) {
        viewModelScope.launch {
            withIndicator {
                attemptRegistrationUseCase(credentials).handle(
                    onSuccess = { attemptLogin(credentials) },
                    onFailure = { emitErrorEvent(it) }
                )
            }
        }
    }

    fun attemptLogin(credentials: AuthParams) {
        viewModelScope.launch {
            withIndicator {
                attemptLoginUseCase(credentials).handle(
                    onSuccess = {
                        authManager.setToken(it)
                        _toLoanListEvent.value = Event(Unit)
                    },
                    onFailure = { emitErrorEvent(it) }
                )
            }
        }
    }
}