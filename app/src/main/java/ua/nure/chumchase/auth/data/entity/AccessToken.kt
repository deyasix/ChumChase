package ua.nure.chumchase.auth.data.entity

import com.google.gson.annotations.SerializedName
import ua.nure.chumchase.core.base.ResponseEntity
import ua.nure.chumchase.core.data.token.AccessTokenDTO

data class AccessToken(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Long
) : ResponseEntity<AccessTokenDTO> {
    override fun toDomainModel(): AccessTokenDTO =
        AccessTokenDTO(accessToken, refreshToken, expiresIn)

}
