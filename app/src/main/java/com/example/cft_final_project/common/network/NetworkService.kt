package com.example.cft_final_project.common.network

interface NetworkService {
    fun <T> create(clazz: Class<T>): T
}