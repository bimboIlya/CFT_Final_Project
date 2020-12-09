package com.example.cft_final_project.common.presentation

import androidx.annotation.StringRes
import com.example.cft_final_project.common.exceptions.error_parser.ParsedError

interface SnackbarManager {
    fun showError(error: ParsedError)

    fun showMessage(@StringRes stringId: Int)

    fun showMessage(message: String)
}