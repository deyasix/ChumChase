package ua.nure.chumchase.auth.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.AuthRepository
import ua.nure.chumchase.core.domain.BaseFieldErrors
import ua.nure.chumchase.auth.domain.model.LoginUserDTO
import ua.nure.chumchase.core.base.BaseOperationResult
import ua.nure.chumchase.core.base.BaseViewModel

class LoginViewModel(private val authRepository: AuthRepository) :
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
            val loginValue = login.value
            val passwordValue = password.value
            val result = if (loginValue == null || passwordValue == null) BaseOperationResult(
                isSuccess = false,
                error = BaseFieldErrors.EMPTY
            )
            else authRepository.login(LoginUserDTO(loginValue, passwordValue))
            handleResult(result)
        }
    }
}