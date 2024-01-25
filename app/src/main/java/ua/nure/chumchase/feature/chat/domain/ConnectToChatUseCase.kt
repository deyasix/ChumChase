package ua.nure.chumchase.feature.chat.domain

import io.getstream.chat.android.client.ChatClient
import ua.nure.chumchase.core.base.BaseResult

class ConnectToChatUseCase(private val chatClient: ChatClient) {
    suspend fun execute(): BaseResult<Boolean> {
        val user = io.getstream.chat.android.models.User(
            id = "login",
            name = "Tutorial Droid",
            image = "https://bit.ly/2TIt8NR"
        )
        val r = chatClient.connectUser(
            user = user,
            token = chatClient.devToken("login")
        ).await()
        return if (r.isSuccess) BaseResult(isSuccess = true) else BaseResult(isSuccess = false)
    }
}