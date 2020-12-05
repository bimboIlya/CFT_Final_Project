package com.example.cft_final_project.authentication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.authentication.data.UserRepository
import com.example.cft_final_project.common.util.Event
import com.example.cft_final_project.common.error_parser.ErrorParser
import com.example.cft_final_project.common.network.Result.*
import com.example.cft_final_project.common.presentation.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepo: UserRepository,
    private val authManager: AuthManager,
    errorParser: ErrorParser
) : BaseViewModel(errorParser) {

    private val _toLoanListEvent = MutableLiveData<Event<Unit>>()
    val toLoanListEvent: LiveData<Event<Unit>> get() = _toLoanListEvent

    fun attemptRegistration(name: String, password: String) {
        viewModelScope.launch {
            loadingStarted()

            // todo check login/pass

            when (val result = userRepo.register(name, password)) {
                is Success -> attemptLogin(name, password)
                is Error -> {
                    loadingStopped()
                    emitErrorEvent(result.exception)
                }
            }
        }
    }

    fun attemptLogin(name: String, password: String) {
        viewModelScope.launch {
            loadingStarted()

            // todo check login/pass

            when (val result = userRepo.login(name, password)) {
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