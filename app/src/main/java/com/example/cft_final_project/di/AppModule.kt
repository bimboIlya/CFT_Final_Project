package com.example.cft_final_project.di

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.AuthManagerImpl
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParser
import com.example.cft_final_project.common.exceptions.error_parser.ErrorParserImpl
import com.example.cft_final_project.loans.data.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    single { provideAuthManager(get()) }
    single<ErrorParser> { ErrorParserImpl() }
    single { provideDatabase(get()) }
    single { Dispatchers.IO }
}

private fun provideAuthManager(context: Context): AuthManager {
    val defaultPrefs = PreferenceManager.getDefaultSharedPreferences(context)

    return AuthManagerImpl(defaultPrefs)
}

private fun provideDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app.db"
    ).build()
}
