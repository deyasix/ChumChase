package ua.nure.chumchase.auth.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import ua.nure.chumchase.auth.data.entity.AccessToken
import ua.nure.chumchase.auth.data.entity.LoginUser
import ua.nure.chumchase.auth.data.entity.Message
import ua.nure.chumchase.auth.data.entity.RegisterUser

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(
        @Body loginUser: LoginUser
    ): Response<AccessToken>

    @Headers("Content-Type: application/json")
    @POST("user/register")
    suspend fun register(
        @Body registerUser: RegisterUser
    ): Response<Message>
}