package com.example.cft_final_project.authentication.data

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.model.UserApiToUserMapper
import com.example.cft_final_project.authentication.data.network.AuthApiService
import com.example.cft_final_project.authentication.data.network.AuthParams
import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import com.example.cft_final_project.common.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val authApiService: AuthApiService,
    private val dispatcherIO: CoroutineDispatcher
) : UserRepository {

    override suspend fun register(name: String, password: String): Result<User> =
        withContext(dispatcherIO) {
            return@withContext try {
                val credentials = AuthParams(name, password)

                val userApi = authApiService.registration(credentials)
                val user = UserApiToUserMapper.map(userApi)

                Result.Success(user)
            } catch (ex: Throwable) {
                Result.Error(ex)
            }
        }

    override suspend fun login(name: String, password: String): Result<String> =
        withContext(dispatcherIO) {
            return@withContext try {
                val credentials = AuthParams(name, password)

                val tokenApi = authApiService.login(credentials)?.string()
                val token: String = tokenApi ?: throw MissingBaseDataFromApiException()

                Result.Success(token)
            } catch (ex: Throwable) {
                Result.Error(ex)
            }
        }
}