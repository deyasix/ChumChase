package ua.nure.chumchase.core.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.core.profile.domain.GetUserInfoUseCase
import ua.nure.chumchase.core.profile.domain.model.Comment
import java.time.LocalDateTime

class ProfileViewModel(private val getUserInfoUseCase: GetUserInfoUseCase) : BaseViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String>
        get() = _login
    private val _photoUrl = MutableLiveData<String>()
    val photoUrl: LiveData<String>
        get() = _photoUrl

    private val _tags = MutableLiveData<List<String>>()
    val tags: LiveData<List<String>>
        get() = _tags

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>>
        get() = _comments
    private val _currentComment = MutableLiveData<String>()
    val currentComment: LiveData<String>
        get() = _currentComment

    fun setCurrentComment(text: String) {
        _currentComment.value = text
    }

    init {
        getInfo()
    }

    private fun getInfo() {
        startLoading()
        viewModelScope.launch {
            val result = getUserInfoUseCase.execute()
            result.data?.let {
                _login.postValue(it.login)
                _photoUrl.postValue(it.photoUrl)
                _tags.postValue(it.tags)
                _comments.postValue(it.comments)
            }
            handleResult(result)
        }
    }

    fun sendComment() {
        val dateTime = LocalDateTime.now().toString()
        val comment =
            Comment(_login.value ?: "", _currentComment.value ?: "", _photoUrl.value, dateTime)
        _comments.value?.reversed()?.toMutableList()?.let {
            it.add(comment)
            _comments.value = it.reversed()
        }
    }
}