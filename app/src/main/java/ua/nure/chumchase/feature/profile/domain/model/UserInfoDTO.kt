package ua.nure.chumchase.feature.profile.domain.model

import io.getstream.chat.android.models.User

data class UserInfoDTO(
    val id: Int,
    val login: String,
    val tags: List<String> = listOf(),
    val photoUrl: String? = null,
    val comments: List<CommentDTO> = listOf()
) {
    fun toChatModel(): User {
        return if (photoUrl != null) User(id = "$id", name = login, image = photoUrl) else User(
            id = "$id",
            name = login
        )
    }
}
