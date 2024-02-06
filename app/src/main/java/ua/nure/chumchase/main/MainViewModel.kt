package ua.nure.chumchase.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.AuthRepository
import ua.nure.chumchase.core.base.BaseResult
import ua.nure.chumchase.core.base.BaseViewModel

class MainViewModel(private val authRepository: AuthRepository): BaseViewModel() {
    private val _isUserLogged = MutableLiveData<Boolean>()
    val isUserLogged: LiveData<Boolean>
        get() = _isUserLogged

    init {
        startLoading()
        viewModelScope.launch {
            //val result = authRepository.getLoggedUser()
            val result = BaseResult<Boolean>(isSuccess = false)
            handleResult(result)
            _isUserLogged.postValue(result.isSuccess)
        }
    }
}