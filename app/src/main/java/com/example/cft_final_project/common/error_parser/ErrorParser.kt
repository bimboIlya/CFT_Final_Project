package com.example.cft_final_project.common.error_parser

interface ErrorParser {
    fun parseError(ex: Throwable): ParsedError
}