package ua.nure.chumchase.feature.profile.domain.model

data class UserInfoDTO(
    val login: String,
    val tags: List<String> = listOf(),
    val photoUrl: String? = null,
    val comments: List<CommentDTO> = listOf()
)
