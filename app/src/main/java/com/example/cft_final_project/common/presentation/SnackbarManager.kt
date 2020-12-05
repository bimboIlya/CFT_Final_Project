package com.example.cft_final_project.common.presentation

import androidx.annotation.StringRes
import com.example.cft_final_project.common.error_parser.ParsedError

interface SnackbarManager {
    fun showError(e: ParsedError)

    fun showMessage(@StringRes stringId: Int)

    fun showMessage(message: String)
}