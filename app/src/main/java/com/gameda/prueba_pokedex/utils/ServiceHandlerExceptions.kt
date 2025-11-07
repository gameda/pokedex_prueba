package com.gameda.prueba_pokedex.utils

import retrofit2.HttpException
import java.io.IOException

suspend fun <T> serviceHandlerExceptions(apiCall: suspend () -> T): Result<T> =
    try {
        Result.success(
            apiCall()
        )
    } catch (e: HttpException) {
        Result.failure(e)
    } catch (e: IOException) {
        Result.failure(e)
    }