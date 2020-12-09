package com.example.cft_final_project.common.exceptions

/** Указывает на отсутствие необходимых
 *  данных со стороны сервера */
class MissingBaseDataFromApiException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)