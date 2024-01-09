package ua.nure.chumchase.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.LoginUseCase
import ua.nure.chumchase.base.BaseViewModel

class LoginViewModel(private val loginUseCase: LoginUseCase) :
    BaseViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    fun setLogin(text: String) {
        _login.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    fun login() {
        startLoading()
        viewModelScope.launch {
            val result = loginUseCase.execute(login.value, password.value)
            handleResult(result)
        }
    }
}