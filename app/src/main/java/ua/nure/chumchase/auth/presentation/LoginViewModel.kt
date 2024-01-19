package ua.nure.chumchase.auth.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.nure.chumchase.auth.domain.LoginUseCase
import ua.nure.chumchase.core.base.BaseViewModel

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

    fun isLoginAvailable(): Boolean {
        return (login.value ?: "").isNotEmpty() && (password.value ?: "").isNotEmpty()
    }

    fun login() {
        startLoading()
        viewModelScope.launch {
            val result = loginUseCase.execute(login.value, password.value)
            handleResult(result)
            if (result.isSuccess) Timber.d("Success")
            else Timber.d("Failure")
        }
    }
}