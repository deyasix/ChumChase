package ua.nure.chumchase.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.core.data.token.TokenManager

class MainViewModel(private val tokenManager: TokenManager) : BaseViewModel() {
    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged

    init {
        startLoading()
        viewModelScope.launch {
            val result = tokenManager.isUserLogged()
            handleResult(result)
            _isUserLogged.postValue(result.data ?: false)
        }
    }
}