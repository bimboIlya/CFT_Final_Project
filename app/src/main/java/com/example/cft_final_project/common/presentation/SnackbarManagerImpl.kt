package com.example.cft_final_project.common.presentation

import android.view.View
import com.example.cft_final_project.common.error_parser.ParsedError

class SnackbarManagerImpl private constructor(

) : SnackbarManager {

    override fun showError(e: ParsedError) {  }

    override fun showMessage(stringId: Int) {  }

    override fun showMessage(message: String) {  }

    companion object {
        fun View.createSnackBarManager(): SnackbarManager {
            return SnackbarManagerImpl()
        }
    }
}