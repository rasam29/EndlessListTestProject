package com.example.endlessproject.tools

import retrofit2.Response

suspend fun <T> handleHttpResponse( request:suspend ()-> Response<T>): Either<Failure, T> {
    return try {
        val response = request.invoke()
        return if (response.isSuccessful && response.body() != null) Either.Right(response.body() as T)
        else Either.Left(Failure(""))
    }catch (e:Exception){
        Either.Left(Failure(""))
    }

}
