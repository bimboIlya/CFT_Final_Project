package com.example.cft_final_project.authentication.data.network

import com.google.gson.annotations.SerializedName

class AuthParams(
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)