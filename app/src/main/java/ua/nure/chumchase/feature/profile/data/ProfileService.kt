package ua.nure.chumchase.feature.profile.data

import retrofit2.Response
import retrofit2.http.*
import ua.nure.chumchase.feature.profile.data.entity.Profile

interface ProfileService {
    @Headers("Content-Type: application/json")
    @GET("user/get")
    suspend fun getMyProfile(
        @Header("Authorization") token: String,
    ): Response<Profile>

    @Headers("Content-Type: application/json")
    @POST("user/get/{uid}")
    suspend fun getProfile(@Path("uid") uid: String): Response<Profile>
}