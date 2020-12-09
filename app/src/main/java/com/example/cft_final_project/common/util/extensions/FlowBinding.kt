package com.example.cft_final_project.common.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

/**
 * Создает [Flow] изменений текста из [TextView]
 *
 * Держит ссылку на [TextView] до отмены корутины, на которой работает [Flow]
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun TextView.textChanges() = callbackFlow<CharSequence> {
    val listener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            offer(s)
        }

        override fun afterTextChanged(s: Editable) {}
    }

    addTextChangedListener(listener)
    awaitClose { removeTextChangedListener(listener) }
}
    .onStart { emit(text) }