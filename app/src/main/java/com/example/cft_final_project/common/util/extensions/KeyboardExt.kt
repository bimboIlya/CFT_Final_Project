package com.example.cft_final_project.common.util.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

fun View.hideKeyboard() {
    clearFocus()
    val imm = context.getSystemService<InputMethodManager>()
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

fun Activity.hideKeyboard() = currentFocus?.hideKeyboard()

fun Fragment.hideKeyboard() = requireActivity().hideKeyboard()

fun TextView.onActionDoneHideKeyboard() = setOnEditorActionListener { v, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_DONE) {
        v.hideKeyboard()
        true
    } else {
        false
    }
}
