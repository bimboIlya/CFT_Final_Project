package com.example.cft_final_project.common.exceptions.error_parser

import androidx.annotation.StringRes

data class ParsedError(
    @StringRes val errorMessageId: Int
)
