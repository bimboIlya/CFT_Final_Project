package com.example.cft_final_project.di

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.AuthManagerImpl
import com.example.cft_final_project.common.error_parser.ErrorParser
import com.example.cft_final_project.common.error_parser.ErrorParserImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    single { provideAuthManager(get()) }
    single<ErrorParser> { ErrorParserImpl() }
    single { Dispatchers.IO }
}

private fun provideAuthManager(context: Context): AuthManager {
    val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context)

    return AuthManagerImpl(defaultPrefs)
}
