package com.example.cft_final_project.authentication.data.model

data class User(
    val name: String,
    val type: UserType
)

enum class UserType {
    ADMIN, USER
}