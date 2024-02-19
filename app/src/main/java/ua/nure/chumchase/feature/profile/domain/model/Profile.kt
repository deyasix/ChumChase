package ua.nure.chumchase.feature.profile.domain.model

data class Profile(
    val uid: String,
    val login: String,
    val email: String,
    val photoUrl: String?,
    val tags: List<String>,
    val comments: List<Comment>
)