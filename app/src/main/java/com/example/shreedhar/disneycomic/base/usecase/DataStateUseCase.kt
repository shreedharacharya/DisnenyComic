package com.example.shreedhar.disneycomic.base.usecase

import com.example.shreedhar.disneycomic.base.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class DataStateUseCase<in Params, out ReturnType>(private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO) where ReturnType : Any {
    suspend operator fun invoke(params: Params) = flow {
        execute(params)
    }.flowOn(coroutineDispatcher)

    protected abstract suspend fun FlowCollector<Result<ReturnType>>.execute(params: Params)
}