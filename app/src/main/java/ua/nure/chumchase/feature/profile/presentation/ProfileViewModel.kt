package ua.nure.chumchase.feature.profile.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.profile.domain.ProfileRepository
import ua.nure.chumchase.feature.profile.domain.SendCommentUseCase
import ua.nure.chumchase.feature.profile.domain.model.PlainComment
import ua.nure.chumchase.feature.profile.domain.model.Profile

class ProfileViewModel(
    private val uid: String?,
    private val profileRepository: ProfileRepository,
    private val sendCommentUseCase: SendCommentUseCase
) : BaseViewModel() {
    private val _currentComment = MutableLiveData<String>()
    val currentComment: LiveData<String>
        get() = _currentComment
    private val _userInfo = MutableLiveData<Profile>()
    val userInfo: LiveData<Profile>
        get() = _userInfo
    private val _isMyProfile = MutableLiveData<Boolean>()
    val isMyProfile: LiveData<Boolean>
        get() = _isMyProfile

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
            val result = if (uid == null) {
                profileRepository.getLoggedUserInfo()
            } else {
                profileRepository.getUserInfo(uid)
            }
            _isMyProfile.postValue(
                if (uid != null) {
                    profileRepository.isMyProfile(uid).isSuccess
                } else true
            )

            result.data?.let {
                _userInfo.postValue(it)
            }
            handleResult(result)
        }
    }

    fun sendComment() {
        userInfo.value?.let {
            val comment = PlainComment(it.uid, currentComment.value)
            _isCommentSending.value = true
            viewModelScope.launch {
                sendCommentUseCase.execute(comment)
                if (uid == null) {
                    profileRepository.getLoggedUserInfo().data?.let { profile ->
                        _userInfo.postValue(profile)
                    }
                } else {
                    profileRepository.getUserInfo(uid).data?.let { profile ->
                        _userInfo.postValue(profile)
                    }
                }
                _isCommentSending.postValue(false)
            }
        }
    }
}