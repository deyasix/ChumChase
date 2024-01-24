package ua.nure.chumchase.feature.friends.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.friends.domain.FriendDTO

class FriendsViewModel : BaseViewModel() {
    private val _friends = MutableLiveData<List<FriendDTO>>()
    val friends: LiveData<List<FriendDTO>>
        get() = _friends

    fun search(query: String) {

    }
}