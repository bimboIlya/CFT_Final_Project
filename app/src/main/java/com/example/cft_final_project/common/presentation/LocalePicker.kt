package com.example.cft_final_project.common.presentation

import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.cft_final_project.R
import com.yariksoffice.lingver.Lingver

const val RUSSIAN = "ru"
const val ENGLISH = "en"

fun FragmentActivity.setEnglishSystemLocale() {
    Lingver.getInstance().setLocale(this, ENGLISH)
    recreate()
}

fun FragmentActivity.setRussianSystemLocale() {
    Lingver.getInstance().setLocale(this, RUSSIAN)
    recreate()
}

fun Fragment.showTranslatePopUpMenu() {
    val view = requireActivity().findViewById<View>(R.id.menu_translate)
    PopupMenu(requireContext(), view).apply {
        menuInflater.inflate(R.menu.menu_language, menu)

        setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_english -> {
                    requireActivity().setEnglishSystemLocale()
                    true
                }
                R.id.menu_russian -> {
                    requireActivity().setRussianSystemLocale()
                    true
                }
                else -> false
            }
        }

        show()
    }
}