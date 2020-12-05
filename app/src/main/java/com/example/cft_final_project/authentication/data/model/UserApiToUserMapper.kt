package com.example.cft_final_project.authentication.data.model

import com.example.cft_final_project.common.util.Mapper
import com.example.cft_final_project.common.util.MissingBaseDataException

object UserApiToUserMapper : Mapper<UserApiModel, User> {
    override fun map(input: UserApiModel): User {
        input.apply {
            return User(
                name = name ?: throw MissingBaseDataException(),
                type = if (role != null) UserType.valueOf(role) else throw MissingBaseDataException()
            )
        }
    }

}