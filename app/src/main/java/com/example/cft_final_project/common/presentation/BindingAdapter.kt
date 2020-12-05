package com.example.cft_final_project.common.presentation

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun showHide(v: View, isVisible: Boolean) {
    v.isVisible = isVisible
}