package com.example.cft_final_project.common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.exceptions.error_parser.ParsedError
import com.example.cft_final_project.common.network.Result
import com.example.cft_final_project.common.util.Event
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val errorParser: ErrorParser by inject()

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorEvent = MutableLiveData<Event<ParsedError>>()
    val errorEvent: LiveData<Event<ParsedError>> get() = _errorEvent

    protected fun emitErrorEvent(ex: Throwable) {
        val parsedError = errorParser.parseError(ex)
        _errorEvent.value = Event(parsedError)
    }

    protected fun loadingStarted() {
        _isLoadingLiveData.value = true
    }

    protected fun loadingStopped() {
        _isLoadingLiveData.value = false
    }

    protected suspend fun withIndicator(block: suspend () -> Unit) {
        loadingStarted()
        block()
        loadingStopped()
    }

    protected fun <R> Result<R>.handleVm(
        onFailure: (ex: Throwable) -> Unit = { emitErrorEvent(it) },
        onSuccess: (data: R) -> Unit = { }
    ) {
        handle(onFailure, onSuccess)
    }
}