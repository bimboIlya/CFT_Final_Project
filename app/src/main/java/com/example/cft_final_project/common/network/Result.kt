package com.example.cft_final_project.common.network

sealed class Result<out R> {

    fun handle(
        onSuccess: (R) -> Unit = { },
        onFailure: (ex: Throwable) -> Unit = { }
    ) {
        when (this) {
            is Success -> onSuccess(this.data)
            is Error -> onFailure(this.exception)
        }
    }

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
