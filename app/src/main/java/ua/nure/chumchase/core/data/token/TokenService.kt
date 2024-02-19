package ua.nure.chumchase.core.data.token

import retrofit2.Response
import retrofit2.http.*
import ua.nure.chumchase.auth.data.entity.AccessToken

interface TokenService {
    @Headers("Content-Type: application/json")
    @POST("user/refresh_token")
    suspend fun refreshToken(@Header("Authorization") refreshToken: String): Response<AccessToken>
}