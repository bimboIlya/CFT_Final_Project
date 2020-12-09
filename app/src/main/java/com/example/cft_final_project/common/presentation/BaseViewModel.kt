package com.example.cft_final_project.common.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.exceptions.error_parser.ParsedError
import com.example.cft_final_project.common.util.Event

abstract class BaseViewModel(
    private val errorParser: ErrorParser
) : ViewModel() {

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
}