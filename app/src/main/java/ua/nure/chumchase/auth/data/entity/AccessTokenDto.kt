package ua.nure.chumchase.auth.data.entity

import com.google.gson.annotations.SerializedName

data class AccessTokenDto(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("expires_in") val expiresIn: Long
)
