package com.example.endlessproject.appList

import com.example.endlessproject.authentication.ListKey
import com.example.endlessproject.tools.Either
import com.example.endlessproject.tools.Failure
import com.example.endlessproject.tools.handleHttpResponse

interface AppListRepository {
    suspend fun getPagedList(offset: Int, listKey: ListKey): Either<Failure, EndlessListResponse>
}

class AppListRepositoryImpl(private val network: AppListService) : AppListRepository {
    override suspend fun getPagedList(
        offset: Int,
        listKey: ListKey
    ): Either<Failure, EndlessListResponse> {
        return network.getApplicationList(
            listKey.name, offset
        ).handleHttpResponse()
    }

}