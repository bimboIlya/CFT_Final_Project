package com.example.cft_final_project.common.exceptions.error_parser

import com.example.cft_final_project.R
import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorParserImpl : ErrorParser {
    override fun parseError(ex: Throwable): ParsedError {
        return when (ex) {
            is HttpException -> handleHttpExceptions(ex)
            is SocketTimeoutException -> ParsedError(R.string.exception_timeout)
            is MissingBaseDataFromApiException -> ParsedError(R.string.exception_server)
            is UnknownHostException -> ParsedError(R.string.exception_no_connection)
            else -> ParsedError(R.string.exception_unknown)
        }.also { Timber.e(ex) }
    }

    private fun handleHttpExceptions(ex: HttpException): ParsedError {
        val isUserAlreadyExistsException = ex.code() == 400 &&
                ex.response()?.errorBody()?.string()
                    ?.contains("User already exist", true) ?: false

        val isWrongLoginOrPassword = ex.code() == 404 &&
                ex.response()?.errorBody()?.string()
                    ?.contains("User not found", true) ?: false

        return when {
            isUserAlreadyExistsException -> ParsedError(R.string.exception_user_exists)
            isWrongLoginOrPassword -> ParsedError(R.string.wrong_login_or_password)
            ex.code() == 401 -> ParsedError(R.string.exception_unauthorized)
            ex.code() == 403 -> ParsedError(R.string.exception_forbidden)
            ex.code() == 404 -> ParsedError(R.string.exception_not_found)
            ex.code() == 500 -> ParsedError(R.string.exception_server)
            else -> ParsedError(R.string.exception_unknown)
        }
    }
}