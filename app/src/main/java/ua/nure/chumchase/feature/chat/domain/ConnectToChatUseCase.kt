package ua.nure.chumchase.feature.chat.domain

import io.getstream.chat.android.client.ChatClient
import ua.nure.chumchase.auth.domain.AuthRepository
import ua.nure.chumchase.core.base.BaseResult

class ConnectToChatUseCase(
    private val chatClient: ChatClient,
    private val authRepository: AuthRepository
) {
    suspend fun execute(): BaseResult<Boolean> {
//        authRepository.getLoggedUser().data?.let {
//            val user = it.toChatModel()
//            val result = chatClient.connectUser(
//                user = user,
//                token = chatClient.devToken(user.id)
//            ).await()
//            if (result.isSuccess) return BaseResult(isSuccess = true)
//        }
        return BaseResult(isSuccess = false)
    }
}