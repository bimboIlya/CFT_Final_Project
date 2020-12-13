package com.example.cft_final_project

import android.app.Application
import com.example.cft_final_project.di.appModule
import com.example.cft_final_project.di.authModule
import com.example.cft_final_project.di.loanModule
import com.example.cft_final_project.di.networkModule
import com.yariksoffice.lingver.Lingver
import com.yariksoffice.lingver.store.PreferenceLocaleStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.*

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Lingver.init(this, PreferenceLocaleStore(this))

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        val modules = listOf(
            appModule,
            authModule,
            loanModule,
            networkModule
        )

        startKoin {
            androidContext(this@MyApp)
            modules(modules)
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}