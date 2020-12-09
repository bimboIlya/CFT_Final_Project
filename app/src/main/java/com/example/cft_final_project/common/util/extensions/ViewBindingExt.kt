package com.example.cft_final_project.common.util.extensions

import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding


fun ViewBinding.getString(@StringRes stringId: Int): String {
    return this.root.context.getString(stringId)
}

fun ViewBinding.getString(@StringRes stringId: Int, vararg args: Any?): String {
    return this.root.context.getString(stringId, *args)
}