package ua.nure.chumchase.feature.profile.data

import retrofit2.Response
import retrofit2.http.*
import ua.nure.chumchase.feature.profile.data.entity.ProfileDto

interface ProfileService {
    @GET("user/get")
    suspend fun getMyProfile(): Response<ProfileDto>

    @GET("user/get/{uid}")
    suspend fun getProfile(@Path("uid") uid: String): Response<ProfileDto>

    @GET("user/is_me/{uid}")
    suspend fun isMyProfile(@Path("uid") uid: String): Response<Any>
}