package ua.nure.chumchase.feature.chat.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.nure.chumchase.core.base.BaseViewModel
import ua.nure.chumchase.feature.chat.domain.ConnectToChatUseCase

class ChatViewModel(private val connectToChatUseCase: ConnectToChatUseCase): BaseViewModel() {
    init {
        startLoading()
        viewModelScope.launch {
            val result = connectToChatUseCase.execute()
            handleResult(result)
        }
    }
}