package com.example.cft_final_project.common.domain

import com.example.cft_final_project.common.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class CoroutineUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(params: P): Result<R> = withContext(coroutineDispatcher) {
        return@withContext try {
            val result = execute(params)
            Result.Success(result)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    abstract suspend fun execute(params: P): R
}

abstract class Scenario<in P, R>(
    coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<P, R>(coroutineDispatcher)