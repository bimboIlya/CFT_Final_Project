package com.example.cft_final_project.authentication.data.sources.network

import com.google.gson.annotations.SerializedName

class AuthParams(
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String
)