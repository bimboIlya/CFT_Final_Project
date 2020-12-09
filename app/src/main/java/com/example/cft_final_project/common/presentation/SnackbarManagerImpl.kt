package com.example.cft_final_project.common.presentation

import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import com.example.cft_final_project.common.exceptions.error_parser.ParsedError
import com.example.cft_final_project.common.util.delegates.SnackbarManagerDelegate
import com.google.android.material.snackbar.Snackbar

/**
 * Хранит ссылку на View
 * Чтобы она не утекла, нужно обнулять в onDestroyView()
 * см. [SnackbarManagerDelegate]
 */
class SnackbarManagerImpl(
    private val view: View,
    private val anchorId: Int?
) : SnackbarManager, LifecycleObserver {

    private val snackbar: Snackbar by lazy {
        Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    }

    init {
        anchorId?.let {
            snackbar.setAnchorView(anchorId)
        }
    }

    override fun showError(error: ParsedError) {
        snackbar.setText(error.errorMessageId).show()
    }

    override fun showMessage(@StringRes stringId: Int) {
        snackbar.setText(stringId).show()
    }

    override fun showMessage(message: String) {
        snackbar.setText(message).show()
    }
}