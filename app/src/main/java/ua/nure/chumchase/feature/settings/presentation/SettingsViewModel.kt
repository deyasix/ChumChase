package ua.nure.chumchase.feature.settings.presentation

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.nure.chumchase.core.base.BaseViewModel

class SettingsViewModel : BaseViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login

    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
        get() = _email

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>>
        get() = _tags

    fun changeLogin(newLogin: String) {
        _login.value = newLogin
    }

    fun changeEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun changeTags(newTags: List<String>) {
        _tags.value = newTags
    }
}