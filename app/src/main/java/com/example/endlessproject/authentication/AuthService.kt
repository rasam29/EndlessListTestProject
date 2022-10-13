package com.example.endlessproject.authentication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthService {
    @GET("/data")
    suspend fun getNumber(): Response<String>

    @GET("/authorization/{answer}")
    suspend fun retrieveAuthToken(@Path("answer") multipliedNumber: Int): Response<String>
}