package ua.nure.chumchase.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.core.base.BaseViewModel

class MainViewModel(private val userDataSource: UserDataSource): BaseViewModel() {
    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged

    init {
        startLoading()
        viewModelScope.launch {
            val result = userDataSource.getLoggedUser()
            handleResult(result)
            _isUserLogged.postValue(result.isSuccess)
        }
    }
}