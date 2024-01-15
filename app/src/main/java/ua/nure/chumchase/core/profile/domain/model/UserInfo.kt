package ua.nure.chumchase.core.profile.domain.model

data class UserInfo(
    val login: String,
    val tags: List<String> = listOf(),
    val photoUrl: String,
    val comments: List<Comment> = listOf()
)
