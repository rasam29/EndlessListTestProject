package com.example.endlessproject.authentication

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.endlessproject.tools.*
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
            _handleFailure.postValue(it)
            _isLoading.postValue(false)
        }
    }

    fun getAuthKey(pair: Pair<Int, Int>) = viewModelScope.launch(IO) {
        val result = repository.getAuthKey(pair.first * pair.second)
        result.onFailure {
            _isLoading.postValue(false)
            _handleFailure.postValue(it)
        }
        result.onSuccess {
            sharedPref.edit().putString("token", it).apply()
            _isLoading.postValue(false)
            _navigateToMainPage.postValue(Unit)
        }
    }
}
