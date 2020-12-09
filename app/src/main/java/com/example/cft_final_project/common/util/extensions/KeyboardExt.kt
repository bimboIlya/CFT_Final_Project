package com.example.cft_final_project.common.util.extensions

import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.getSystemService

fun View.hideKeyboard() {
    clearFocus()
    val imm: InputMethodManager? = context.getSystemService()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun TextView.onActionDoneHideKeyboard() {
    this.setOnEditorActionListener { v, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            v.hideKeyboard()
            true
        } else {
            false
        }
    }
}