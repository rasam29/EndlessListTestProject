package com.example.endlessproject.appList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.endlessproject.appList.list.AppListPagingSource
import com.example.endlessproject.authentication.ListKey
import com.example.endlessproject.tools.BaseViewModel
import com.example.endlessproject.tools.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(private val repository: AppListRepository) :
    BaseViewModel() {

    lateinit var listKey: ListKey

    private val _maxRateItem: MutableLiveData<UiModel.AppPlusMetaData?> = SingleLiveEvent()
    val maxRateItem: LiveData<UiModel.AppPlusMetaData?> = _maxRateItem

    private val _progress: MutableLiveData<Boolean> = SingleLiveEvent()
    val progress: LiveData<Boolean> = _progress

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        AppListPagingSource(repository, listKey) {
            _maxRateItem.postValue(it)
            _progress.postValue(false)
        }
    }.flow.map {
       it.map {
           UiModel.AppPlusMetaData().apply {
               it
           }
       }.insertSeparators { before, after ->
           when {
               before != null && after != null -> {
                   UiModel.Separator
               }
               before == null -> UiModel.Separator
               after == null -> null

               else -> null
           }
       }
    }.cachedIn(viewModelScope)
     .onStart { _progress.postValue(true) }
}
