package ua.nure.chumchase.feature.profile.domain.model

data class ProfileDTO(
    val uid: String,
    val login: String,
    val email: String,
    val photoUrl: String? = null,
    val tags: List<String>? = listOf()
)