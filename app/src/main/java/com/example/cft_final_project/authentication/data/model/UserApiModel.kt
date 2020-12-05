package com.example.cft_final_project.authentication.data.model

import com.google.gson.annotations.SerializedName

class UserApiModel(
    @SerializedName("name") val name: String?,
    @SerializedName("role") val role: String?,
)