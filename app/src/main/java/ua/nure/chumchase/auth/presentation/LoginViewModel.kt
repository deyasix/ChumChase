package ua.nure.chumchase.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.nure.chumchase.auth.domain.LoginUseCase

class LoginViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
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
        loginUseCase.execute()
    }
}