package ua.nure.chumchase.feature.profile.data.entity

data class Profile(
    val uid: String,
    val login: String,
    val email: String,
    val photo_url: String,
    val tag_list: List<String>
)
