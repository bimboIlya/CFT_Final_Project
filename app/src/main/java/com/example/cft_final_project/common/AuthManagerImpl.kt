package com.example.cft_final_project.common

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthManagerImpl(
    private val sharedPref: SharedPreferences
) : AuthManager {

    override val accessToken: String?
        get() = sharedPref.getString(ACCESS_TOKEN_KEY, null)

    override fun setToken(accessToken: String) {
        sharedPref.edit {
            putString(ACCESS_TOKEN_KEY, accessToken)
        }
    }

    override fun invalidateToken() {
        sharedPref.edit {
            putString(ACCESS_TOKEN_KEY, null)
        }
    }


    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
    }
}