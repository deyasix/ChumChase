package ua.nure.chumchase.feature.settings.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.nure.chumchase.auth.domain.UserDataSource
import ua.nure.chumchase.core.base.BaseViewModel

class SettingsViewModel(private val userDataSource: UserDataSource) : BaseViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>>
        get() = _tags

    init {
        getInfo()
    }

    fun changeLogin(newLogin: String) {
        _login.value = newLogin
    }

    fun changeEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun changeTags(newTags: List<String>) {
        _tags.value = newTags
    }

    private fun getInfo() {
        startLoading()
        viewModelScope.launch {
            val result = userDataSource.getLoggedUser()
            handleResult(result)
            if (result.isSuccess) {
                result.data?.let {
                    _login.postValue(it.login)
                    _tags.postValue(it.tags)
                }
            }
        }
    }
}