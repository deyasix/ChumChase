package ua.nure.chumchase.feature.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.profile.domain.UserInfoRepository
import ua.nure.chumchase.feature.profile.domain.model.CommentDTO
import ua.nure.chumchase.feature.profile.domain.model.UserInfoDTO
import java.time.LocalDateTime

class ProfileViewModel(private val userInfoRepository: UserInfoRepository) : BaseViewModel() {
    private val _currentComment = MutableLiveData<String>()
    val currentComment: LiveData<String>
        get() = _currentComment
    private val _userInfo = MutableLiveData<UserInfoDTO>()
    val userInfo: LiveData<UserInfoDTO>
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
            val result = userInfoRepository.getLoggedUserInfo()
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
                userInfoRepository.sendComment(commentDTO, it)
                userInfoRepository.getLoggedUserInfo().data?.let {
                    _userInfo.postValue(it)
                }
                _isCommentSending.postValue(false)
            }
        }
    }
}