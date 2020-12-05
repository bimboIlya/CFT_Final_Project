package com.example.cft_final_project.common.error_parser

import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.MissingBaseDataException
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException

class ErrorParserImpl : ErrorParser {
    override fun parseError(ex: Throwable): ParsedError {
        return when (ex) {
            is HttpException -> handleHttpExceptions(ex)
            is SocketTimeoutException -> ParsedError(R.string.exception_timeout)
            is MissingBaseDataException -> ParsedError(R.string.exception_server)
            else -> ParsedError(R.string.exception_unknown)
        }.also { Timber.e(ex) }
    }

    private fun handleHttpExceptions(ex: HttpException): ParsedError {
        val isUserAlreadyExistsException =
            ex.response()?.errorBody()?.string()?.contains("User already exist", true)
                    ?: false && ex.code() == 400  // todo поискать более красивый способ достать сообщение

        return when {
            ex.code() == 401 -> ParsedError(R.string.exception_unauthorized)
            ex.code() == 403 -> ParsedError(R.string.exception_forbidden)
            ex.code() == 404 -> ParsedError(R.string.exception_not_found)
            ex.code() == 500 -> ParsedError(R.string.exception_server)
            isUserAlreadyExistsException -> ParsedError(R.string.exception_user_exists)
            else -> ParsedError(R.string.exception_unknown)
        }
    }
}