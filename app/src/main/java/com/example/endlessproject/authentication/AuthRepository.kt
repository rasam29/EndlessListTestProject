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
        return handleHttpResponse {
            network.getNumber()
        }
    }

    override suspend fun getAuthKey(multipliedNumber: Int): Either<Failure, String> {
        return handleHttpResponse{
            network.retrieveAuthToken(multipliedNumber)
        }
    }
}
