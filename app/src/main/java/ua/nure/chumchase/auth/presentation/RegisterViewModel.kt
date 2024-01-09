package ua.nure.chumchase.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.EmailFieldErrors
import ua.nure.chumchase.auth.domain.ErrorMessage
import ua.nure.chumchase.auth.domain.PasswordFieldErrors
import ua.nure.chumchase.auth.domain.RegisterUseCase
import ua.nure.chumchase.base.BaseViewModel

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : BaseViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private val _repeatedPassword = MutableLiveData<String>()
    val repeatedPassword: LiveData<String>
        get() = _repeatedPassword

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    fun setLogin(text: String) {
        _login.value = text
    }

    fun setPassword(text: String) {
        _password.value = text
    }

    fun setEmail(text: String) {
        _email.value = text
    }

    fun setRepeatedPassword(text: String) {
        _repeatedPassword.value = text
    }

    fun isRegistrationAvailable(): Boolean {
        val passwordValidation = verifyPassword()
        val repeatedPasswordValidation = verifyRepeatedPassword()
        val emailValidation = verifyEmail()
        return (login.value
            ?: "").isNotBlank() && passwordValidation.isValid && repeatedPasswordValidation.isValid && emailValidation.isValid
    }

    fun verifyPassword(): ValidState {
        val message = PasswordFieldErrors.getMessage(password.value ?: "")
        return getValidStateByMessage(message)
    }

    fun verifyRepeatedPassword(): ValidState {
        val message = PasswordFieldErrors.getMessage(repeatedPassword.value ?: "")
        return if (repeatedPassword.value == password.value) getValidStateByMessage(message)
        else ValidState(false, PasswordFieldErrors.NOT_MATCH)
    }

    fun verifyEmail(): ValidState {
        val message = EmailFieldErrors.getMessage(email.value ?: "")
        return getValidStateByMessage(message)
    }

    private fun getValidStateByMessage(message: ErrorMessage?): ValidState {
        return if (message != null) {
            ValidState(false, message)
        } else ValidState(true)
    }

    fun register() {
        startLoading()
        viewModelScope.launch {
            val result = registerUseCase.execute(login.value, password.value, email.value)
            handleResult(result)
        }
    }
}