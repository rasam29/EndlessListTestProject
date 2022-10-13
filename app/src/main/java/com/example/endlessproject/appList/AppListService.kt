package com.example.endlessproject.appList

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppListService {

    @GET("applications/package/{listKey}")
    suspend fun getApplicationList(
        @Path("listKey")
        listKey: String,
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int = 20,
        @Query("lang")
        lang: String = "fa"
    ): Response<EndlessListResponse>

}