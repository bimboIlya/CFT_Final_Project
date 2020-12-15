package com.example.cft_final_project.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.authentication.domain.AttemptLoginUseCase
import com.example.cft_final_project.authentication.domain.AttemptRegistrationUseCase
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.network.Result.Error
import com.example.cft_final_project.common.network.Result.Success
import com.example.cft_final_project.common.presentation.BaseViewModel
import com.example.cft_final_project.common.util.Event
import kotlinx.coroutines.launch

class AuthViewModel(
    private val attemptRegistrationUseCase: AttemptRegistrationUseCase,
    private val attemptLoginUseCase: AttemptLoginUseCase,
    private val authManager: AuthManager,
    errorParser: ErrorParser
) : BaseViewModel(errorParser) {

    private val _toLoanListEvent = MutableLiveData<Event<Unit>>()
    val toLoanListEvent: LiveData<Event<Unit>> get() = _toLoanListEvent

    fun attemptRegistration(credentials: AuthParams) {
        viewModelScope.launch {
            loadingStarted()

            when (val result = attemptRegistrationUseCase(credentials)) {
                is Success -> attemptLogin(credentials)
                is Error -> {
                    loadingStopped()
                    emitErrorEvent(result.exception)
                }
            }
        }
    }

    fun attemptLogin(credentials: AuthParams) {
        viewModelScope.launch {
            loadingStarted()

            when (val result = attemptLoginUseCase(credentials)) {
                is Success -> {
                    authManager.setToken(result.data)
                    _toLoanListEvent.value = Event(Unit)
                }
                is Error -> emitErrorEvent(result.exception)
            }

            loadingStopped()
        }
    }
}