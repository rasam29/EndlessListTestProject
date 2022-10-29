package com.example.endlessproject.appList.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.endlessproject.appList.AppListRepository
import com.example.endlessproject.appList.AppPlusMetaData
import com.example.endlessproject.authentication.ListKey
import com.example.endlessproject.tools.Either

class AppListPagingSource(
    private val repository: AppListRepository,
    private val listKey: ListKey,
    private val max: (AppPlusMetaData?) -> Unit
) :
    PagingSource<Int, AppPlusMetaData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppPlusMetaData> {
        val response = repository.getPagedList(
            offset = params.key ?: 0,
            listKey
        )

        return when {
            response.isRight -> {
                val pageData = (response as Either.Right).b
                max.invoke(
                    pageData.appPlusMetaData.maxByOrNull {
                        it.rating ?: 0F
                    }
                )

                LoadResult.Page(
                    data = pageData.appPlusMetaData,
                    prevKey = null, // Only paging forward.
                    nextKey = if (pageData.eol == false) (params.key ?: 0) + 20 else null
                )
            }
            response.isLeft -> {
                LoadResult.Error(Throwable())
            }
            else -> {
                LoadResult.Error(Throwable())
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AppPlusMetaData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
