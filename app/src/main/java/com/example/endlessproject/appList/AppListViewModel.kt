package com.example.endlessproject.appList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.endlessproject.appList.list.AppListPagingSource
import com.example.endlessproject.authentication.ListKey
import com.example.endlessproject.tools.BaseViewModel
import com.example.endlessproject.tools.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(private val repository: AppListRepository) :
    BaseViewModel() {

    lateinit var listKey: ListKey

    private val _maxRateItem: MutableLiveData<AppPlusMetaData?> = SingleLiveEvent()
    val maxRateItem: LiveData<AppPlusMetaData?> = _maxRateItem

    private val _progress: MutableLiveData<Boolean> = SingleLiveEvent()
    val progress: LiveData<Boolean> = _progress

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        AppListPagingSource(repository, listKey) {
            _maxRateItem.postValue(it)
            _progress.postValue(false)
        }
    }.flow.cachedIn(viewModelScope).onStart { _progress.postValue(true) }
}
