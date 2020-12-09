package com.example.cft_final_project.common.exceptions.error_parser

interface ErrorParser {
    fun parseError(ex: Throwable): ParsedError
}