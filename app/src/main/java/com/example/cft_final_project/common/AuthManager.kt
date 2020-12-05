package com.example.cft_final_project.common

interface AuthManager {

    val accessToken: String?

    fun hasAccessToken() = accessToken != null

    fun setToken(accessToken: String)

    fun invalidateToken()
}