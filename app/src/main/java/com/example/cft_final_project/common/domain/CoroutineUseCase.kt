package com.example.cft_final_project.common.domain

import com.example.cft_final_project.common.network.Result

abstract class CoroutineUseCase<in P, R> {
    abstract suspend operator fun invoke(parameters: P): Result<R>
}