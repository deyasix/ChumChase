package ua.nure.chumchase.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.AuthDataSource
import ua.nure.chumchase.core.base.BaseViewModel

class MainViewModel(private val authDataSource: AuthDataSource): BaseViewModel() {
    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged

    init {
        startLoading()
        viewModelScope.launch {
            val result = authDataSource.isUserLogged()
            handleResult(result)
            _isUserLogged.postValue(result.data?:false)
        }
    }
}