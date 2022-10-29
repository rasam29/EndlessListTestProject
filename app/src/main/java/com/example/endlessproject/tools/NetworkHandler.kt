package com.example.endlessproject.tools

import retrofit2.Response

fun <T> Response<T>.handleHttpResponse(): Either<Failure, T> {
    return if (isSuccessful && body() != null) Either.Right(body() as T)
    else Either.Left(Failure(""))
}
