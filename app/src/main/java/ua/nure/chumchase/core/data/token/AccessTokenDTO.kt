package ua.nure.chumchase.core.data.token

data class AccessTokenDTO(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long
)