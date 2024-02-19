package ua.nure.chumchase.core.data.token

data class AccessToken(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long
)