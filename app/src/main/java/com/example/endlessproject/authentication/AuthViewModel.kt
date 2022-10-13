package com.example.endlessproject.authentication

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.endlessproject.tools.BaseViewModel
import com.example.endlessproject.tools.SingleLiveEvent
import com.example.endlessproject.tools.onFailure
import com.example.endlessproject.tools.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val sharedPref: SharedPreferences,
    private val repository: AuthRepository
) : BaseViewModel() {


    private val _navigateToMainPage: MutableLiveData<Unit> = SingleLiveEvent()
    val navigateToMainPage: LiveData<Unit> = _navigateToMainPage

    private val _isLoading: MutableLiveData<Boolean> = SingleLiveEvent()
    val isAuthLoading: LiveData<Boolean> = _isLoading

    fun authenticate() = viewModelScope.launch(IO) {
        _isLoading.postValue(true)
        val list = repository.getNumberList()
        list.onSuccess {
            val pair = it.foundDesignatedPair()
            getAuthKey(pair)
        }
        list.onFailure {
            handleFailure.postValue(it)
            _isLoading.postValue(false)
        }
    }

    private fun getAuthKey(pair: Pair<Int, Int>) = viewModelScope.launch(IO) {
        val result = repository.getAuthKey(pair.first * pair.second)
        result.onFailure {
            _isLoading.postValue(false)
            handleFailure.postValue(it)
        }
        result.onSuccess {
            sharedPref.edit().putString("token", it).apply()
            _isLoading.postValue(false)
            _navigateToMainPage.postValue(Unit)
        }
    }

    /**
    function will find to pair of number for exp (a,b) which a+b = sum :2020
     */
    private fun MutableList<Int>.foundDesignatedPair(sum: Int = 2020): Pair<Int, Int> {
        sort()
        forEach { item ->
            val tempVar = find {
                it == sum - item
            }
            if (tempVar != null) {
                return Pair(item, tempVar)
            }
        }
        return Pair(0, 0)
    }
}