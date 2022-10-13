package com.example.endlessproject.authentication

import com.example.endlessproject.tools.Either
import com.example.endlessproject.tools.Failure
import com.example.endlessproject.tools.handleHttpResponse


interface AuthRepository {
    suspend fun getNumberList(): Either<Failure, MutableList<Int>>
    suspend fun getAuthKey(multipliedNumber: Int): Either<Failure, String>
}

class AuthRepositoryImpl(private val network: AuthService) : AuthRepository {
    override suspend fun getNumberList(): Either<Failure, MutableList<Int>> {
        val response = network.getNumber()
        return if (!response.isSuccessful) {
            Either.Left(Failure())
        } else {
            val numbers = response.body()?.split(",")?.map {
                it.trim().toInt()
            }?.toMutableList()!!
            Either.Right(numbers)
        }

    }

    override suspend fun getAuthKey(multipliedNumber: Int): Either<Failure, String> {
        val response = network.retrieveAuthToken(multipliedNumber)
        return response.handleHttpResponse()
    }

}
