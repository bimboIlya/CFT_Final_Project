package com.example.cft_final_project.authentication.data.model

import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import com.example.cft_final_project.common.util.Mapper

object UserApiToUserMapper : Mapper<UserApiModel, User> {
    override fun map(input: UserApiModel): User {
        input.apply {
            return User(
                name = name ?: throw MissingBaseDataFromApiException(),
                type = if (role != null) UserType.valueOf(role) else
                    throw IllegalArgumentException("Unknown user role: $role")
            )
        }
    }

}