package ua.nure.chumchase.feature.friends.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.friends.domain.FriendDTO
import ua.nure.chumchase.feature.friends.domain.GetFriendsUseCase
import ua.nure.chumchase.feature.friends.domain.SearchFriendUseCase

class FriendsViewModel(
    private val getFriendsUseCase: GetFriendsUseCase,
    private val searchFriendUseCase: SearchFriendUseCase
) : BaseViewModel() {
    private val _friends = MutableLiveData<List<FriendDTO>>()
    val friends: LiveData<List<FriendDTO>>
        get() = _friends
    private val _isSearching = MutableLiveData<Boolean>(false)
    val isSearching: LiveData<Boolean>
        get() = _isSearching

    init {
        getFriends()
    }

    private fun getFriends() {
        startLoading()
        viewModelScope.launch {
            val result = getFriendsUseCase.execute()
            handleResult(result)
            if (result.isSuccess) _friends.postValue(result.data ?: listOf())
        }
    }

    fun search(query: String) {
        _isSearching.value = true
        viewModelScope.launch {
            val result = searchFriendUseCase.execute(query)
            if (result.isSuccess) _friends.postValue(result.data ?: listOf())
            _isSearching.postValue(false)
        }
    }
}