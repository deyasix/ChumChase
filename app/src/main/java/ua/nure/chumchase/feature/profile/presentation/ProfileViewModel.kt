package ua.nure.chumchase.feature.profile.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.profile.domain.ProfileRepository
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.ProfileDTO
import java.time.LocalDateTime

class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {
    private val _currentComment = MutableLiveData<String>()
    val currentComment: LiveData<String>
        get() = _currentComment
    private val _userInfo = MutableLiveData<ProfileDTO>()
    val userInfo: LiveData<ProfileDTO>
        get() = _userInfo
    private val _isCommentSending = MutableLiveData<Boolean>()
    val isCommentSending: LiveData<Boolean>
        get() = _isCommentSending

    fun setCurrentComment(text: String) {
        _currentComment.value = text
    }

    init {
        getInfo()
    }

    private fun getInfo() {
        startLoading()
        viewModelScope.launch {
            val result = profileRepository.getLoggedUserInfo()
            result.data?.let {
                _userInfo.postValue(it)
            }
            handleResult(result)
        }
    }

    fun sendComment() {
        val dateTime = LocalDateTime.now().toString()
        userInfo.value?.let {
            val commentDTO =
                CommentDTO(it, _currentComment.value ?: "", dateTime)
            _isCommentSending.value = true
            viewModelScope.launch {
                profileRepository.sendComment(commentDTO, it)
                profileRepository.getLoggedUserInfo().data?.let {
                    _userInfo.postValue(it)
                }
                _isCommentSending.postValue(false)
            }
        }
    }
}