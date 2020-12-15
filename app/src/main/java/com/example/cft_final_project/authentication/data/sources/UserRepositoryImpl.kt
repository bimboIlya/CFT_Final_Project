package com.example.cft_final_project.authentication.data.sources

import com.example.cft_final_project.authentication.data.model.User
import com.example.cft_final_project.authentication.data.model.UserApiToUserMapper
import com.example.cft_final_project.authentication.data.sources.network.AuthApiService
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.exceptions.MissingBaseDataFromApiException
import com.example.cft_final_project.common.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val authApiService: AuthApiService,
    private val dispatcherIO: CoroutineDispatcher
) : UserRepository {

    override suspend fun register(credentials: AuthParams): Result<User> =
        withContext(dispatcherIO) {
            return@withContext try {

                val userApi = authApiService.registration(credentials)
                val user = UserApiToUserMapper.map(userApi)

                Result.Success(user)
            } catch (ex: Throwable) {
                Result.Error(ex)
            }
        }

    override suspend fun login(credentials: AuthParams): Result<String> =
        withContext(dispatcherIO) {
            return@withContext try {

                val tokenApi = authApiService.login(credentials)?.string()
                val token: String = tokenApi ?: throw MissingBaseDataFromApiException()

                Result.Success(token)
            } catch (ex: Throwable) {
                Result.Error(ex)
            }
        }
}